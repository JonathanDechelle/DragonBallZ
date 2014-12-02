package com.example.dragonballz;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.text.TextPaint;

public class EditText 
{
	private Vector2D Position,PositionTexte;
	private Bitmap Texture;
	private TextPaint TextPaint;
	public String Text;
	public boolean Enabled,Send,Back;
	
	public EditText(Context context, int ScreenWidth, int ScreenHeight)
	{
		Texture = BitmapFactory.decodeResource(context.getResources(), R.drawable.edit_text_1);
		Texture = Bitmap.createScaledBitmap(Texture, ScreenWidth, ScreenHeight/4, false);
		
		Position = new Vector2D(0, 0);
		PositionTexte = new Vector2D(Position.X + 30,Position.Y + 140);
		
		TextPaint = new TextPaint();
		TextPaint.setColor(Color.BLUE);
		TextPaint.setTextSize(150);
		Text = "";
	}
	
	public void Update(cKeyboard Keyboard)
	{
		if(!Enabled)
		{
			if(Keyboard.TexteAssocie != "")
			{
				if(Keyboard.TexteAssocie == "OK")
				{
					Send = true;
				}
				else if(Keyboard.TexteAssocie != "<-")
				{
					Text = Text.concat(Keyboard.TexteAssocie);
				}
				else
				{
					Back = true;
					String NewTexteApparent = "";
					
					for(int i=0 ; i<Text.length() - 1; i++)
					{
						NewTexteApparent = NewTexteApparent.concat(Character.toString(Text.charAt(i)));
					}
					
					Text = NewTexteApparent;
				}
				Keyboard.TexteAssocie = "";
			}
		}
	}
	
	public void draw(Canvas canvas)
	{
		if(!Enabled){
		canvas.drawBitmap(Texture, Position.X, Position.Y, null);
		canvas.drawText(Text, PositionTexte.X , PositionTexte.Y , TextPaint);
		}
	}
}
