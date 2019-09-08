package tests;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import fr.character.Perso1;
import fr.character.Player;
import fr.character.ennemies.Ennemy;
import fr.decor.Plateforms;
import fr.projectiles.AlliedProjectil;
import fr.projectiles.Projectile;

public class World2  extends BasicGameState {

	public static int ID = -1;
	private static Playertest Nico;

	private static ArrayList<Plateforms> plateforms = null;
	private static ArrayList<Ennemy> ennemies = null;
	public static StateBasedGame game;
	private static ArrayList<Projectile> hostileprojectiles;
	private static ArrayList<AlliedProjectil> alliedprojectiles;
	private Image img;

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		Nico=new Playertest();
		img=new Image("Images/reticule.png");
		arg0.setMouseCursor(img, 8, 8);

		plateforms=new ArrayList<Plateforms>();
		ennemies=new ArrayList<Ennemy>();
		hostileprojectiles=new ArrayList<Projectile>();
		alliedprojectiles=new ArrayList<AlliedProjectil>();



		plateforms.add(new Plateforms(2000,20,-20,600));
		plateforms.add(new Plateforms(100,20,500,200));
		plateforms.add(new Plateforms(300,20,900,400));
		plateforms.add(new Plateforms(300,20,1500,100));
		plateforms.add(new Plateforms(2000,20,-20,-500));
		plateforms.add(new Plateforms(150,20,150,-1000));
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
		Nico.render(arg0, arg1, arg2);
		for (int i=0;i<plateforms.size();i++){
			plateforms.get(i).render(arg0, arg1, arg2);
		}
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		Nico.update(arg0, arg1, arg2);

	}


	@Override
	public int getID() {
		return ID;
	}

	public void keyReleased(int key, char c) {
		Nico.keyReleased(key, c);
	}

	public void keyPressed(int key, char c) {
		Nico.keyPressed(key, c);
		if (key == Input.KEY_ESCAPE) {
			System.exit(0);
		}
	}

	//Getters**********************************************************
	public static Playertest getPlayer() {
		return Nico;
	}

	public static ArrayList<Plateforms> getPlateforms() {
		return plateforms;
	}

	public static ArrayList<Ennemy> getEnemies() {
		return ennemies;
	}

}
