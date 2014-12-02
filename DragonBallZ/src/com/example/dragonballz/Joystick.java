package com.example.dragonballz;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceView;

public class Joystick extends SurfaceView
{
	private Bitmap BJoystickPad,BJoyStickS;
	private Rect RecJoystick;
	private Vector2D Position,PositionStick,PositionCenter;
	private Vector2D Deplacement;
	private int Speed;
	private Boolean Move = false;
	
	public Joystick(Context context)
	{
		super(context);
	}
	
	public Joystick(Context context,Vector2D Position)
	{
		super(context);
		BJoystickPad = BitmapFactory.decodeResource(getResources(), R.drawable.joystick);
		BJoyStickS = BitmapFactory.decodeResource(getResources(), R.drawable.joystickstick);
		RecJoystick = new Rect((int)Position.X, (int)Position.Y, BJoystickPad.getWidth(), BJoystickPad.getHeight());
		this.Position = new Vector2D();
		PositionCenter = new Vector2D(RecJoystick.left + BJoystickPad.getWidth()/2 - 11, RecJoystick.top + BJoystickPad.getHeight()/2 - 15);
		PositionStick = new Vector2D(PositionCenter.X, PositionCenter.Y);
		Speed = 4;
		Deplacement = new Vector2D();
	}
	
	public void Update(MotionEvent ev, Personnage player)
	{
		Position = new Vector2D((int)ev.getX(), (int)ev.getY());
		ReplaceIn(player);
			switch(ev.getAction())
			{
				case MotionEvent.ACTION_MOVE:
				{
					PositionStick = new Vector2D(Position.X,Position.Y);
					CheckDeplacement();
					break;
				}
				case MotionEvent.ACTION_UP:
				{
					Move = false;
				}
			}
			
			if(!Move) PositionStick = PositionCenter; 
	}
	
	public void UpdateMotion(Personnage player)
	{
		if(Move)
		{
			if(player.AnimationEnCours != player.A_ShootBigKam)
			{
				player.Commandes.ResetAllButtonTexture(player);
				player.ACTION_Guard = false;
				player.ACTION_Recharge = false;
				player.ACTION_Hit = false;
				player.ACTION_Shoot = false;
				player.ACTION_SuperShoot = false;
				player.Position.Plus(Deplacement);
				player.ACTION_Move = true;
				player.Deplacement = true;
				player.Speed = Deplacement;
				player.ADD_Balle = false;
				player.ADD_Kamehameha = false;
				player.ADD_SuperKamehameha = false;
				player.HOLD_Timer = false;
			}
			
			CheckDeplacement();
		}
		else
		{
			player.ACTION_Move = false;
			player.Deplacement = false;
		}
	}
	
	public void draw(Canvas canvas)
	{
		canvas.drawBitmap(BJoystickPad, RecJoystick.left, RecJoystick.top, null);
		canvas.drawBitmap(BJoyStickS, PositionStick.X, PositionStick.Y, null);
	}
	
	private void CheckDeplacement()
	{
		if(Position.X > PositionCenter.X + 3) 	Deplacement.X = Speed;
		else if(Position.X < PositionCenter.X -3)  Deplacement.X = -Speed;
		else  Deplacement.X = 0;
			
		if(Position.Y > PositionCenter.Y + 3) 	Deplacement.Y = Speed;
		else if(Position.Y < PositionCenter.Y -3)  Deplacement.Y = -Speed;
		else Deplacement.Y = 0;
	}
	
	public void ReplaceIn(Personnage player)
	{
		float OffZone = 75;
		Move = true;
		if(Position.X < RecJoystick.left - OffZone)	ReCenter(player);
		else if (Position.X <= RecJoystick.left)   Position.X = RecJoystick.left;
		else if (Position.X > RecJoystick.left + RecJoystick.right + OffZone) ReCenter(player); 
		else if (Position.X > RecJoystick.left + RecJoystick.right -30) Position.X =  RecJoystick.left + RecJoystick.right -30;
	
		if(Position.Y < RecJoystick.top - OffZone)  ReCenter(player);
		else if(Position.Y <= RecJoystick.top) Position.Y = RecJoystick.top;
		else if (Position.Y > RecJoystick.top + RecJoystick.bottom + OffZone) ReCenter(player); 
		else if (Position.Y > RecJoystick.top + RecJoystick.bottom -30) Position.Y = RecJoystick.top + RecJoystick.bottom -30;
	}
	
	private void ReCenter(Personnage player)
	{
		Position = PositionCenter;
		Move = false;
		player.ACTION_Move = false;
		player.Deplacement = false;
	}
}
