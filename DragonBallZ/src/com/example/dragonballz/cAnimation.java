package com.example.dragonballz;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;

public class cAnimation
{
    private int FrameIndex = 0;
	private boolean IsLooping,isStopped,OwnSpeed;
	public boolean isFinish=false,NextIndex;
    public List<Bitmap> ListeImage = new ArrayList<Bitmap>();
    public Vector2D Position;
    private float FrameSpeed,SpeedMax = 1,CptSpeed;
    public boolean HorizontalFlip;
    
    public int frameIndex()
    {
      return FrameIndex;	
    }
    
    public boolean IsStopped()
    {                    
        return isStopped;
    }
	
    public void Stop()
    {
    	isStopped=true;
    }
    
    public void Continue()
    {
    	isStopped=false;
    }
    
    public void Restart()
    {
    	FrameIndex=0;
    }
    
    public cAnimation(boolean IsLooping,float FrameSpeed) 
    {
    	this.IsLooping = IsLooping;
    	this.FrameSpeed = FrameSpeed;
    	this.Position = new Vector2D();
    	CptSpeed = 0;
    	
    	if(FrameSpeed == 0) OwnSpeed = true;
    	else OwnSpeed = false;
	}
    
    public cAnimation(boolean IsLooping,Vector2D Position,float FrameSpeed) 
    {
    	this.IsLooping = IsLooping;
    	this.Position = new Vector2D(Position.X, Position.Y);
    	this.FrameSpeed = FrameSpeed;
    	CptSpeed = 0;
    	
    	if(FrameSpeed == 0) OwnSpeed = true;
    	else OwnSpeed = false;
	}
    
    public cAnimation(cAnimation another)
    {
    	FrameIndex = another.FrameIndex;
    	IsLooping = another.IsLooping;
    	isStopped = another.isStopped;
    	OwnSpeed = another.OwnSpeed;
    	isFinish = another.isFinish;
    	NextIndex = another.NextIndex;
    	ListeImage = another.ListeImage;
    	Position = another.Position;
    	FrameSpeed = another.FrameSpeed;
    	SpeedMax = another.SpeedMax;
    	CptSpeed = another.CptSpeed;
    	HorizontalFlip = another.HorizontalFlip;
    }
    
    public void AddImage(Context context,int IdImage)
    {
    	Bitmap Image = BitmapFactory.decodeResource(context.getResources(),IdImage);
    	ListeImage.add(Image);
    }
    
    public void AddImage(Bitmap Image)
    {
    	Bitmap b = Image;
    	ListeImage.add(b);
    }
    
    public void Update()
    {
    	if(!isStopped)
    	{
    		if(!OwnSpeed)
    			CptSpeed += FrameSpeed;
    		else
    			if(NextIndex)
    				CptSpeed = SpeedMax;
    		
    		if(CptSpeed >= SpeedMax)
    		{
    			CptSpeed = 0;
    			NextIndex = false;
    			if(IsLooping)
    			{
    				try
    				{
    					//Parcours de lanimation
    					FrameIndex = (FrameIndex + 1) % (ListeImage.size()-1); // le modulo fais un looping
    				}
    				catch(Error e)
    				{
    					FrameIndex = 0; // en cas d'erreur de traitement retours à l'image 0
    				}
    			}
    			else FrameIndex = Math.min(FrameIndex + 1, ListeImage.size() - 1);
    		}
    	}
    	
    	if(FrameIndex == ListeImage.size() - 1) isFinish = true; 
    	else isFinish = false;
    }
    
    public void AdvanceIndex(int Advance)
    {
    	if(FrameIndex + Advance >= ListeImage.size() - 1) FrameIndex = ListeImage.size() - 1;
    	else if (FrameIndex < 0) FrameIndex = 0;
    	else 
    		FrameIndex += Advance;
    	
    }
    public void Draw(Canvas canvas)
    {
    	Matrix matrix = new Matrix();
    	if(HorizontalFlip)
    	{
    		matrix.setScale(-1,1);
    		matrix.postTranslate(ListeImage.get(FrameIndex).getWidth(),0);
    	}
    	matrix.postTranslate(Position.X, Position.Y); //X,Y
    	
    	 canvas.drawBitmap(ListeImage.get(FrameIndex), matrix, null);
    }
}
