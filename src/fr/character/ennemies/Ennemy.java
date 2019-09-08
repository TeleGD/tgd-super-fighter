package fr.character.ennemies;

import fr.character.Player;
import fr.util.Movable;

public abstract class Ennemy extends Movable {
	protected int score;
	protected int life;
	protected double xorigine;
	protected double height,width; //On part du principe que tous les ennemis seront des rectangles
	public void collPlayer(Player joueur) {
	}
	public int getLife(){return life;}
	public double getWidth(){
		return width;
	}
	public double getHeight(){
		return height;
	}

	public void loselife(){
		life-=1;
	}
	public void scoring(){
		fr.main.World.increaseScore(this.score);
	}
}
