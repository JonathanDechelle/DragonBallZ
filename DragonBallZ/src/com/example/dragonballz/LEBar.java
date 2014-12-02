package com.example.dragonballz;

import android.content.Context;
import android.graphics.Canvas;

public class LEBar 
{
	private cAnimation Status,FirstActivation;
	public cAnimation AnimationEnCours;
	public Boolean Initiate = false;
	private int Decalement;
	
	public boolean Empty = false;
	
	public void SetPosition(int X, int Y)
	{
		AnimationEnCours.Position.X = X;
		AnimationEnCours.Position.Y = Y;
	}
	
	public LEBar(Context context,Vector2D Position, boolean LifeBar)
	{
		Status = new cAnimation(false, 0);
		FirstActivation = new cAnimation(false, 0.5f);
		
		if(LifeBar)
		{
			Status.AddImage(context, R.drawable.lifebar1_39);
			Status.AddImage(context, R.drawable.lifebar1_38);
			Status.AddImage(context, R.drawable.lifebar1_37);
			Status.AddImage(context, R.drawable.lifebar1_36);
			Status.AddImage(context, R.drawable.lifebar1_35);
			Status.AddImage(context, R.drawable.lifebar1_34);
			Status.AddImage(context, R.drawable.lifebar1_33);
			Status.AddImage(context, R.drawable.lifebar1_32);
			Status.AddImage(context, R.drawable.lifebar1_31);
			Status.AddImage(context, R.drawable.lifebar1_30);
			Status.AddImage(context, R.drawable.lifebar1_29);
			Status.AddImage(context, R.drawable.lifebar1_28);
			Status.AddImage(context, R.drawable.lifebar1_27);
			Status.AddImage(context, R.drawable.lifebar1_26);
			Status.AddImage(context, R.drawable.lifebar1_25);
			Status.AddImage(context, R.drawable.lifebar1_24);
			Status.AddImage(context, R.drawable.lifebar1_23);
			Status.AddImage(context, R.drawable.lifebar1_22);
			Status.AddImage(context, R.drawable.lifebar1_21);
			Status.AddImage(context, R.drawable.lifebar1_20);
			Status.AddImage(context, R.drawable.lifebar1_19);
			Status.AddImage(context, R.drawable.lifebar1_18);
			Status.AddImage(context, R.drawable.lifebar1_17);
			Status.AddImage(context, R.drawable.lifebar1_16);
			Status.AddImage(context, R.drawable.lifebar1_15);
			Status.AddImage(context, R.drawable.lifebar1_14);
			Status.AddImage(context, R.drawable.lifebar1_13);
			Status.AddImage(context, R.drawable.lifebar1_12);
			Status.AddImage(context, R.drawable.lifebar1_11);
			Status.AddImage(context, R.drawable.lifebar1_10);
			Status.AddImage(context, R.drawable.lifebar1_9);
			Status.AddImage(context, R.drawable.lifebar1_8);
			Status.AddImage(context, R.drawable.lifebar1_7);
			Status.AddImage(context, R.drawable.lifebar1_6);
			Status.AddImage(context, R.drawable.lifebar1_5);
			Status.AddImage(context, R.drawable.lifebar1_4);
			Status.AddImage(context, R.drawable.lifebar1_3);
			Status.AddImage(context, R.drawable.lifebar1_2);
			Status.AddImage(context, R.drawable.lifebar1_1);
			
			FirstActivation.AddImage(context, R.drawable.lifebar1_1);
			FirstActivation.AddImage(context, R.drawable.lifebar1_2);
			FirstActivation.AddImage(context, R.drawable.lifebar1_3);
			FirstActivation.AddImage(context, R.drawable.lifebar1_4);
			FirstActivation.AddImage(context, R.drawable.lifebar1_5);
			FirstActivation.AddImage(context, R.drawable.lifebar1_6);
			FirstActivation.AddImage(context, R.drawable.lifebar1_7);
			FirstActivation.AddImage(context, R.drawable.lifebar1_8);
			FirstActivation.AddImage(context, R.drawable.lifebar1_9);
			FirstActivation.AddImage(context, R.drawable.lifebar1_10);
			FirstActivation.AddImage(context, R.drawable.lifebar1_11);
			FirstActivation.AddImage(context, R.drawable.lifebar1_12);
			FirstActivation.AddImage(context, R.drawable.lifebar1_13);
			FirstActivation.AddImage(context, R.drawable.lifebar1_14);
			FirstActivation.AddImage(context, R.drawable.lifebar1_15);
			FirstActivation.AddImage(context, R.drawable.lifebar1_16);
			FirstActivation.AddImage(context, R.drawable.lifebar1_17);
			FirstActivation.AddImage(context, R.drawable.lifebar1_18);
			FirstActivation.AddImage(context, R.drawable.lifebar1_19);
			FirstActivation.AddImage(context, R.drawable.lifebar1_20);
			FirstActivation.AddImage(context, R.drawable.lifebar1_21);
			FirstActivation.AddImage(context, R.drawable.lifebar1_22);
			FirstActivation.AddImage(context, R.drawable.lifebar1_23);
			FirstActivation.AddImage(context, R.drawable.lifebar1_24);
			FirstActivation.AddImage(context, R.drawable.lifebar1_25);
			FirstActivation.AddImage(context, R.drawable.lifebar1_26);
			FirstActivation.AddImage(context, R.drawable.lifebar1_27);
			FirstActivation.AddImage(context, R.drawable.lifebar1_28);
			FirstActivation.AddImage(context, R.drawable.lifebar1_29);
			FirstActivation.AddImage(context, R.drawable.lifebar1_30);
			FirstActivation.AddImage(context, R.drawable.lifebar1_31);
			FirstActivation.AddImage(context, R.drawable.lifebar1_32);
			FirstActivation.AddImage(context, R.drawable.lifebar1_33);
			FirstActivation.AddImage(context, R.drawable.lifebar1_34);
			FirstActivation.AddImage(context, R.drawable.lifebar1_35);
			FirstActivation.AddImage(context, R.drawable.lifebar1_36);
			FirstActivation.AddImage(context, R.drawable.lifebar1_37);
			FirstActivation.AddImage(context, R.drawable.lifebar1_38);
			FirstActivation.AddImage(context, R.drawable.lifebar1_39);
		}
		else
		{
			Status.AddImage(context, R.drawable.energybar1_39);
			Status.AddImage(context, R.drawable.energybar1_38);
			Status.AddImage(context, R.drawable.energybar1_37);
			Status.AddImage(context, R.drawable.energybar1_36);
			Status.AddImage(context, R.drawable.energybar1_35);
			Status.AddImage(context, R.drawable.energybar1_34);
			Status.AddImage(context, R.drawable.energybar1_33);
			Status.AddImage(context, R.drawable.energybar1_32);
			Status.AddImage(context, R.drawable.energybar1_31);
			Status.AddImage(context, R.drawable.energybar1_30);
			Status.AddImage(context, R.drawable.energybar1_29);
			Status.AddImage(context, R.drawable.energybar1_28);
			Status.AddImage(context, R.drawable.energybar1_27);
			Status.AddImage(context, R.drawable.energybar1_26);
			Status.AddImage(context, R.drawable.energybar1_25);
			Status.AddImage(context, R.drawable.energybar1_24);
			Status.AddImage(context, R.drawable.energybar1_23);
			Status.AddImage(context, R.drawable.energybar1_22);
			Status.AddImage(context, R.drawable.energybar1_21);
			Status.AddImage(context, R.drawable.energybar1_20);
			Status.AddImage(context, R.drawable.energybar1_19);
			Status.AddImage(context, R.drawable.energybar1_18);
			Status.AddImage(context, R.drawable.energybar1_17);
			Status.AddImage(context, R.drawable.energybar1_16);
			Status.AddImage(context, R.drawable.energybar1_15);
			Status.AddImage(context, R.drawable.energybar1_14);
			Status.AddImage(context, R.drawable.energybar1_13);
			Status.AddImage(context, R.drawable.energybar1_12);
			Status.AddImage(context, R.drawable.energybar1_11);
			Status.AddImage(context, R.drawable.energybar1_10);
			Status.AddImage(context, R.drawable.energybar1_9);
			Status.AddImage(context, R.drawable.energybar1_8);
			Status.AddImage(context, R.drawable.energybar1_7);
			Status.AddImage(context, R.drawable.energybar1_6);
			Status.AddImage(context, R.drawable.energybar1_5);
			Status.AddImage(context, R.drawable.energybar1_4);
			Status.AddImage(context, R.drawable.energybar1_3);
			Status.AddImage(context, R.drawable.energybar1_2);
			Status.AddImage(context, R.drawable.energybar1_1);
			
			FirstActivation.AddImage(context, R.drawable.energybar1_1);
			FirstActivation.AddImage(context, R.drawable.energybar1_2);
			FirstActivation.AddImage(context, R.drawable.energybar1_3);
			FirstActivation.AddImage(context, R.drawable.energybar1_4);
			FirstActivation.AddImage(context, R.drawable.energybar1_5);
			FirstActivation.AddImage(context, R.drawable.energybar1_6);
			FirstActivation.AddImage(context, R.drawable.energybar1_7);
			FirstActivation.AddImage(context, R.drawable.energybar1_8);
			FirstActivation.AddImage(context, R.drawable.energybar1_9);
			FirstActivation.AddImage(context, R.drawable.energybar1_10);
			FirstActivation.AddImage(context, R.drawable.energybar1_11);
			FirstActivation.AddImage(context, R.drawable.energybar1_12);
			FirstActivation.AddImage(context, R.drawable.energybar1_13);
			FirstActivation.AddImage(context, R.drawable.energybar1_14);
			FirstActivation.AddImage(context, R.drawable.energybar1_15);
			FirstActivation.AddImage(context, R.drawable.energybar1_16);
			FirstActivation.AddImage(context, R.drawable.energybar1_17);
			FirstActivation.AddImage(context, R.drawable.energybar1_18);
			FirstActivation.AddImage(context, R.drawable.energybar1_19);
			FirstActivation.AddImage(context, R.drawable.energybar1_20);
			FirstActivation.AddImage(context, R.drawable.energybar1_21);
			FirstActivation.AddImage(context, R.drawable.energybar1_22);
			FirstActivation.AddImage(context, R.drawable.energybar1_23);
			FirstActivation.AddImage(context, R.drawable.energybar1_24);
			FirstActivation.AddImage(context, R.drawable.energybar1_25);
			FirstActivation.AddImage(context, R.drawable.energybar1_26);
			FirstActivation.AddImage(context, R.drawable.energybar1_27);
			FirstActivation.AddImage(context, R.drawable.energybar1_28);
			FirstActivation.AddImage(context, R.drawable.energybar1_29);
			FirstActivation.AddImage(context, R.drawable.energybar1_30);
			FirstActivation.AddImage(context, R.drawable.energybar1_31);
			FirstActivation.AddImage(context, R.drawable.energybar1_32);
			FirstActivation.AddImage(context, R.drawable.energybar1_33);
			FirstActivation.AddImage(context, R.drawable.energybar1_34);
			FirstActivation.AddImage(context, R.drawable.energybar1_35);
			FirstActivation.AddImage(context, R.drawable.energybar1_36);
			FirstActivation.AddImage(context, R.drawable.energybar1_37);
			FirstActivation.AddImage(context, R.drawable.energybar1_38);
			FirstActivation.AddImage(context, R.drawable.energybar1_39);
		}
		
		AnimationEnCours = FirstActivation;
		
		if(LifeBar) Decalement = 40;
		else	Decalement = 25;
		SetPosition((int)Position.X, (int)Position.Y - Decalement);
	}
	
	public void Init(Vector2D Position)
	{
		AnimationEnCours.Update();
		if(AnimationEnCours == FirstActivation && AnimationEnCours.isFinish)
		{
			AnimationEnCours.Restart();
			AnimationEnCours = Status;
			Initiate = true;
		}
		
		if(Initiate) SetPosition((int)Position.X, (int)Position.Y - Decalement);
	}
	
	public void UpdateMotion(Vector2D Position)
	{
		SetPosition((int)Position.X, (int)Position.Y - Decalement);
	}
	
	public void UpdateLogic()
	{
		AnimationEnCours.Update();
		if(AnimationEnCours == Status && AnimationEnCours.isFinish)
		{
			Empty = true;
		}
		else 
			Empty = false;
	}
	
	public void draw(Canvas canvas)
	{
		AnimationEnCours.Draw(canvas);
	}
}
