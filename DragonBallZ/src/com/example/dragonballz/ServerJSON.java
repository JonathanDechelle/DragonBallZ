package com.example.dragonballz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerJSON {
	
	private Personnage player2;
	public Socket socket;
	public ServerSocket Serversocket;
	
	public ServerJSON(Personnage player2)
	{
		
		try {
				Serversocket = new ServerSocket(15000);
				this.player2 = player2;
				Thread t = new Thread(new Accepter_Joueur(Serversocket));
				t.start();
				System.out.println("Server ready !");
			} 
		
		catch (IOException e) { e.printStackTrace(); }
	}

	public Personnage Recoit()
	{
		try
		{
			if(socket != null)
			{
				BufferedReader in = new BufferedReader (new InputStreamReader (socket.getInputStream()));
				String message_distant = in.readLine();
				
				if(message_distant != null)
				{
					player2.FromJSON(message_distant);
					System.out.println("Server recoit ... " + message_distant);
				}
				
				socket.close();
			}
			else
				System.out.println("No Client");
		}

		catch (IOException e)
		{
			e.printStackTrace(); 
		}
		
		return player2;
	}
	
	public void Connect(ServerSocket socketserver)
	{
		try 
		{
			//Accepte connexion
			if(!socketserver.isClosed())
			socket = socketserver.accept();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		} 
	}
	
	public void Envoie()
	{
		try
		{
			PrintWriter out = new PrintWriter(socket.getOutputStream());
			out.println(player2.toJSON());
			out.flush();
		
			System.out.println("Server envoie ... " + player2.toJSON());
		}
		
		catch (IOException e)
		{
			e.printStackTrace();
		}
			
	}
	
	public void Close()
	{
		try { Serversocket.close(); socket = null;} 
		catch (IOException e) {	e.printStackTrace();}
	}

	class Accepter_Joueur implements Runnable {
	
		private ServerSocket socketserver;
		
		public Accepter_Joueur(ServerSocket s)
		{ 
			socketserver = s; 
		}
			
		public void run() 
		{
			while(true)
			{
				Connect(socketserver);
			}
		}
	}
}


