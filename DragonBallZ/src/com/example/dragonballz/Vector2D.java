package com.example.dragonballz;

public class Vector2D implements Comparable<Vector2D>
{
	float X;
	float Y;
	
	public Vector2D(int X, int Y)
	{
		this.X=X;
		this.Y=Y;
	}
	
	public Vector2D(float X, float Y)
	{
		this.X=X;
		this.Y=Y;
	}
	
	public Vector2D()
	{
		this.X = 0;
		this.Y = 0;
	}

	@Override
	public int compareTo(Vector2D V)
	{
		if(X > V.X) return 1;
		else if(X < V.X) return -1;
		else
		{
			if(Y > V.Y) return 1;
			else if(Y < V.Y) return -1;
			else return 0;
		}
		
	}
	
	public void Plus(Vector2D V)
	{
		X += V.X;
		Y += V.Y;
	}
	
	public void Moins(Vector2D V)
	{
		X -= V.X;
		Y -= V.Y;
	}
	
	public void Diviser(int Chiffre)
	{
		X /= Chiffre;
		Y /= Chiffre;
	}

	@Override
    public String toString(){
        return "X = ".concat(String.valueOf(X).concat(" Y = ").concat(String.valueOf(Y)));
    }
}
