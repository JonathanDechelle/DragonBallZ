package com.example.dragonballz;

import java.util.Iterator;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

public class CollisionSystem 
{
	private Rect RecCollision,RecCollisionOpponent,RecBalle;
	private Bitmap BitmapOK,BitmapCollision;
	private boolean Collide;
	private double FactorSize;
	
	public CollisionSystem(Context context,Vector2D Position)
	{
		RecCollision = new Rect((int)Position.X, (int)Position.Y, 65,50);
		BitmapOK = BitmapFactory.decodeResource(context.getResources(), R.drawable.backgroundkeyboard);
		BitmapOK = Bitmap.createScaledBitmap(BitmapOK,RecCollision.right,RecCollision.bottom,false);
		BitmapCollision = BitmapFactory.decodeResource(context.getResources(), R.drawable.touched);
		BitmapCollision = Bitmap.createScaledBitmap(BitmapCollision,RecCollision.right,RecCollision.bottom,false);
		RecCollisionOpponent = new Rect();
		RecCollisionOpponent.left = RecCollision.left;
		RecCollisionOpponent.top = RecCollision.top;
		RecCollisionOpponent.right = RecCollision.right;
		RecCollisionOpponent.bottom = RecCollision.bottom;
		RecBalle = new Rect((int)Position.X, (int)Position.Y, 30, 30);
		Collide = false;
	}

	public void Update(Personnage Player1,Personnage Player2)
	{
		//Positioning
		RecCollision.left = (int)Player1.Position.X;
		RecCollision.top = (int)Player1.Position.Y;
		RecCollisionOpponent.left = (int)Player2.Position.X;
		RecCollisionOpponent.top = (int)Player2.Position.Y;
		
		//Resizing
		RecCollision.right = 65;
		if(Player1.NoCharacter == 2) { RecCollision.right = 75; RecCollision.bottom = 60; RecCollision.top -= 20;}
		if(Player2.NoCharacter == 2) { RecCollisionOpponent.right = 75; RecCollisionOpponent.bottom = 60; RecCollisionOpponent.top -= 20;}
		
			//Hit animation resize rectangle of the collision for frame 2,3,4,5
			if(Player1.ACTION_Hit && Player1.AnimationEnCours == Player1.A_Hit)
			{
				if(
					Player1.AnimationEnCours.frameIndex() == 2 ||
					Player1.AnimationEnCours.frameIndex() == 3 ||
				    Player1.AnimationEnCours.frameIndex() == 4 ||
				    Player1.AnimationEnCours.frameIndex() == 5)
					{
						if(Player2.NoCharacter != 2)  RecCollision.right = 82;
						else RecCollision.right = 92;
					}
			}
			
			if(Player1.NoCharacter != 2) FactorSize = RecCollision.right * 0.5;
			else FactorSize = RecCollision.right * 0.25;
			
			if(RecCollision.left + RecCollision.right < RecCollisionOpponent.left  + RecCollisionOpponent.right + FactorSize)
			{
				int Damage = 1;
				if(Intesection(RecCollision,RecCollisionOpponent))
				{
					Collide = true;
					
					if(Player1.ClickAttack && Player1.ACTION_Hit)
					{
						HurtingTheOther(Player1, Player2, Damage);
					}
				}
				else
				{
					for (Iterator<Ball> ListeBalle = Player1.ListBalls.iterator(); ListeBalle.hasNext(); ) 
			    	{
						Ball balle = ListeBalle.next();
					
						RecBalle.left = (int) (balle.Position.X + balle.Speed.X);
						RecBalle.top = (int) (balle.Position.Y + balle.Speed.Y);
						
						if(balle.Sorte == 1)
						{
							RecBalle.left += 200;
							Damage = 5;
						}
						else if(balle.Sorte == 2) Damage = 15;
			    		
						if(Intesection(RecCollisionOpponent,RecBalle))
						{
							Collide = true;
							HurtingTheOther(Player1, Player2, Damage);
							ListeBalle.remove();
							RecBalle = new Rect((int)Player1.Position.X, (int)Player1.Position.Y, 30, 30);
						}
			    	}
				
					if(Collide) Collide = false;
				}
				
					
			}
			else Collide = false;
	}
	
	public void Draw(Canvas canvas)
	{
		if(Collide)
		{
			canvas.drawBitmap(BitmapCollision, RecCollision.left, RecCollision.top, null);
			canvas.drawBitmap(BitmapCollision, RecCollisionOpponent.left, RecCollisionOpponent.top, null);
		}
		else
		{
			canvas.drawBitmap(BitmapOK, RecCollision.left, RecCollision.top, null);
			canvas.drawBitmap(BitmapOK, RecCollisionOpponent.left, RecCollisionOpponent.top, null);
			canvas.drawBitmap(BitmapOK, RecBalle.left, RecBalle.top, null);
		}
	}
	
	private void HurtingTheOther(Personnage Player1, Personnage Player2, int Damage)
	{
		Player2.LifeBar.AnimationEnCours.AdvanceIndex(Damage);
		Player2.LifeBar.AnimationEnCours.NextIndex = true;
		Player1.ClickAttack = false;
		
		if(Player2.ACTION_Hit) Player2.ACTION_Hit = false;
		else if(Player2.ACTION_Move) Player2.ACTION_Move = false;
		else if(Player2.ACTION_Recharge) Player2.ACTION_Recharge = false;
		else if(Player2.ACTION_Shoot) Player2.ACTION_Shoot = false;
		else if(Player2.ACTION_SuperShoot) Player2.ACTION_SuperShoot = false;
		
		Player2.ACTION_Hurt = true;
		Player2.AnimationEnCours = Player2.A_Hurt;
		Player2.cptTimeInactiveHit = 0;
	}
	
	private boolean Intesection(Rect RectangleA, Rect RectangleB)
	{ 
		//		   000000
		//	A------0--  0 B
		//	 -     0 -	0
		//   -     0 -  0 
		//   -     0 -  0
		//   ------0--  0
		//         000000

		//Rectangle A
		int RightA = RectangleB.left + RectangleB.right;
		int BottomA = RectangleB.top + RectangleB.bottom;
		int LeftA = RectangleB.left;
		int TopA = RectangleB.top;

		//Rectangle B
		int RightB = RectangleA.left + RectangleA.right;
		int BottomB = RectangleA.top + RectangleA.bottom;
		int LeftB = RectangleA.left;
		int TopB = RectangleA.top;

		//Regarde la collision4
		if(LeftA <= RightB &&
			RightA >= LeftB  &&
			TopA <= BottomB &&
			BottomA >= TopB)
			return true;
		else
			return false;
	};
}
