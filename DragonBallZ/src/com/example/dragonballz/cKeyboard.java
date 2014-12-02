package com.example.dragonballz;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;

public class cKeyboard 
{
	private cButton b0,b1,b2,b3,b4,b5,b6,b7,b8,b9,bPoint,bOk,bBack;
	private Bitmap Background;
	private Vector2D BackgroundPosition;
	public String TexteAssocie;
	public boolean Enabled;
	
	public cKeyboard(Context context, int ScreenWidth, int ScreenHeight)
	{
		Background = BitmapFactory.decodeResource(context.getResources(), R.drawable.backgroundkeyboard);
		Background = Bitmap.createScaledBitmap(Background, ScreenWidth, ScreenHeight/3 , false);
		BackgroundPosition = new Vector2D(0, (ScreenHeight/3)*2);
		
		int ButtonWidth = Background.getWidth()/6;
		int ButtonHeight = Background.getHeight()/4;
		TexteAssocie = "";
		
		b1 = new cButton(context,
				R.drawable.keyboardb_1,
				(int)(BackgroundPosition.X + (ButtonWidth * 1))
				,(int)BackgroundPosition.Y
				,ButtonWidth
				,ButtonHeight);
		
		b2 = new cButton(context,
				R.drawable.keyboardb_2,
				(int)(BackgroundPosition.X + (ButtonWidth * 2))
				,(int)BackgroundPosition.Y
				,ButtonWidth
				,ButtonHeight);
		
		b3 = new cButton(context,
				R.drawable.keyboardb_3,
				(int)(BackgroundPosition.X + (ButtonWidth * 3))
				,(int)BackgroundPosition.Y
				,ButtonWidth
				,ButtonHeight);
		
		b4 = new cButton(context,
				R.drawable.keyboardb_4,
				(int)(BackgroundPosition.X + (ButtonWidth * 1))
				,(int)(BackgroundPosition.Y + (ButtonHeight *1))
				,ButtonWidth
				,ButtonHeight);
		
		b5 = new cButton(context,
				R.drawable.keyboardb_5,
				(int)(BackgroundPosition.X + (ButtonWidth * 2))
				,(int)(BackgroundPosition.Y + (ButtonHeight *1))
				,ButtonWidth
				,ButtonHeight);
		
		
		b6 = new cButton(context,
				R.drawable.keyboardb_6,
				(int)(BackgroundPosition.X + (ButtonWidth * 3))
				,(int)(BackgroundPosition.Y + (ButtonHeight *1))
				,ButtonWidth
				,ButtonHeight);
		
		bPoint = new cButton(context,
				R.drawable.keyboardb_point,
				(int)(BackgroundPosition.X + (ButtonWidth * 4))
				,(int)(BackgroundPosition.Y + (ButtonHeight *1))
				,ButtonWidth
				,ButtonHeight);
		
		b7 = new cButton(context,
				R.drawable.keyboardb_7,
				(int)(BackgroundPosition.X + (ButtonWidth * 1))
				,(int)(BackgroundPosition.Y + (ButtonHeight *2))
				,ButtonWidth
				,ButtonHeight);
		
		b8 = new cButton(context,
				R.drawable.keyboardb_8,
				(int)(BackgroundPosition.X + (ButtonWidth * 2))
				,(int)(BackgroundPosition.Y + (ButtonHeight *2))
				,ButtonWidth
				,ButtonHeight);


		b9 = new cButton(context,
				R.drawable.keyboardb_9,
				(int)(BackgroundPosition.X + (ButtonWidth * 3))
				,(int)(BackgroundPosition.Y + (ButtonHeight *2))
				,ButtonWidth
				,ButtonHeight);
		
		b0 = new cButton(context,
				R.drawable.keyboardb_0,
				(int)(BackgroundPosition.X + (ButtonWidth * 2))
				,(int)(BackgroundPosition.Y + (ButtonHeight *3))
				,ButtonWidth
				,ButtonHeight);
		
		bOk = new cButton(context,
				R.drawable.keyboardb_ok,
				(int)(BackgroundPosition.X + (ButtonWidth * 4))
				,(int)(BackgroundPosition.Y + (ButtonHeight *3))
				,ButtonWidth
				,ButtonHeight);	
		
		bBack = new cButton(context,
				R.drawable.keyboardb_back,
				(int)(BackgroundPosition.X + (ButtonWidth * 4))
				,(int)(BackgroundPosition.Y + (ButtonHeight *2))
				,ButtonWidth
				,ButtonHeight);	
		
	}
	
	public void Update(Context context, MotionEvent ev)
	{
		if(!Enabled)
		{
		b0.Update(context, ev);
		b1.Update(context, ev);
		b2.Update(context, ev);
		b3.Update(context, ev);
		b4.Update(context, ev);
		b5.Update(context, ev);
		b6.Update(context, ev);
		b7.Update(context, ev);
		b8.Update(context, ev);
		b9.Update(context, ev);
		bOk.Update(context, ev);
		bPoint.Update(context, ev);
		bBack.Update(context, ev);
		
		if(b0.IsClicked) { b0.IsClicked = false; TexteAssocie = "0";}
		if(b1.IsClicked) { b1.IsClicked = false; TexteAssocie = "1";}
		if(b2.IsClicked) { b2.IsClicked = false; TexteAssocie = "2";}
		if(b3.IsClicked) { b3.IsClicked = false; TexteAssocie = "3";}
		if(b4.IsClicked) { b4.IsClicked = false; TexteAssocie = "4";}
		if(b5.IsClicked) { b5.IsClicked = false; TexteAssocie = "5";}
		if(b6.IsClicked) { b6.IsClicked = false; TexteAssocie = "6";}
		if(b7.IsClicked) { b7.IsClicked = false; TexteAssocie = "7";}
		if(b8.IsClicked) { b8.IsClicked = false; TexteAssocie = "8";}
		if(b9.IsClicked) { b9.IsClicked = false; TexteAssocie = "9";}
		if(bOk.IsClicked) { bOk.IsClicked = false; TexteAssocie = "OK"; }
		if(bPoint.IsClicked) { bPoint.IsClicked = false; TexteAssocie = ".";}
		if(bBack.IsClicked) { bBack.IsClicked = false; TexteAssocie = "<-";}
		
		ev.setAction(3);
		}
	}
	
	public void draw(Canvas canvas)
	{
		if(!Enabled)
		{
		canvas.drawBitmap(Background, BackgroundPosition.X, BackgroundPosition.Y, null);
		b0.draw(canvas);
		b1.draw(canvas);
		b2.draw(canvas);
		b3.draw(canvas);
		b4.draw(canvas);
		b5.draw(canvas);
		b6.draw(canvas);
		b7.draw(canvas);
		b8.draw(canvas);
		b9.draw(canvas);
		bPoint.draw(canvas);
		bOk.draw(canvas);
		bBack.draw(canvas);
		}
	}
}
