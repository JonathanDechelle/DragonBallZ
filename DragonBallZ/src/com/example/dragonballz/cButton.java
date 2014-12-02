package com.example.dragonballz;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceView;

/***
 * 
 * @author Joyvax
 * <p>Classe permettant De creer nos propres boutons avec un click listener pour les evenemnt </p>
 * 
 * <dd><b> Fonction </b><dd>
 * <dl> SetImage,Draw,Update</dl>
 * 
 */

public class cButton extends SurfaceView
{
    private Bitmap  ImgButton;
	private Rect RecImage;
    private int PosX,PosY;
    boolean IsClicked=false,IsIn,AutomicOff;
    private int IDImage, Height,Width;
    int CptOff;
    
    public int PosX(){ return PosX; }
    public int PosY(){ return PosY; }
  
    public int idImage()
    {
    	return IDImage;
    }
    
  
    public cButton(Context context)
    {
    	super(context);
    }
    
	public cButton(Context context,int IDImage,int PosX,int PosY,int Width,int Height)
	{
		super(context);
		this.IDImage = IDImage;
		ImgButton = BitmapFactory.decodeResource(getResources(),IDImage); //Load a image for the button.
		ImgButton = Bitmap.createScaledBitmap(ImgButton,Width,Height,false); //Scale background to fit with button
		
		this.PosX=PosX;
		this.PosY=PosY;
		
		RecImage = new Rect(PosX,PosY,PosX+Width,PosY+Height);
		this.Height = Height;
		this.Width = Width;
	}
	
	public void SetPosition(int X, int Y)
	{
		this.PosX=X;
		this.PosY=Y;
		
		RecImage = new Rect(PosX,PosY,PosX+Width,PosY+Height);
	}
	
	public void SetImage(int IDImage)
	{
		ImgButton = BitmapFactory.decodeResource(getResources(),IDImage);
		ImgButton = Bitmap.createScaledBitmap(ImgButton,Width,Height,false);
		this.IDImage = IDImage;
	}
	
	public void Update(Context context,MotionEvent ev)
	{
		 switch (ev.getAction()) 
		 {
         	case MotionEvent.ACTION_DOWN: 
         	{
         		if (RecImage.contains((int)ev.getX(),(int)ev.getY()))
         		{
         			if(IsClicked) IsClicked=false;
         			else IsClicked = true;
         			IsIn=true;
           		}	
        	 
         		break;
         	}
         	case MotionEvent.ACTION_UP:
         		IsIn=false;
  		
         		break;
		 }
	}
	
	@Override
	public void draw(Canvas canvas)
	{
		canvas.drawBitmap(ImgButton,PosX,PosY, null);
		
		  if(AutomicOff) 
	      {
			  AutomaticOff(4);
	      }
	}
	
	public void AutomaticOff(int Time)
	{
		if(AutomicOff)
		{
			CptOff++;
			if(CptOff >= Time)
			{
				CptOff = 0;
				AutomicOff = false;
			}
		}
	}
	
		
	
	
}
