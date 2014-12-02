package com.example.dragonballz;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Looper;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

class ConnexionView extends SurfaceView implements SurfaceHolder.Callback {
    private GameThread thread;
  
    public Context context;

    public cKeyboard Keyboard;
    public EditText TextEdit;
    
    private Bitmap Background;
  
    public ConnexionView(Context context) 
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
        Keyboard = new cKeyboard(context, w, h);
        Keyboard.Enabled = true;
        TextEdit = new EditText(context, w, h);
        TextEdit.Enabled = true;
        Background = Bitmap.createScaledBitmap(Background, w, h, false);
    }

    //***************************************
    //				  TOUCH  
    //***************************************
 
    public synchronized boolean onTouchEvent(MotionEvent ev) 
    {
    	Keyboard.Update(context, ev);
    	if(TextEdit.Send)
    	{
    		 TextEdit.Send = false;
    	}
        return true;
    }
    
    @Override
    public void draw(Canvas canvas) 
    {
        super.draw(canvas);  
        canvas.drawBitmap(Background, 0, 0, null);
        Keyboard.draw(canvas);
        TextEdit.Update(Keyboard);
        TextEdit.draw(canvas);
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
        private ConnexionView gameView;
        private boolean run = false;

        public GameThread(SurfaceHolder surfaceHolder, ConnexionView gameView) 
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