package com.example.dragonballz;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Looper;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

class GameView extends SurfaceView implements SurfaceHolder.Callback {
    GameThread thread;
  
    int Screen_Width,Screen_Height;
    Context context;
    Personnage Player1;
    Personnage Player2;
    Bitmap Background;

    
    ClientJSON Client;
    ServerJSON Server;
    boolean IsServer;
    Joystick joystick;
    
    CollisionSystem CSysteme;
    
    public GameView(Context context) 
    {
        super(context);
        this.context = context;
        //Set thread
        getHolder().addCallback(this);
        setFocusable(true); 
        Player1 = new Personnage(context,new Vector2D(200, 300),1);
        Player2 = new Personnage(context,new Vector2D(500, 300),2);
        Background = BitmapFactory.decodeResource(getResources(), R.drawable.niv1);
        joystick = new Joystick(context, new Vector2D(200, 550));
    } 
    
    public void ServerQuestion(boolean IsServer, String ip, int NoPersonnage)
    {
    	this.IsServer = IsServer;
    	
    	  if(!IsServer)
    	  {
    	      Client = new ClientJSON(Player2,ip);
    	      Server = null;
    	      Player1.IsEnnemy = true;
    	      Player1.SetAnimation(1);
        	  Player2.SetAnimation(NoPersonnage);
        	  CSysteme = new CollisionSystem(context, Player2.Position);  	  
    	  }
    	  else 
    	  {
    	      Server = new ServerJSON(Player2);
    	      Client = null;
    	      Player2.IsEnnemy = true;
    	      Player1.SetAnimation(NoPersonnage);
        	  Player2.SetAnimation(1);
        	  CSysteme = new CollisionSystem(context, Player1.Position);  	  
    	  }     
    	  
    	 
    }

    @Override
    public void onSizeChanged (int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //This event-method provides the real dimensions of this custom view.
        Screen_Height = h;
        Screen_Width = w;
        Background = Bitmap.createScaledBitmap(Background, w, h, true);
    }

    //***************************************
    //				  TOUCH  
    //***************************************
 
    public synchronized boolean onTouchEvent(MotionEvent ev) 
    {
    	if(Player2.IsEnnemy)
    	{
    		Player1.UpdateMotion(ev);
    		joystick.Update(ev,Player1);
    	}
    	
    	else
    	{
    		Player2.UpdateMotion(ev);
    		joystick.Update(ev,Player2);
    	}
    	
        return true;
    }

    @Override
    public void draw(Canvas canvas) 
    {
        super.draw(canvas);  
      
        canvas.drawBitmap(Background, 0, 0,null);
        if(Player2.IsEnnemy) joystick.UpdateMotion(Player1);
        else joystick.UpdateMotion(Player2);
        Player1.UpdateLogic();
        Player1.draw(canvas);
        Player1.GestionShootingBalls(canvas, Screen_Width);
        Player2.UpdateLogic();
        Player2.draw(canvas);
        Player2.GestionShootingBalls(canvas, Screen_Width);
        
        if(!IsServer)
        {
	        Client.Connect();
	        Client.Update(Player2);
	        Client.draw(canvas);
	        Client.Envoi();
        }
        else
        {
        	Personnage p = Server.Recoit();
        	if(p != null) Player2 = p;
        }
        
        CSysteme.Update(Player1, Player2);
        //CSysteme.Draw(canvas);
        
        joystick.draw(canvas);
    }
    
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) 
    {
    	
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) 
    {
        thread = new GameThread(getHolder(), this);
        thread.setRunning(true);
        thread.start();
    }
    
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) 
    {
        boolean retry = true;
        thread.setRunning(false);
   
        while (retry)
        {
            try 
            {
                thread.join();
                retry = false;
            } 
            
            catch (InterruptedException e) {     }
        }
    }


    class GameThread extends Thread 
    {
        private SurfaceHolder surfaceHolder;
        private GameView gameView;
        private boolean run = false;

        public GameThread(SurfaceHolder surfaceHolder, GameView gameView) 
        {
            this.surfaceHolder = surfaceHolder;
            this.gameView = gameView;
        }

        public void setRunning(boolean run) 
        {
            this.run = run;
        }

        public SurfaceHolder getSurfaceHolder() 
        {
            return surfaceHolder;
        }
        
        
        @Override
        public void run() 
        {
        	Looper.prepare();
            Canvas c;
            while (run) 
            {
                c = null;
                try 
                {
                	c = surfaceHolder.lockCanvas(null);
                    synchronized (surfaceHolder)
                    {
                    	if(c!=null)
                        gameView.draw(c);
                    }
                } 
                finally 
                {
                    if (c != null) {surfaceHolder.unlockCanvasAndPost(c);}
                }
            }                 
        }
    }
}