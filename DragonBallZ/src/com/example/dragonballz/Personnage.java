package com.example.dragonballz;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;

public class Personnage 
{
	private Vector2D NewPosition,InitialSpeed;
	public Vector2D Position,Speed;
	public cAnimation AnimationEnCours;
	
	public boolean ACTION_Move,ACTION_Guard,ACTION_Hit,ACTION_Shoot,ACTION_SuperShoot,ACTION_Recharge,ACTION_Hurt,
	SHOOT_Kamehameha,SHOOT_SuperKamehameha;
	
	public boolean Deplacement,HOLD_Timer,TOUCH_a_Button,TOUCH_OneTime;
	
	private int LimiteX = 1115,LimiteY = 580;
	public int cptTimeInactiveHit = 0,cptTimeInactiveShoot = 0,cptTimeHurt = 0,InactiveTime = 25;
	private int HOLD_Time = 0,HOLD_TimeKamehameha = 0;
	
	public Commandes Commandes;
	public Context context;
	
	public Boolean ADD_Balle = false,ADD_Kamehameha = false,ADD_SuperKamehameha = false, LauchSuperKamehameha = false, LauchSuperKamehamehaPiccolo = false;
	private Boolean OneTimeKamehameha = false,OneTimeSuperKamehameha = false, ChangeForEmptyButton = false;
	public Boolean ClickAttack = false;
	
	public LEBar LifeBar,EnergyBar;	
	public int NoPlayer;

	public cAnimation A_Idle,
	 A_MovingF,
	 A_MovingB,
	 A_Block,
	 A_Hit,
	 A_ShootKam,
	 A_ShootBalle,
	 A_Hurt;
	
	public cAnimation  A_ShootBigKam;
	
	private cAnimation Feu,ChargementKamehameha,ChargementSuperKamehameha;
	
	public List<Ball> ListBalls;
	
	public boolean IsEnnemy = false;
	public int NoCharacter;
	
	public Personnage(Context context,Vector2D Position,int NoPlayer)
	{
		this.Position = Position;
		NewPosition = new Vector2D(Position.X,Position.Y);
		AnimationEnCours = A_Idle;
		
		InitialSpeed = new Vector2D(5, 5);
		Speed = new Vector2D(InitialSpeed.X,InitialSpeed.Y);
		
		this.context = context;
		
		HOLD_Timer = false;
		LifeBar = new LEBar(context, Position, true);
		EnergyBar = new LEBar(context, Position, false);
		Commandes = new Commandes(context);
		
		this.NoPlayer = NoPlayer;
		ListBalls = new ArrayList<Ball>();
		Feu = new cAnimation(RessourceAnimation.Feu);
	}
	
	public void SetAnimation(int Character)
	{
		NoCharacter = Character;
		
		switch(Character)
		{
		case 1:
			 A_Idle = new cAnimation(RessourceAnimation.Goku_Idle); 
			 A_MovingF = new cAnimation(RessourceAnimation.Goku_MovingF);
			 A_MovingB = new cAnimation(RessourceAnimation.Goku_MovingB); 
			 A_Block = new cAnimation(RessourceAnimation.Goku_Block);
			 A_Hit = new cAnimation(RessourceAnimation.Goku_HittingF);
			 A_ShootKam = new cAnimation(RessourceAnimation.Goku_ShootKam);
			 A_ShootBalle = new cAnimation(RessourceAnimation.Goku_ShootBalle);
			 A_ShootBigKam = new cAnimation(RessourceAnimation.Goku_ShootBigKam);
			 ChargementKamehameha = new cAnimation(RessourceAnimation.ChargeKamehameha);
			 A_Hurt = new cAnimation(RessourceAnimation.Goku_Hurt);
		break;
		case 2:
			 A_Idle = new cAnimation(RessourceAnimation.Krillin_Idle); 
			 A_MovingF = new cAnimation(RessourceAnimation.Krillin_MovingF);
			 A_MovingB = new cAnimation(RessourceAnimation.Krillin_MovingB); 
			 A_Block = new cAnimation(RessourceAnimation.Krillin_Block);
			 A_Hit = new cAnimation(RessourceAnimation.Krillin_HittingF);
			 A_ShootKam = new cAnimation(RessourceAnimation.Krillin_ShootKam);
			 A_ShootBalle = new cAnimation(RessourceAnimation.Krillin_ShootBalle);
			 A_ShootBigKam = new cAnimation(RessourceAnimation.Krillin_ShootBigKam);
			 ChargementKamehameha = new cAnimation(RessourceAnimation.ChargeKamehameha);
			 A_Hurt = new cAnimation(RessourceAnimation.Krillin_Hurt);
		break;
		case 3:
			 A_Idle = new cAnimation(RessourceAnimation.Piccolo_Idle); 
			 A_MovingF = new cAnimation(RessourceAnimation.Piccolo_MovingF);
			 A_MovingB = new cAnimation(RessourceAnimation.Piccolo_MovingB); 
			 A_Block = new cAnimation(RessourceAnimation.Piccolo_Block);
			 A_Hit = new cAnimation(RessourceAnimation.Piccolo_HittingF);
			 A_ShootKam = new cAnimation(RessourceAnimation.Piccolo_ShootKam);
			 A_ShootBalle = new cAnimation(RessourceAnimation.Piccolo_ShootBalle);
			 A_ShootBigKam = new cAnimation(RessourceAnimation.Piccolo_ShootBigKam);
			 ChargementKamehameha = new cAnimation(RessourceAnimation.ChargeKamehamehaPiccolo);
			 ChargementSuperKamehameha = new cAnimation(RessourceAnimation.ChargeSuperKamehamehaPiccolo);
			 A_Hurt = new cAnimation(RessourceAnimation.Piccolo_Hurt);
		break;
		}
	}
	
	public void UpdateLogic()
	{
		if (ACTION_Move)
		{
			LifeBar.UpdateMotion(Position);
			EnergyBar.UpdateMotion(Position);
		
			if(!Deplacement) AnimationEnCours = A_Idle;
			else
			{
				if(Speed.X > 0)
				{
					if(NoPlayer == 1) AnimationEnCours = A_MovingF;
					else AnimationEnCours = A_MovingB;
				}
				else
				{
					if(NoPlayer == 1) AnimationEnCours = A_MovingB;
					else AnimationEnCours = A_MovingF;
				}
			}
		}
		else if (ACTION_Guard)
		{
			AnimationEnCours = A_Block;
				
			//Update FireAnimation
			if(ACTION_Recharge)
			{
				Feu.Position = new Vector2D(Position.X,Position.Y);
				switch(NoCharacter)
				{
					case 1:
						Feu.Position.Y += 10;
						break;
					case 2: 
						Feu.Position.Y -= 25;
						Feu.Position.X -= 15;
						break;
					case 3:
						Feu.Position.Y -= 15;
						Feu.Position.X -= 15;
						break;
				}
				Feu.Update();
			}
		}
		else if (ACTION_Hit)
		{
			if(cptTimeInactiveHit < InactiveTime) cptTimeInactiveHit++;
				
			//Automatic idle Animation
			if(cptTimeInactiveHit == InactiveTime)
			{
				if(AnimationEnCours != A_Idle) AnimationEnCours.Restart();
				AnimationEnCours = A_Idle;
			}
			else if(!TOUCH_a_Button) AnimationEnCours = A_Hit;
		}
		else if (ACTION_Shoot)
		{
			if(!SHOOT_Kamehameha)
			{
				if(cptTimeInactiveShoot < InactiveTime) cptTimeInactiveShoot++;
			}
			
			//Automatic idle Animation
			if(cptTimeInactiveShoot == InactiveTime)
			{
				if(AnimationEnCours != A_Idle) AnimationEnCours.Restart();
				AnimationEnCours = A_Idle;
			}
			else if(!TOUCH_a_Button)
			{
				if(!EnergyBar.Empty)
				{
					if(SHOOT_Kamehameha) AnimationEnCours = A_ShootKam;
					else AnimationEnCours = A_ShootBalle;
				}
			}
		}
		else if (ACTION_SuperShoot)
		{
			if(!SHOOT_SuperKamehameha)
			{
				if(cptTimeInactiveShoot < InactiveTime) cptTimeInactiveShoot++;
			}
			
			//Automatic idle Animation
			if(cptTimeInactiveShoot == InactiveTime)
			{
				if(AnimationEnCours != A_Idle) AnimationEnCours.Restart();
				AnimationEnCours = A_Idle;
			}
			else if(!TOUCH_a_Button && !EnergyBar.Empty)  AnimationEnCours = A_ShootBigKam;
		}
		else if (ACTION_Hurt)
		{
			if(cptTimeHurt < InactiveTime) cptTimeHurt++;
			if(cptTimeHurt == InactiveTime)
			{
				ACTION_Hurt = false;
				cptTimeHurt = 0;
				if(AnimationEnCours != A_Idle) AnimationEnCours.Restart();
			}
		}
		else
			AnimationEnCours = A_Idle;
	
		if(EnergyBar.Empty)
		{
			if(!ChangeForEmptyButton)
			{
				Commandes.ResetAllButtonTexture(this);
				ChangeForEmptyButton = true;
			}
		}
		else
		{
			if(ChangeForEmptyButton)
			{
				ChangeForEmptyButton = false;
				Commandes.ResetButton(1, this);
			}
		}
		
		if(!LifeBar.Initiate && !EnergyBar.Initiate)
		{ 
			LifeBar.Init(Position);
			EnergyBar.Init(Position);
		}
		else
		{
			LifeBar.UpdateLogic();
			EnergyBar.UpdateLogic();
		}
	
		
		//Limitations
		if(Position.Y >= LimiteY) { Position.Y = LimiteY; NewPosition.Y = LimiteY;}
		if(Position.X >= LimiteX) { Position.X = LimiteX; NewPosition.X = LimiteX;}
		
		
		/*#region Position **/
		AnimationEnCours.Position = Position;
		AnimationEnCours.Update();
		/*#endregion**/
	}
	
	public void UpdateMotion(MotionEvent ev)
	{
		Commandes.UpdateButtonLogic(context, ev,this);
		
		switch(ev.getAction())
		{
			case MotionEvent.ACTION_DOWN:
				if(!ACTION_Move)
				{
					//Dont execute the ACTION_Code if the player touched a 
					// button just for the first time
					if(TOUCH_OneTime)
					{
						TOUCH_a_Button = false;
						TOUCH_OneTime = false;
					}
					
					if(TOUCH_a_Button)
					{
						if(!TOUCH_OneTime) TOUCH_OneTime = true;
					}
					else
					{
						if (ACTION_Guard) ACTION_Recharge = true;
						else if (ACTION_Hit) 
						{
							AnimationEnCours.NextIndex = true;
							
							cptTimeInactiveHit = 0;
							if(AnimationEnCours.isFinish)AnimationEnCours.Restart();
							
							ClickAttack = true;
						}
						else if (ACTION_Shoot)
						{
							if(!EnergyBar.Empty)
							{
								HOLD_Timer = true;
								if(!SHOOT_Kamehameha) ADD_Balle = true;
								AnimationEnCours.NextIndex = true;
							}
							
							cptTimeInactiveShoot = 0;
							
							if(!SHOOT_Kamehameha)
							{
								if(AnimationEnCours.isFinish)AnimationEnCours.Restart();
							}
							else AnimationEnCours.Restart();
							
						}
						else if (ACTION_SuperShoot)
						{
							if(!EnergyBar.Empty)
							{
								HOLD_Timer = true;
								AnimationEnCours.NextIndex = true;
							}
							
							cptTimeInactiveShoot = 0;
						}
					}
				}
			break;
			
			case MotionEvent.ACTION_UP:
				HOLD_Timer = false;
				ACTION_Recharge = false;
				ClickAttack = false;
				
				if(SHOOT_Kamehameha)
				{
					AnimationEnCours.NextIndex = true;
					if(!OneTimeKamehameha)
					{
						if(AnimationEnCours.frameIndex() == 0 && AnimationEnCours == A_ShootKam)
						{
							OneTimeKamehameha = true;
							ADD_Kamehameha = true;
						}
					}
				}
				else if (SHOOT_SuperKamehameha)
				{
					AnimationEnCours.NextIndex = true;
					if(AnimationEnCours.frameIndex() == 0) 
					{
						LauchSuperKamehameha = true;
						if(NoCharacter == 3) LauchSuperKamehamehaPiccolo = true;
					}
				}
				break;				
		}
	}
	
	public void draw(Canvas canvas)
	{
		//Animation Charge Kamehameha (except Piccolo)
		if(SHOOT_Kamehameha && HOLD_Timer && NoCharacter != 3)
		{
			switch (NoCharacter) {
			case 1:
				ChargementKamehameha.Position = new Vector2D(Position.X - 255,Position.Y + 30);
				break;

			case 2:
				ChargementKamehameha.Position = new Vector2D(Position.X - 255,Position.Y + 10);
				break;
			}
			
			ChargementKamehameha.Update();
			ChargementKamehameha.Draw(canvas);
		}
		
		AnimationEnCours.Draw(canvas);
		
		//Animation Charge Kamehameha (Piccolo only)
		if(SHOOT_Kamehameha && HOLD_Timer && NoCharacter == 3)
		{
			ChargementKamehameha.Position = new Vector2D(Position.X - 225,Position.Y + 25);
			ChargementKamehameha.Update();
			ChargementKamehameha.Draw(canvas);
		}
		
		//Animation Charge SuperKamehameha (Piccolo only)
		if(SHOOT_SuperKamehameha && HOLD_Timer && NoCharacter == 3)
		{
			ChargementSuperKamehameha.Position = new Vector2D(Position.X + 55, Position.Y + 20);
			ChargementSuperKamehameha.Update();
			ChargementSuperKamehameha.Draw(canvas);
		}
		
		if(NoPlayer == 2) AnimationEnCours.HorizontalFlip = true;
		
		//FireAnimation Update
		if(ACTION_Recharge)
		{
			Feu.Draw(canvas);
			if(EnergyBar.Initiate)
			{
				EnergyBar.AnimationEnCours.AdvanceIndex(-1);
				if(EnergyBar.AnimationEnCours.frameIndex() < 0)
					EnergyBar.AnimationEnCours.AdvanceIndex(0);
			}
		}
		
		if(!IsEnnemy)
		Commandes.draw(canvas);

		Hold_Timer_Fonc();
	
		LifeBar.draw(canvas);
		EnergyBar.draw(canvas);
	}
	
	private void Hold_Timer_Fonc()
	{
		if(HOLD_Timer) 
		{
			HOLD_Time++;
			
			if (ACTION_Shoot)
			{
				if(HOLD_Time >= 20)
				{
					SHOOT_Kamehameha = true;
					HOLD_Time = 0;
				}
				else if(AnimationEnCours != A_ShootBalle) AnimationEnCours.Restart();	
			}
			else if (ACTION_SuperShoot)
			{
				if(HOLD_Time != 0 && AnimationEnCours.frameIndex() == 0)
				{
					SHOOT_SuperKamehameha = true;
					if(!OneTimeSuperKamehameha)
					{
						OneTimeSuperKamehameha = true;
						ADD_SuperKamehameha = true;
						HOLD_Time = 0;
					}
				}
			}
		}
		else
		{
			HOLD_Time = 0;
			
			if(SHOOT_Kamehameha)
			{
				HOLD_TimeKamehameha ++;
				if(HOLD_TimeKamehameha >= 30)
				{
					SHOOT_Kamehameha = false;
					OneTimeKamehameha = false;
					HOLD_TimeKamehameha = 0;
					AnimationEnCours.Restart();
				}
			}
			else if(SHOOT_SuperKamehameha)
			{
				HOLD_TimeKamehameha ++;
				if(HOLD_TimeKamehameha >= 10)
				{
					SHOOT_SuperKamehameha = false;
					OneTimeSuperKamehameha = false;
				}
			}
		}
	}
	
	public void GestionShootingBalls(Canvas canvas, int Screen_Width)
	{
		if(ADD_Balle) 
        {
        	ListBalls.add(new Ball(this, 0,NoCharacter));
        	EnergyBar.AnimationEnCours.NextIndex = true;
        	ADD_Balle = false;
        }
        if(ADD_Kamehameha)
        {
        	ListBalls.add(new Ball(this, 1,NoCharacter));
        	EnergyBar.AnimationEnCours.AdvanceIndex(5);
        	EnergyBar.AnimationEnCours.NextIndex = true;
        	ADD_Kamehameha = false;
        }
        if(ADD_SuperKamehameha)
        {
        	ListBalls.add(new Ball(this, 2,NoCharacter));
        	EnergyBar.AnimationEnCours.AdvanceIndex(37);
        	EnergyBar.AnimationEnCours.NextIndex = true;}
        	ADD_SuperKamehameha = false;
       
        Iterator<Ball> IBall = ListBalls.iterator();
		while(IBall.hasNext())
		{
			Ball b = IBall.next();
			{
				if(b.Sorte == 2)
					if(LauchSuperKamehameha)
					{
						if(NoPlayer == 1) b.SetSpeed(15, 0); else b.SetSpeed(-15, 0);
						LauchSuperKamehameha  = false;
					}
				
				if(NoCharacter != 3)
				{
					b.Update();
					b.draw(canvas);
				}
				else
				{
					if(b.Sorte != 2 || LauchSuperKamehamehaPiccolo)
					{
						b.Update();
						b.draw(canvas);
					}
				}
	        	if(b.Position().X > Screen_Width) { IBall.remove(); LauchSuperKamehamehaPiccolo = false;}
			}
		}
	}
	
	public String toJSON()
	{		
		try 
		{
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("NoPlayer", NoPlayer);
			jsonObj.put("PositionX", Position.X);
			jsonObj.put("PositionY", Position.Y);
			jsonObj.put("LifeX", LifeBar.AnimationEnCours.Position.X);
			jsonObj.put("LifeY", LifeBar.AnimationEnCours.Position.Y);
			jsonObj.put("EnergyX", EnergyBar.AnimationEnCours.Position.X);
			jsonObj.put("EnergyY", EnergyBar.AnimationEnCours.Position.Y);
			jsonObj.put("Deplacement", Deplacement);
			jsonObj.put("Guard", ACTION_Guard);
			jsonObj.put("Hit", ACTION_Hit);
			jsonObj.put("Shoot", ACTION_Shoot);
			jsonObj.put("Kamehameha", SHOOT_Kamehameha);
			jsonObj.put("SuperKamehameha",SHOOT_SuperKamehameha);
			jsonObj.put("DeplacementActif",ACTION_Move);
			jsonObj.put("Hold",HOLD_Timer);
			jsonObj.put("TouchButton",TOUCH_a_Button);
			jsonObj.put("SuperKActivate",ACTION_SuperShoot);
			
			return jsonObj.toString();
		} 
		
		catch (JSONException e) {
			e.printStackTrace();
		}
		
		return "";
	}
	
	public void FromJSON(String Data)
	{
		if(Data == null) Data = "";
		
		try {
			JSONObject jObj = new JSONObject(Data);
			NoPlayer = jObj.getInt("NoPlayer");
			
			float X = (float) jObj.getDouble("PositionX");
			float Y = (float) jObj.getDouble("PositionY");
			Position = new Vector2D(X, Y);
			
			 X = (float) jObj.getDouble("LifeX");
			 Y = (float) jObj.getDouble("LifeY");
			LifeBar.AnimationEnCours.Position = new Vector2D(X,Y);
			
			X = (float) jObj.getDouble("EnergyX");
			Y = (float) jObj.getDouble("EnergyY");
			EnergyBar.AnimationEnCours.Position = new Vector2D(X,Y);
			
			Deplacement = jObj.getBoolean("Deplacement");
			ACTION_Guard = jObj.getBoolean("Guard");
			ACTION_Hit = jObj.getBoolean("Hit");
			ACTION_Shoot = jObj.getBoolean("Shoot");
			SHOOT_Kamehameha = jObj.getBoolean("Kamehameha");
			SHOOT_SuperKamehameha = jObj.getBoolean("SuperKamehameha");
			
			ACTION_Move = jObj.getBoolean("DeplacementActif");
			HOLD_Timer = jObj.getBoolean("Hold");
			TOUCH_a_Button = jObj.getBoolean("TouchButton");
			ACTION_SuperShoot = jObj.getBoolean("SuperKActivate");
			
		} catch (JSONException e) {
		
			e.printStackTrace();
		}

	}
}
