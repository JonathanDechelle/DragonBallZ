package com.example.dragonballz;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;

public class Commandes 
{
	private cButton B_Hit,B_Shoot,B_ShootSuper,B_Guard;
	
	public Commandes(Context context)
	{
		B_Guard =  new cButton(context, R.drawable.buttonguardoff, 700, 550, 70, 50);
		B_Hit =  new cButton(context, R.drawable.buttonhitoff, 800, 550, 70, 50);
		B_Shoot =  new cButton(context, R.drawable.buttonshootoff, 900, 550, 70, 50);
		B_ShootSuper =  new cButton(context, R.drawable.buttonshootsuperoff, 1000, 550, 70, 50);
	}
	
	public void draw(Canvas canvas)
	{
		B_Guard.draw(canvas);
		B_Hit.draw(canvas);
		B_Shoot.draw(canvas);
		B_ShootSuper.draw(canvas);
	}
	
	
	public void UpdateButtonLogic(Context context, MotionEvent ev, Personnage Player)
	{
		B_Guard.Update(context, ev);
		B_Hit.Update(context, ev);
		B_Shoot.Update(context, ev);
		B_ShootSuper.Update(context, ev);
		
		if(B_Guard.IsClicked)
		{
			ResetButton(1, Player);
			Player.TOUCH_a_Button = true;
			Player.TOUCH_OneTime = false;
			Player.SHOOT_Kamehameha = false;
			Player.SHOOT_SuperKamehameha = false;
			
			if(B_Guard.idImage() == R.drawable.buttonguardon)
			{
				B_Guard.SetImage(R.drawable.buttonguardoff);
				Player.ACTION_Guard = false;
			}
			else
			{
				B_Guard.SetImage(R.drawable.buttonguardon);
				Player.ACTION_Guard = true;
			}
			
			B_Guard.IsClicked = false;
		}
		
		else if(B_Hit.IsClicked)
		{
			ResetButton(2, Player);
			Player.TOUCH_a_Button = true;
			Player.TOUCH_OneTime = false;
			Player.SHOOT_Kamehameha = false;
			Player.SHOOT_SuperKamehameha = false;
	
			
			if(B_Hit.idImage() == R.drawable.buttonhiton)
			{
				B_Hit.SetImage(R.drawable.buttonhitoff);
				Player.ACTION_Hit = false;
			}
			else
			{
				B_Hit.SetImage(R.drawable.buttonhiton);
				Player.ACTION_Hit = true;
				Player.cptTimeInactiveHit = 0;
			}
			B_Hit.IsClicked = false;
		}
		
		else if(B_Shoot.IsClicked)
		{
			ResetButton(3, Player);
			Player.TOUCH_a_Button = true;
			Player.TOUCH_OneTime = false;
			
			if(!Player.EnergyBar.Empty)
			{
				if(B_Shoot.idImage() == R.drawable.buttonshooton)
				{
					B_Shoot.SetImage(R.drawable.buttonshootoff);
					Player.ACTION_Shoot = false;
				}
				else
				{
					B_Shoot.SetImage(R.drawable.buttonshooton);
					Player.ACTION_Shoot = true;
				}
			}
			else
			{
				if(B_Shoot.idImage() == R.drawable.buttonshooton)
				{
					B_Shoot.SetImage(R.drawable.buttonshootonempty);
				}
				else if(B_Shoot.idImage() == R.drawable.buttonshootonempty)
				{
					B_Shoot.SetImage(R.drawable.buttonshootoffempty);
				}
				else
				{
					B_Shoot.SetImage(R.drawable.buttonshootonempty);
				}
			}
		
			B_Shoot.IsClicked = false;
		}
		
		else if(B_ShootSuper.IsClicked)
		{
			ResetButton(4, Player);
			Player.TOUCH_a_Button = true;
			Player.TOUCH_OneTime = false;
			
			if(!Player.EnergyBar.Empty)
			{
				if(B_ShootSuper.idImage() == R.drawable.buttonshootsuperon)
				{
					B_ShootSuper.SetImage(R.drawable.buttonshootsuperoff);
					Player.ACTION_SuperShoot = false;
				}
				else
				{
					B_ShootSuper.SetImage(R.drawable.buttonshootsuperon);
					Player.ACTION_SuperShoot = true;
				}
			}
			else
			{
				if(B_ShootSuper.idImage() == R.drawable.buttonshooton)
				{
					B_ShootSuper.SetImage(R.drawable.buttonshootsuperonempty);
				}
				else if(B_ShootSuper.idImage() == R.drawable.buttonshootsuperonempty)
				{
					B_ShootSuper.SetImage(R.drawable.buttonshootsuperoffempty);
				}
				else
				{
					B_ShootSuper.SetImage(R.drawable.buttonshootsuperonempty);
				}
			}
			
			B_ShootSuper.IsClicked = false;
		}
	}
	
	public void ResetButton(int NoButton, Personnage Player)
	{
		Player.HOLD_Timer = false;
		
		RessourceAnimation.Goku_ShootKam.Restart();
		
		if(NoButton != 1)
		{
			if(B_Guard.idImage() != R.drawable.buttonguardoff)
				B_Guard.SetImage(R.drawable.buttonguardoff);
			Player.ACTION_Guard = false;
		}
		if(NoButton != 2)
		{
			if(B_Hit.idImage() != R.drawable.buttonhitoff)
				B_Hit.SetImage(R.drawable.buttonhitoff);
			RessourceAnimation.Goku_HittingF.Restart();
			Player.ACTION_Hit = false;
		}
		if(NoButton != 3)
		{
			if(!Player.EnergyBar.Empty)
			{
				if(B_Shoot.idImage() != R.drawable.buttonshootoff)
					B_Shoot.SetImage(R.drawable.buttonshootoff);
			}
			else
			{
				if(B_Shoot.idImage() != R.drawable.buttonshootoffempty)
					B_Shoot.SetImage(R.drawable.buttonshootoffempty);
			}
			Player.ACTION_Shoot = false;
		}
		if(NoButton != 4) 
		{
			if(!Player.EnergyBar.Empty)
			{
				if(B_ShootSuper.idImage() != R.drawable.buttonshootsuperoff)
					B_ShootSuper.SetImage(R.drawable.buttonshootsuperoff);
			}
			else
			{
				if(B_ShootSuper.idImage() != R.drawable.buttonshootsuperoffempty)
					B_ShootSuper.SetImage(R.drawable.buttonshootsuperoffempty);
			}
			Player.ACTION_SuperShoot = false;
		}
	}
	
	
	public void ResetAllButtonTexture(Personnage Player)
	{
		if(B_Guard.idImage() != R.drawable.buttonguardoff)
			B_Guard.SetImage(R.drawable.buttonguardoff);
		
		if(B_Hit.idImage() != R.drawable.buttonhitoff)
			B_Hit.SetImage(R.drawable.buttonhitoff);
		
		if(!Player.EnergyBar.Empty)
		{
			if(B_Shoot.idImage() != R.drawable.buttonshootoff)
				B_Shoot.SetImage(R.drawable.buttonshootoff);
			
			if(B_ShootSuper.idImage() != R.drawable.buttonshootsuperoff)
				B_ShootSuper.SetImage(R.drawable.buttonshootsuperoff);
		}
		else
		{
			if(B_Shoot.idImage() != R.drawable.buttonshootoffempty)
				B_Shoot.SetImage(R.drawable.buttonshootoffempty);
			
			if(B_ShootSuper.idImage() != R.drawable.buttonshootsuperoffempty)
				B_ShootSuper.SetImage(R.drawable.buttonshootsuperoffempty);
		}
	}
}
