package games.superFighter.entities;

import org.newdawn.slick.state.StateBasedGame;

import games.superFighter.World;
import games.superFighter.entities.Player;
import games.superFighter.util.Movable;

public abstract class Ennemy extends Movable {
	protected World world;
	protected int score;
	protected int life;
	protected double xorigine;
	protected double height,width; //On part du principe que tous les ennemis seront des rectangles
	public Ennemy(World world) {
		this.world = world;
	}
	public void collPlayer(StateBasedGame game, Player joueur) {
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
		world.increaseScore(this.score);
	}
}
