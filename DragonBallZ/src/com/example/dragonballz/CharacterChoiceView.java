package com.example.dragonballz;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Looper;
import android.text.TextPaint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

class CharacterChoiceView extends SurfaceView implements SurfaceHolder.Callback {
    private GameThread thread;
  
    public Context context;
    public boolean Close;
    private Bitmap Background,NomPerso;
    private cButton SelectorGoku,SelectorKrillin,SelectorPiccolo;
    private cButton BtnCharChoice;
    private cAnimation AnimationEnCours,Goku_Idle,Krillin_Idle,Piccolo_Idle;
    public int ChoixCharactere;
	private TextPaint ErrorTextPaint;
	private String ErrorText;

    public CharacterChoiceView(Context context) 
    {
        super(context);
        this.context = context;
        //Set thread
        getHolder().addCallback(this);
        setFocusable(true);
      
        Background = BitmapFactory.decodeResource(context.getResources(), R.drawable.img_main);
        NomPerso = BitmapFactory.decodeResource(context.getResources(), R.drawable.gokubarre);
        SelectorGoku = new cButton(context, R.drawable.portrait_goku_2, 0, 0, 80, 112);
        SelectorKrillin = new cButton(context, R.drawable.portrait_krillin_1, 90, 0, 80, 112);
        SelectorPiccolo = new cButton(context, R.drawable.portrait_piccolo_1, 180, 0, 80, 112);
        Goku_Idle = new cAnimation(RessourceAnimation.Goku_Idle);
        Krillin_Idle = new cAnimation(RessourceAnimation.Krillin_Idle);
        Piccolo_Idle = new cAnimation(RessourceAnimation.Piccolo_Idle);
        Goku_Idle.Position = new Vector2D(275,150);
        Krillin_Idle.Position = new Vector2D(280,175);
        Piccolo_Idle.Position = new Vector2D(280,175);
        ChoixCharactere = 1;
        
        ErrorTextPaint = new TextPaint();
		ErrorTextPaint.setColor(Color.RED);
		ErrorTextPaint.setTextSize(40);
		ErrorText = "";
		Close = false;
    } 
    
    @Override
    public void onSizeChanged (int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Background = Bitmap.createScaledBitmap(Background, w, h, false);
        BtnCharChoice = new cButton(context, R.drawable.buttoncharacterchoice, w/8, h/2 + h/5, w/3, h/5);
    }

    //***************************************
    //				  TOUCH  
    //***************************************
 
    public synchronized boolean onTouchEvent(MotionEvent ev) 
    {
    	SelectorGoku.Update(context, ev);
    	SelectorKrillin.Update(context, ev);
    	SelectorPiccolo.Update(context, ev);
    	BtnCharChoice.Update(context, ev);
        return true;
    }
    
    @Override
    public void draw(Canvas canvas) 
    {
        super.draw(canvas);  
        
        if(SelectorGoku.IsClicked)
        {
        	SelectorGoku.IsClicked = false;
        	if(SelectorGoku.idImage() == R.drawable.portrait_goku_1)
        	{
        		SelectorGoku.SetImage(R.drawable.portrait_goku_2);
        		SelectorKrillin.SetImage(R.drawable.portrait_krillin_1);
        		SelectorPiccolo.SetImage(R.drawable.portrait_piccolo_1);
        		ChoixCharactere = 1;
        	}
        	else
        	{
        		SelectorGoku.SetImage(R.drawable.portrait_goku_1);
        		ChoixCharactere = 0;
        	}
        }
        
        if(SelectorKrillin.IsClicked)
        {
        	SelectorKrillin.IsClicked = false;
        	if(SelectorKrillin.idImage() == R.drawable.portrait_krillin_1)
        	{
        		SelectorKrillin.SetImage(R.drawable.portrait_krillin_2);
        		SelectorGoku.SetImage(R.drawable.portrait_goku_1);
        		SelectorPiccolo.SetImage(R.drawable.portrait_piccolo_1);
        		ChoixCharactere = 2;
        	}
        	else
        	{
        		SelectorKrillin.SetImage(R.drawable.portrait_krillin_1);
        		ChoixCharactere = 0;
        	}
        }
        
        if(SelectorPiccolo.IsClicked)
        {
        	SelectorPiccolo.IsClicked = false;
        	if(SelectorPiccolo.idImage() == R.drawable.portrait_piccolo_1)
        	{
        		SelectorPiccolo.SetImage(R.drawable.portrait_piccolo_2);
        		SelectorKrillin.SetImage(R.drawable.portrait_krillin_1);
        		SelectorGoku.SetImage(R.drawable.portrait_goku_1);
        		ChoixCharactere = 3;
        	}
        	else
        	{
        		SelectorPiccolo.SetImage(R.drawable.portrait_piccolo_1);
        		ChoixCharactere = 0;
        	}
        }
        
        if(BtnCharChoice.IsClicked)
        {
        	BtnCharChoice.IsClicked = false;
        	if(ChoixCharactere != 0)
        		Close = true;
        }
 
        canvas.drawBitmap(Background, 0, 0, null);
        SelectorGoku.draw(canvas);
        SelectorKrillin.draw(canvas);
        SelectorPiccolo.draw(canvas);
        BtnCharChoice.draw(canvas);
        
        if(ChoixCharactere != 0) ErrorText = "";
        else
    		ErrorText = "Please choose a character";
        	
       switch (ChoixCharactere) 
       {
       case 0:
    	   NomPerso = null;
    	break;
       	case 1: AnimationEnCours = Goku_Idle;
       	NomPerso = BitmapFactory.decodeResource(context.getResources(), R.drawable.gokubarre);
		break;
		
       	case 2: AnimationEnCours = Krillin_Idle;
       	NomPerso = BitmapFactory.decodeResource(context.getResources(), R.drawable.krillinbarre);
		break;
		
       	case 3: AnimationEnCours = Piccolo_Idle;
       	NomPerso = BitmapFactory.decodeResource(context.getResources(), R.drawable.piccolobarre);
       	break; 
       }
       
       if(ChoixCharactere != 0)
       {
    	   if(NomPerso != null)
    		   canvas.drawBitmap(NomPerso, 125, 180, null);
    	
    	   AnimationEnCours.Update();
    	   AnimationEnCours.Draw(canvas);
       }
       
       canvas.drawText(ErrorText, 100, 250, ErrorTextPaint);
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
        private CharacterChoiceView gameView;
        private boolean run = false;

        public GameThread(SurfaceHolder surfaceHolder, CharacterChoiceView gameView) 
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