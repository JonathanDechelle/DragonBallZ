package com.example.dragonballz;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;

import org.apache.http.conn.util.InetAddressUtils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends Activity {

	GameView Jeu;
	ConnexionView Connexion;
	CharacterChoiceView CharacterChoice;
	ModeSPMPView ModeChoice;
	
	Context MainContext;
	boolean ConnexionSend,DialogueConnexionCreate;
	int m_CharacterChoice;
	int ViewNo = 0;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        RessourceAnimation.LoadAnimation(this);  
        NewCharacterChoiceView();
        MainContext = CharacterChoice.context;
    }
    
  
    public void DialogueConnection()
    {
    	NewGameView();
    	
    	  //Selection Server ou Client
       	AlertDialog.Builder DialogueConnection = new AlertDialog.Builder(MainContext);
			DialogueConnection.setTitle("Would you create the game and waiting others players??");
			
			DialogueConnection
				.setMessage("Yes = Create a game (Your IP =  " + getIPAddress(true)+")" + System.getProperty("line.separator") + "No = Host by IP others androids phones")
				.setCancelable(false)
				.setPositiveButton("Yes",new DialogInterface.OnClickListener() 
				{
					public void onClick(DialogInterface dialog,int id) 
					{
						((ViewGroup)Connexion.getParent()).removeView(Connexion);
                 		Jeu.ServerQuestion(true,"", m_CharacterChoice);
						setContentView(Jeu);
						ViewNo = 3;
					}
				})
				.setNegativeButton("No",new DialogInterface.OnClickListener() 
				{
					public void onClick(DialogInterface dialog,int id) 
					{
						Connexion.Keyboard.Enabled = false;
						Connexion.TextEdit.Enabled = false;
					}
				});
			
				//Creation du dialogued'alerte avec le builder
				AlertDialog alertDialog = DialogueConnection.create();
				alertDialog.show();
    }
    
    @SuppressLint("DefaultLocale") public static String getIPAddress(boolean useIPv4) {
        try {
            List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface intf : interfaces) {
                List<InetAddress> addrs = Collections.list(intf.getInetAddresses());
                for (InetAddress addr : addrs) {
                    if (!addr.isLoopbackAddress()) {
                        String sAddr = addr.getHostAddress().toUpperCase();
                        boolean isIPv4 = InetAddressUtils.isIPv4Address(sAddr); 
                        if (useIPv4) {
                            if (isIPv4) 
                                return sAddr;
                        } else {
                            if (!isIPv4) {
                                int delim = sAddr.indexOf('%'); // drop ip6 port suffix
                                return delim<0 ? sAddr : sAddr.substring(0, delim);
                            }
                        }
                    }
                }
            }
        } catch (Exception ex) { } // for now eat exceptions
        return "";
    }

    
    @Override
    protected void onDestroy() 
    {
    	super.onDestroy();
    }	

    @Override
    protected void onPause()
    {
    	super.onPause();
    }
    
    @Override
    protected void onResume()
    {
    	super.onResume();
    }
   
    public void NewCharacterChoiceView()
    {
    	 CharacterChoice = new CharacterChoiceView(this);
         setContentView(CharacterChoice);
         ViewNo = 0;
         
         CharacterChoice.setOnTouchListener(
                 new View.OnTouchListener() {
                 
                 public boolean onTouch(View myView, MotionEvent event) 
                 {
                 	if(CharacterChoice.Close)
                 	{
                 		((ViewGroup)CharacterChoice.getParent()).removeView(CharacterChoice);
                 		m_CharacterChoice = CharacterChoice.ChoixCharactere;
                 		NewModeSPMPView();
                 	}
                     return false;
                 }
             }); 
    }
    
    public void NewModeSPMPView()
    {
    	 ModeChoice = new ModeSPMPView(this);
    	 setContentView(ModeChoice);
  		 ViewNo=1;
  		 
         ModeChoice.setOnTouchListener(
                 new View.OnTouchListener() {
                 
                 public boolean onTouch(View myView, MotionEvent event) 
                 {
                 	if(ModeChoice.Close)
                 	{
                 		((ViewGroup)ModeChoice.getParent()).removeView(ModeChoice);
                 		ModeChoice.Close = false;
                 		
                 		if(ModeChoice.ChooseMPMode) NewConnexionView();
                 		else setContentView(ModeChoice); 
                 	}
                     return false;
                 }
             });    
    }
    
    public void NewConnexionView()
    {
    	 Connexion = new ConnexionView(this);
    	 setContentView(Connexion);
    	 ViewNo = 2;
    	 
    	 if(!DialogueConnexionCreate) 
		 {
			DialogueConnection();
			DialogueConnexionCreate = true;
		 }
		 
         Connexion.setOnTouchListener(
                 new View.OnTouchListener() {
                 
                 public boolean onTouch(View myView, MotionEvent event) 
                 {
                 	if(Connexion.TextEdit.Send)
                 	{
                 		((ViewGroup)Connexion.getParent()).removeView(Connexion);
                 		
                 		Jeu.ServerQuestion(false,Connexion.TextEdit.Text, m_CharacterChoice);
 						setContentView(Jeu);
 						ViewNo = 3;
                 	}
                 	
                    return false;
                 }
             }); 
    }
    
    public void NewGameView()
    {
		Jeu = new GameView(this);
		
		Jeu.setOnTouchListener(
             new View.OnTouchListener() {
             
             public boolean onTouch(View myView, MotionEvent event) 
             {
             	if(Jeu.Client != null)
             	{
             		if(Jeu.Client.ConnectFailed)
             		{
             			Connexion.Keyboard.Enabled = false;
 						Connexion.TextEdit.Enabled = false;
 						Jeu.Client.ConnectFailed = false; 
 						setContentView(Connexion);
 						ViewNo = 2;
             		}
             	}
             
                 return false;
             }
         });
    }
    @Override
    public void onBackPressed() {
      
    	//Confirmation message before to quit a menu
       	AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainContext);
       		String Title = "";
       		       		
       		switch(ViewNo)
       		{
       		case 0:
       			Title = "Would you QUIT the game";
       			break;
       		case 1:
       			Title = "Would you return to INTRO menu";
       			break;
       		case 2:
       			Title = "Would you return to MODE CHOICE menu";
       			break;
       		case 3:
       			Title = "Would you return to CONNEXION menu";
       			break;
       		}
       		
       		CharSequence cTitle = Title;
       		
       			alertDialogBuilder.setTitle(cTitle);
			
       			alertDialogBuilder
       				.setMessage("Are you sure ??") 
					.setCancelable(false)
					.setPositiveButton("Yes",new DialogInterface.OnClickListener() 
					{
						public void onClick(DialogInterface dialog,int id) 
						{
							switch(ViewNo)
							{
							case 0: 
								
								if(Jeu != null)
									if(Jeu.Server != null)
										Jeu.Server.Close();
									MainActivity.this.finish();
								break;
							case 1: 
								((ViewGroup)ModeChoice.getParent()).removeView(ModeChoice);
		                       NewCharacterChoiceView();
								break;
							case 2:
							    ((ViewGroup)Connexion.getParent()).removeView(Connexion);
								DialogueConnexionCreate = false; 
								ConnexionSend = false;
								NewModeSPMPView();
								break;
							case 3:
								DialogueConnection();
								Connexion.Keyboard.Enabled = true;
								Connexion.TextEdit.Enabled = true;
		 						setContentView(Connexion);
		 						ViewNo = 2;
							}
	                      
						}
					})
					.setNegativeButton("No",new DialogInterface.OnClickListener() 
					{
						public void onClick(DialogInterface dialog,int id) 
						{
							dialog.cancel();
						}
					});
       		
      
				
				//Creation du dialogued'alerte avec le builder
				AlertDialog alertDialog = alertDialogBuilder.create();
				alertDialog.show();
       
    }
}


