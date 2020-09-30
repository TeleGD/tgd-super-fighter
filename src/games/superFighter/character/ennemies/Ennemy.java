package games.superFighter.character.ennemies;

import games.superFighter.World;
import games.superFighter.character.Player;
import games.superFighter.util.Movable;

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
		World.increaseScore(this.score);
	}
}
