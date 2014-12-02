package com.example.dragonballz;

import android.graphics.Canvas;

public class Ball 
{
	public Vector2D Position,Speed;
	private cAnimation Animation;
	
	public int Sorte;
	
	private boolean Horizontalflip = false;
	
	public void SetSpeed(int X,int Y)
	{
		Speed = new Vector2D(X, Y);
	}
	
	public Vector2D Position()
	{
		return Position;
	}
	
	public Ball(Personnage Player,int Sorte, int NoCharacter)
	{
		this.Sorte = Sorte;
		
		if(Player.NoPlayer == 2) Horizontalflip = true;
		
		switch(Sorte)
		{
		case 0: Animation =  new cAnimation(false, 0.05f);
		Animation.AddImage(Player.context, R.drawable.balle1_1);
		Animation.AddImage(Player.context, R.drawable.balle1_2);
		Animation.AddImage(Player.context, R.drawable.balle1_3);
		Animation.AddImage(Player.context, R.drawable.balle1_4);
		Animation.AddImage(Player.context, R.drawable.balle1_5);
		Animation.AddImage(Player.context, R.drawable.balle1_6);
		Animation.AddImage(Player.context, R.drawable.balle1_7);
		Animation.AddImage(Player.context, R.drawable.balle1_7);
		this.Position = new Vector2D(Player.Position.X + 60,Player.Position.Y + 50);
		
		if(NoCharacter != 1) this.Position.Y -= 15;
		
		if(Player.NoPlayer == 1) Speed = new Vector2D(5,0); 
		else 
		{
			Speed = new Vector2D(-5,0);
			this.Position.X -= 50;
		}
		
		
			break;
		case 1: Animation = new cAnimation(false, 0.5f);
		Animation.AddImage(Player.context, R.drawable.kamehameha1_1);
		Animation.AddImage(Player.context, R.drawable.kamehameha1_2);
		Animation.AddImage(Player.context, R.drawable.kamehameha1_3);
		Animation.AddImage(Player.context, R.drawable.kamehameha1_4);
		Animation.AddImage(Player.context, R.drawable.kamehameha1_5);
		Animation.AddImage(Player.context, R.drawable.kamehameha1_6);
		Animation.AddImage(Player.context, R.drawable.kamehameha1_7);
		Animation.AddImage(Player.context, R.drawable.kamehameha1_8);
		Animation.AddImage(Player.context, R.drawable.kamehameha1_9);
		Animation.AddImage(Player.context, R.drawable.kamehameha1_10);
		Animation.AddImage(Player.context, R.drawable.kamehameha1_11);
		Animation.AddImage(Player.context, R.drawable.kamehameha1_12);
		Animation.AddImage(Animation.ListeImage.get(10));
		Animation.AddImage(Animation.ListeImage.get(9));
		Animation.AddImage(Animation.ListeImage.get(8));
		Animation.AddImage(Animation.ListeImage.get(7));
		Animation.AddImage(Animation.ListeImage.get(6));
		Animation.AddImage(Animation.ListeImage.get(5));
		Animation.AddImage(Animation.ListeImage.get(4));
		Animation.AddImage(Animation.ListeImage.get(3));
		Animation.AddImage(Animation.ListeImage.get(2));
		Animation.AddImage(Animation.ListeImage.get(1));
		Animation.AddImage(Animation.ListeImage.get(0));
		this.Position = new Vector2D(Player.Position.X - 150,Player.Position.Y + 40);
		
		if(NoCharacter != 1) this.Position.Y -= 15;
		
		if(Player.NoPlayer == 1) Speed = new Vector2D(10,0); 
		else
		{
			Speed = new Vector2D(-10,0);
			this.Position.X += 90;
		}
		
		break;
		case 2: Animation = new cAnimation(false, 0.5f);
		if(NoCharacter != 3){
			Animation.AddImage(Player.context, R.drawable.bigkamehameha1_1);
			Animation.AddImage(Player.context, R.drawable.bigkamehameha1_2);
			Animation.AddImage(Player.context, R.drawable.bigkamehameha1_3);
			Animation.AddImage(Player.context, R.drawable.bigkamehameha1_4);
			Animation.AddImage(Player.context, R.drawable.bigkamehameha1_5);
			Animation.AddImage(Player.context, R.drawable.bigkamehameha1_6);
			Animation.AddImage(Player.context, R.drawable.bigkamehameha1_7);
		}
		else{
			Animation.AddImage(Player.context, R.drawable.superkam_piccolo_1);
			Animation.AddImage(Player.context, R.drawable.superkam_piccolo_2);
			Animation.AddImage(Player.context, R.drawable.superkam_piccolo_3);
			Animation.AddImage(Player.context, R.drawable.superkam_piccolo_4);
			Animation.AddImage(Player.context, R.drawable.superkam_piccolo_5);
			Animation.AddImage(Player.context, R.drawable.superkam_piccolo_6);
			Animation.AddImage(Player.context, R.drawable.superkam_piccolo_7);
			Animation.AddImage(Player.context, R.drawable.superkam_piccolo_8);
		}
		
		if(NoCharacter != 3)
			this.Position = new Vector2D(Player.Position.X - 50,Player.Position.Y - 100);
		else
			this.Position = new Vector2D(Player.Position.X - 50,Player.Position.Y);
		Speed = new Vector2D();
		
		if(Player.NoPlayer == 2) this.Position.X += 30;
		break;
		}
	}
	
	public void Update()
	{
		Position.Plus(Speed);
		Animation.Position = new Vector2D(Position.X, Position.Y);
		Animation.Update();
	}
	
	public void draw(Canvas canvas)
	{
		Animation.Draw(canvas);
		if(Horizontalflip) Animation.HorizontalFlip = true;
	}
}
