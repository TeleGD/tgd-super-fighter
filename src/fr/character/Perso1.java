package fr.character;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Perso1 extends Player{

	private double xtir,ytir,norm;//variables pour le tir
	private int compt;

	public Perso1(){
		super();
		compt=500;
	}


	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		super.render(container, game, g);
	}


	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		super.update(container, game, delta);
		compt+=1;
	}



	public void tir(){


		xtir=Mouse.getX()-x-width/2;
		ytir=(720-Mouse.getY())-y-height/4;
		norm=Math.sqrt(xtir*xtir+ytir*ytir);
		xtir=1.2*xtir/norm;
		ytir=1.2*ytir/norm;
		fr.main.World.createAlliedStraightProjectil(x+width/2, y+height/4, xtir, ytir);
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
