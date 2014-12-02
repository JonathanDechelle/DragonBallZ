package com.example.dragonballz;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Looper;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

class ModeSPMPView extends SurfaceView implements SurfaceHolder.Callback {
    private GameThread thread;
  
    public Context context;
    public boolean Close,ChooseMPMode = false;
    private Bitmap Background;
    private cButton BtnModeChoice,SPMode,MPMode;
	
    public ModeSPMPView(Context context) 
    {
        super(context);
        this.context = context;
        //Set thread
        getHolder().addCallback(this);
        setFocusable(true);
       
        Background = BitmapFactory.decodeResource(context.getResources(), R.drawable.img_main);
    } 
    
    @Override
    public void onSizeChanged (int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Background = Bitmap.createScaledBitmap(Background, w, h, false);
        BtnModeChoice = new cButton(context, R.drawable.buttonmodechoice, w/8, h/2 + h/5, w/3, h/5);
        SPMode = new cButton(context, R.drawable.buttonsoloon, w/15, h/11, w/5, h/5);
        MPMode = new cButton(context, R.drawable.buttonmpoff, w/3, h/11, w/5, h/5);
    }

    //***************************************
    //				  TOUCH  
    //***************************************
 
    public synchronized boolean onTouchEvent(MotionEvent ev) 
    {
    	BtnModeChoice.Update(context, ev);
    	SPMode.Update(context, ev);
    	MPMode.Update(context, ev);
    	
    	if(SPMode.IsClicked)
    	{
    		if(SPMode.idImage() == R.drawable.buttonsoloon)
    		{
    			SPMode.SetImage(R.drawable.buttonsolooff);
    			MPMode.SetImage(R.drawable.buttonmpon);
    		}
    		else
    		{
    			SPMode.SetImage(R.drawable.buttonsoloon);
    			MPMode.SetImage(R.drawable.buttonmpoff);
    		}
    		SPMode.IsClicked = false;
    	}
    	
    	if(MPMode.IsClicked)
    	{
    		if(MPMode.idImage() == R.drawable.buttonmpon)
    		{
    			MPMode.SetImage(R.drawable.buttonmpoff);
    			SPMode.SetImage(R.drawable.buttonsoloon);
    		}
    		else
    		{
    			MPMode.SetImage(R.drawable.buttonmpon);
    			SPMode.SetImage(R.drawable.buttonsolooff);
    		}
    		MPMode.IsClicked = false;
    	}
    	
    	if(BtnModeChoice.IsClicked)
    	{
    		BtnModeChoice.IsClicked = false;
    		Close = true;
    		
    		if(MPMode.idImage() == R.drawable.buttonmpon)
    			ChooseMPMode = true;
    		else if(SPMode.idImage() == R.drawable.buttonsoloon)
    			ChooseMPMode = false;
    	}
        return true;
    }
    
    @Override
    public void draw(Canvas canvas) 
    {
        super.draw(canvas);  
        canvas.drawBitmap(Background, 0, 0, null);
        BtnModeChoice.draw(canvas);
        SPMode.draw(canvas);
        MPMode.draw(canvas);
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
        private ModeSPMPView gameView;
        private boolean run = false;

        public GameThread(SurfaceHolder surfaceHolder, ModeSPMPView gameView) 
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