package fr.projectiles;

import fr.util.Circle;
import fr.util.Movable;

public abstract class Projectile extends Movable implements Circle{

	protected double radius;
	public boolean destroyed=false;

	@Override
	public double getRadius() {
		return radius;
	}

	public double getXCenter(){
		return x;
	}
	public double getYCenter(){
		return y;
	}

	public double getnewXCenter(){
		return newx;
	}
	public double getnewYCenter(){
		return newy;
	}

	public boolean isDestroyed(){
		return destroyed;
	}
}