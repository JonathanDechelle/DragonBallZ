package com.example.dragonballz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import android.graphics.Canvas;
import android.graphics.Color;
import android.text.TextPaint;

public class ClientJSON
{
	public Socket socket;
    private Personnage player;

	private static final int SERVERPORT = 15000;
    private static String SERVER_IP = "127.0.0.1";
	
	public boolean ConnectFailed = false;
	private TextPaint TextInfoPaint;
	private String TextInfo ="";
	
	public ClientJSON(Personnage player, String ip)
	{
		this.player = player;
		SERVER_IP = ip;
		TextInfoPaint = new TextPaint();
		TextInfoPaint.setColor(Color.RED);
		TextInfoPaint.setTextSize(40);
	}
	
	public void Connect()
	{
		try
		{
			InetAddress serverAddr = InetAddress.getByName(SERVER_IP);
			socket = new Socket(serverAddr, SERVERPORT);
		}
		catch (UnknownHostException e1)
		{
			e1.printStackTrace();   ConnectFailed = true; TextInfo = "Connect Failed Touch screen for retrying";
		}
		
		catch (IOException e1) 
		{ e1.printStackTrace(); ConnectFailed = true; TextInfo = "Connect Failed Touch screen for retrying";}
	}
	
	public void Close()
	{
		try { socket.close(); socket = null;} 
		catch (IOException e) {	e.printStackTrace();}
	}
	
	public void Envoi()
	{
		PrintWriter out = null;
		try 
		{
			if(socket != null)
			{
				out = new PrintWriter(socket.getOutputStream());
				out.println(player.toJSON()); //Envoie du message
				out.flush();
			
				System.out.println("Player envoie ... " + player.toJSON());
				socket.close();
			}
			else
			{
				System.out.println("Find any server");
			}
		} 
		
		catch (IOException e){ e.printStackTrace();}
	}
	
	public void Recoit()
	{
	   try
	   {
		   BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		   String message_distant = in.readLine();

	       player.FromJSON(message_distant);
	   
	       System.out.println("Player recoit ... " + player.toString());
	   }

	   catch (IOException e){ e.printStackTrace(); }
	}
	
	public void draw(Canvas canvas)
	{
		canvas.drawText(TextInfo, 100, 100, TextInfoPaint);
	}
	
	public void Update(Personnage player)
	{
		this.player = player;
	}
}
