package fr.main;


import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import fr.character.Player;
import fr.character.Player1;
import fr.character.Player2;
import fr.decor.Plateforms;

public class World extends BasicGameState {

	public static int ID = 0;
	private static Player1 Nico;
	private static Player2 Rafa;
	private static ArrayList<Plateforms> plateforms = null;
	public static StateBasedGame game;
	private static double x, y;
	//variables pour la creation de plateform x,y,deltaY,droite?gauche? width,oldwidth
	private static double deltaY;
	private static double width,oldwidth;
	private static double rand;
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		/*img=new Image("Images/reticule.png");
		arg0.setMouseCursor(img, 8, 8);*/

		World.game = arg1;
		plateforms = new ArrayList<Plateforms>();
		Nico = new Player1();
		Rafa = new Player2();
		newdecor();
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
		Nico.render(arg0, arg1, arg2);
		Rafa.render(arg0, arg1, arg2);
		for (int i = 0; i < plateforms.size(); i++)
			plateforms.get(i).render(arg0, arg1, arg2);
		arg2.drawString(""+Nico.getlife(), 1200, 20);
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		Nico.update(arg0, arg1, arg2);
		Rafa.update(arg0, arg1, arg2);
		for(int i=0;i<plateforms.size();i++){
			plateforms.get(i).update(arg0, arg1, arg2);
		}
	}

	//Souris*****************************************************************************
	/*public void mousePressed(int button,int x,int y){
		Nico.mousePressed(button,x,y);
	}*/


	@Override
	public int getID() {
		return ID;
	}

	public void keyReleased(int key, char c) {
		Nico.keyReleased(key, c);
		Rafa.keyReleased(key, c);
	}

	public void keyPressed(int key, char c) {
		Nico.keyPressed(key, c);
		Rafa.keyPressed(key, c);
		if (key == Input.KEY_ESCAPE) {
			System.exit(0);
		}
	}

	public static Player1 getPlayer1() {
		return Nico;
	}
	public static Player2 getPlayer2() {
		return Rafa;
	}


	public static ArrayList<Plateforms> getPlateforms() {
		return plateforms;
	}


	// Reset*****************************************************************************
	public static void reset() {
		Nico.reset();
		plateforms = new ArrayList<Plateforms>();
		newdecor();
	}


	// Creation des projectiles de maniere determinee****************************************
	/*public static void createStraightProjectil(double x, double y, double speedx, double speedy) {
		hostileprojectiles.add(new StraightProjectil(x, y, speedx, speedy));
	}

	public static void createAlliedStraightProjectil(double x, double y, double speedx, double speedy){
		alliedprojectiles.add(new AlliedStraightProjectil(x, y, speedx, speedy));
	}*/

	// Generation aleatoire de plateformes************************************************************
	public static void newdecor() {
		createPlateform(2000, 20,-400, 600);
		y=600;
		while (y>200){

			deltaY=70+Math.random()*(120-70);
			y-=deltaY;
			width=oldwidth;
			//On met une plateforme par ordonnée
			if ((x<100 && width< 200)||(x>1200)){
				width=350+Math.random()*(800-350);
			}else{
				width = 200+Math.random()*(800-200);
			}
			rand=Math.random();
			if(rand>0.5){
				x=(x+width-50)+Math.random()*(x+width+150-(x+width-50));
			}else{
				x=x-150-width+Math.random()*((x-width+50)-(x-150-width));
			}
			if (x>1200){x=800;}
			if(x+width<50){x=400;}
			createPlateform((float)width,(float)20,(float)x,(float)y);
		}
	}

	// Ajout d'une plateforme
	public static void createPlateform(float sizeX, float sizeY, float posX, float posY) {
		plateforms.add(new Plateforms(sizeX, sizeY, posX, posY));
	}
	/*public static void createRotationgPlateform() {
		plateforms.add(new RotatingPlateform(100, 20, 600, 150));
	}*/




}
