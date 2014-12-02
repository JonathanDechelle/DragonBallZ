package com.example.dragonballz;

import android.content.Context;

public class RessourceAnimation 
{
	public static cAnimation Goku_Idle,Goku_MovingF,Goku_MovingB,
	Goku_Block,Goku_HittingF,Goku_HittingU,Goku_HittingD,Goku_Hurt,
	Goku_ShootBalle,Goku_ShootBigKam,Goku_ShootKam,Goku_SeeNothing;
	
	public static cAnimation Krillin_Idle,Krillin_MovingF,Krillin_MovingB,
	Krillin_Block,Krillin_HittingF,Krillin_Hurt,
	Krillin_ShootBalle,Krillin_ShootBigKam,Krillin_ShootKam,Krillin_SeeNothing;
	
	public static cAnimation Piccolo_Idle,Piccolo_MovingF,Piccolo_MovingB,
	Piccolo_Block,Piccolo_HittingF,Piccolo_Hurt,
	Piccolo_ShootBalle,Piccolo_ShootBigKam,Piccolo_ShootKam,Piccolo_SeeNothing;
	
	public static cAnimation Feu,ChargeKamehameha,ChargeKamehamehaPiccolo,ChargeSuperKamehamehaPiccolo;
	
	static public void LoadAnimation(Context context)
	{
		Goku_Idle = new cAnimation(true, 0.05f);
		Goku_Idle.AddImage(context, R.drawable.goku1_1);
		Goku_Idle.AddImage(context, R.drawable.goku1_2);
		Goku_Idle.AddImage(context, R.drawable.goku1_2);
		Goku_MovingB = new cAnimation(true, 0.05f);
		Goku_MovingB.AddImage(context, R.drawable.goku2_1);
		Goku_MovingB.AddImage(context, R.drawable.goku2_1);
		Goku_MovingF = new cAnimation(true, 0.05f);
		Goku_MovingF.AddImage(context, R.drawable.goku2_2);
		Goku_MovingF.AddImage(context, R.drawable.goku2_2);
		Goku_Block = new cAnimation(true, 0.05f);
		Goku_Block.AddImage(context, R.drawable.goku3_1);
		Goku_Block.AddImage(context, R.drawable.goku3_2);
		Goku_Block.AddImage(context, R.drawable.goku3_2);
		Goku_HittingF = new cAnimation(false, 0);
		Goku_HittingF.AddImage(context, R.drawable.goku4_1);
		Goku_HittingF.AddImage(context, R.drawable.goku4_2);
		Goku_HittingF.AddImage(context, R.drawable.goku4_3);
		Goku_HittingF.AddImage(context, R.drawable.goku4_4);
		Goku_HittingF.AddImage(context, R.drawable.goku4_5);
		Goku_HittingF.AddImage(context, R.drawable.goku4_6);
		Goku_HittingF.AddImage(context, R.drawable.goku4_7);
		Goku_HittingU = new cAnimation(false, 0.05f);
		Goku_HittingU.AddImage(context, R.drawable.goku5_1);
		Goku_HittingU.AddImage(context, R.drawable.goku5_2);
		Goku_HittingD = new cAnimation(false, 0.05f);
		Goku_HittingD.AddImage(context, R.drawable.goku6_1);
		Goku_HittingD.AddImage(context, R.drawable.goku6_2);
		Goku_Hurt = new cAnimation(true, 0);
		Goku_Hurt.AddImage(context, R.drawable.goku7_1);
		Goku_Hurt.AddImage(context, R.drawable.goku7_2);
		Goku_Hurt.AddImage(context, R.drawable.goku7_3);
		Goku_Hurt.AddImage(context, R.drawable.goku7_4);
		//Goku_Hurt.AddImage(context, R.drawable.goku7_5);
		//Goku_Hurt.AddImage(context, R.drawable.goku7_6);
		Goku_ShootBalle = new cAnimation(true, 0f);
		Goku_ShootBalle.AddImage(context, R.drawable.goku8_1);
		Goku_ShootBalle.AddImage(context, R.drawable.goku8_2);
		Goku_ShootBalle.AddImage(context, R.drawable.goku8_2);
		Goku_ShootBigKam = new cAnimation(false, 0);
		Goku_ShootBigKam.AddImage(context, R.drawable.goku9_1);
		Goku_ShootBigKam.AddImage(context, R.drawable.goku9_2);
		Goku_ShootBigKam.AddImage(context, R.drawable.goku9_2);
		Goku_ShootKam = new cAnimation(false, 0);
		Goku_ShootKam.AddImage(context, R.drawable.goku10_1);
		Goku_ShootKam.AddImage(context, R.drawable.goku10_2);
		Goku_ShootKam.AddImage(context, R.drawable.goku10_2);
		Goku_SeeNothing = new cAnimation(true, 0.05f);
		Goku_SeeNothing.AddImage(context, R.drawable.goku11_1);
		Goku_SeeNothing.AddImage(context, R.drawable.goku11_2);
		Goku_SeeNothing.AddImage(context, R.drawable.goku11_2);
		
		Krillin_Idle = new cAnimation(true, 0.05f);
		Krillin_Idle.AddImage(context, R.drawable.krillin1_1);
		Krillin_Idle.AddImage(context, R.drawable.krillin1_2);
		Krillin_Idle.AddImage(context, R.drawable.krillin1_2);
		Krillin_MovingB = new cAnimation(true, 0.05f);
		Krillin_MovingB.AddImage(context, R.drawable.krillin2_1);
		Krillin_MovingB.AddImage(context, R.drawable.krillin2_1);
		Krillin_MovingF = new cAnimation(true, 0.05f);
		Krillin_MovingF.AddImage(context, R.drawable.krillin2_2);
		Krillin_MovingF.AddImage(context, R.drawable.krillin2_2);
		Krillin_Block = new cAnimation(true, 0.05f);
		Krillin_Block.AddImage(context, R.drawable.krillin3_1);
		Krillin_Block.AddImage(context, R.drawable.krillin3_2);
		Krillin_Block.AddImage(context, R.drawable.krillin3_2);
		Krillin_HittingF = new cAnimation(false, 0);
		Krillin_HittingF.AddImage(context, R.drawable.krillin4_1);
		Krillin_HittingF.AddImage(context, R.drawable.krillin4_2);
		Krillin_HittingF.AddImage(context, R.drawable.krillin4_3);
		Krillin_HittingF.AddImage(context, R.drawable.krillin4_4);
		Krillin_HittingF.AddImage(context, R.drawable.krillin4_5);
		Krillin_HittingF.AddImage(context, R.drawable.krillin4_6);
		Krillin_HittingF.AddImage(context, R.drawable.krillin4_7);
		Krillin_Hurt = new cAnimation(true, 0f);
		Krillin_Hurt.AddImage(context, R.drawable.krillin5_1);
		Krillin_Hurt.AddImage(context, R.drawable.krillin5_2);
		Krillin_Hurt.AddImage(context, R.drawable.krillin5_3);
		Krillin_Hurt.AddImage(context, R.drawable.krillin5_4);
		//Krillin_Hurt.AddImage(context, R.drawable.krillin5_5);
		//Krillin_Hurt.AddImage(context, R.drawable.krillin5_6);
		Krillin_ShootBalle = new cAnimation(true, 0f);
		Krillin_ShootBalle.AddImage(context, R.drawable.krillin6_1);
		Krillin_ShootBalle.AddImage(context, R.drawable.krillin6_2);
		Krillin_ShootBalle.AddImage(context, R.drawable.krillin6_2);
		Krillin_ShootBigKam = new cAnimation(false, 0);
		Krillin_ShootBigKam.AddImage(context, R.drawable.krillin7_1);
		Krillin_ShootBigKam.AddImage(context, R.drawable.krillin7_2);
		Krillin_ShootBigKam.AddImage(context, R.drawable.krillin7_2);
		Krillin_ShootKam = new cAnimation(false, 0);
		Krillin_ShootKam.AddImage(context, R.drawable.krillin8_1);
		Krillin_ShootKam.AddImage(context, R.drawable.krillin8_2);
		Krillin_ShootKam.AddImage(context, R.drawable.krillin8_2);
		Krillin_SeeNothing = new cAnimation(true, 0.05f);
		Krillin_SeeNothing.AddImage(context, R.drawable.krillin9_1);
		Krillin_SeeNothing.AddImage(context, R.drawable.krillin9_2);
		Krillin_SeeNothing.AddImage(context, R.drawable.krillin9_2);
		
		Piccolo_Idle = new cAnimation(true, 0.05f);
		Piccolo_Idle.AddImage(context, R.drawable.piccolo1_1);
		Piccolo_Idle.AddImage(context, R.drawable.piccolo1_2);
		Piccolo_Idle.AddImage(context, R.drawable.piccolo1_2);
		Piccolo_MovingB = new cAnimation(true, 0.05f);
		Piccolo_MovingB.AddImage(context, R.drawable.piccolo2_1);
		Piccolo_MovingB.AddImage(context, R.drawable.piccolo2_1);
		Piccolo_MovingF = new cAnimation(true, 0.05f);
		Piccolo_MovingF.AddImage(context, R.drawable.piccolo2_2);
		Piccolo_MovingF.AddImage(context, R.drawable.piccolo2_2);
		Piccolo_Block = new cAnimation(true, 0.05f);
		Piccolo_Block.AddImage(context, R.drawable.piccolo3_1);
		Piccolo_Block.AddImage(context, R.drawable.piccolo3_2);
		Piccolo_Block.AddImage(context, R.drawable.piccolo3_2);
		Piccolo_HittingF = new cAnimation(false, 0);
		Piccolo_HittingF.AddImage(context, R.drawable.piccolo4_1);
		Piccolo_HittingF.AddImage(context, R.drawable.piccolo4_2);
		Piccolo_HittingF.AddImage(context, R.drawable.piccolo4_3);
		Piccolo_HittingF.AddImage(context, R.drawable.piccolo4_4);
		Piccolo_HittingF.AddImage(context, R.drawable.piccolo4_5);
		Piccolo_HittingF.AddImage(context, R.drawable.piccolo4_6);
		Piccolo_HittingF.AddImage(context, R.drawable.piccolo4_7);
		Piccolo_Hurt = new cAnimation(true, 0f);
		Piccolo_Hurt.AddImage(context, R.drawable.piccolo5_1);
		Piccolo_Hurt.AddImage(context, R.drawable.piccolo5_2);
		Piccolo_Hurt.AddImage(context, R.drawable.piccolo5_3);
		Piccolo_Hurt.AddImage(context, R.drawable.piccolo5_4);
		//Piccolo_Hurt.AddImage(context, R.drawable.piccolo5_5);
		//Piccolo_Hurt.AddImage(context, R.drawable.piccolo5_6);
		Piccolo_ShootBalle = new cAnimation(true, 0f);
		Piccolo_ShootBalle.AddImage(context, R.drawable.piccolo6_1);
		Piccolo_ShootBalle.AddImage(context, R.drawable.piccolo6_2);
		Piccolo_ShootBalle.AddImage(context, R.drawable.piccolo6_2);
		Piccolo_ShootBigKam = new cAnimation(false, 0);
		Piccolo_ShootBigKam.AddImage(context, R.drawable.piccolo7_1);
		Piccolo_ShootBigKam.AddImage(context, R.drawable.piccolo7_2);
		Piccolo_ShootBigKam.AddImage(context, R.drawable.piccolo7_2);
		Piccolo_ShootKam = new cAnimation(false, 0);
		Piccolo_ShootKam.AddImage(context, R.drawable.piccolo8_1);
		Piccolo_ShootKam.AddImage(context, R.drawable.piccolo8_2);
		Piccolo_ShootKam.AddImage(context, R.drawable.piccolo8_2);
		
		
		Feu = new cAnimation(true,0.5f);
		Feu.AddImage(context, R.drawable.feu1);
		Feu.AddImage(context, R.drawable.feu2);
		Feu.AddImage(context, R.drawable.feu3);
		Feu.AddImage(context, R.drawable.feu4);
		Feu.AddImage(context, R.drawable.feu5);
		Feu.AddImage(context, R.drawable.feu5);
		
		ChargeKamehameha = new cAnimation(true,0.5f);
		ChargeKamehameha.AddImage(context, R.drawable.balle_charge_1);
		ChargeKamehameha.AddImage(context, R.drawable.balle_charge_2);
		ChargeKamehameha.AddImage(context, R.drawable.balle_charge_3);
		ChargeKamehameha.AddImage(context, R.drawable.balle_charge_4);
		ChargeKamehameha.AddImage(context, R.drawable.balle_charge_4);
		
		ChargeKamehamehaPiccolo = new cAnimation(true,0.5f);
		ChargeKamehamehaPiccolo.AddImage(context, R.drawable.balle_charge_1_o);
		ChargeKamehamehaPiccolo.AddImage(context, R.drawable.balle_charge_2_o);
		ChargeKamehamehaPiccolo.AddImage(context, R.drawable.balle_charge_3_o);
		ChargeKamehamehaPiccolo.AddImage(context, R.drawable.balle_charge_4_o);
		ChargeKamehamehaPiccolo.AddImage(context, R.drawable.balle_charge_4_o);
		
		ChargeSuperKamehamehaPiccolo = new cAnimation(true,0.5f);
		ChargeSuperKamehamehaPiccolo.AddImage(context, R.drawable.superkampiccolo_1);
		ChargeSuperKamehamehaPiccolo.AddImage(context, R.drawable.superkampiccolo_2);
		ChargeSuperKamehamehaPiccolo.AddImage(context, R.drawable.superkampiccolo_3);
		ChargeSuperKamehamehaPiccolo.AddImage(context, R.drawable.superkampiccolo_4);
		ChargeSuperKamehamehaPiccolo.AddImage(context, R.drawable.superkampiccolo_4);
	}
}
