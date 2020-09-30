package games.superFighter.entities.players;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import games.superFighter.World;
import games.superFighter.entities.Player;

public class Perso1 extends Player{

	private double xtir,ytir,norm;//variables pour le tir
	private int compt;

	public Perso1(World world){
		super(world);
		compt=500;
	}


	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) {
		super.render(container, game, g);
	}


	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) {
		super.update(container, game, delta);
		compt+=1;
	}



	public void tir(){


		xtir=Mouse.getX()-x-width/2;
		ytir=(720-Mouse.getY())-y-height/4;
		norm=Math.sqrt(xtir*xtir+ytir*ytir);
		xtir=1.2*xtir/norm;
		ytir=1.2*ytir/norm;
		world.createAlliedStraightProjectil(x+width/2, y+height/4, xtir, ytir);
		compt+=1;
	}



	//Souris***************************************************************
	public void mousePressed(int button, int x,int y){
		if(button==0){
			if(compt>=10){
				tir();
				compt=0;
			}
		}
	}

	public void mouseReleased(int button, int x,int y){
		leftclick=false;
	}

	public void mouseClicked(int button, int x, int y, int clickCount){
		leftclick=true;
	}




	public void keyReleased(int key, char c) {
		super.keyReleased(key, c);
	}

	public void keyPressed(int key, char c) {
		super.keyPressed(key, c);
	}
}
