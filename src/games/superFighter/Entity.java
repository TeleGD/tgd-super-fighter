package games.superFighter;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

public abstract class Entity {

	protected double x,y;
	protected double newx,newy,oldx,oldy;

	//Nécessaire pour les jeux****************************************************
	public abstract void render(GameContainer container, StateBasedGame game, Graphics g);
	public abstract void update(GameContainer container, StateBasedGame game, int delta);


	//Setters*********************************************************************
	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}



	//Getters***********************************************************************
	public double getY() {
		return y;
	}

	public double getX() {
		return x;
	}
	public double getnewY() {
		return y;
	}

	public double getnewX() {
		return x;
	}





}
