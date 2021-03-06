package games.superFighter;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import app.AppLoader;

import games.superFighter.Plateforms;
import games.superFighter.entities.Ennemy;
import games.superFighter.entities.Playertest;
import games.superFighter.entities.Projectile;

public class World2  extends BasicGameState {

	private int ID;
	private int state;

	private Playertest Nico;

	private ArrayList<Plateforms> plateforms = null;
	private ArrayList<Ennemy> ennemies = null;
	private ArrayList<Projectile> hostileprojectiles;
	private ArrayList<Projectile> alliedprojectiles;
	private Image img;

	public World2(int ID) {
		this.ID = ID;
		this.state = 0;
	}

	@Override
	public int getID() {
		return this.ID;
	}

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) {
		/* Méthode exécutée une unique fois au chargement du programme */
		Nico=new Playertest(this);
		img=AppLoader.loadPicture("/images/superFighter/reticule.png");
		try {
			arg0.setMouseCursor(img, 8, 8);
		} catch (SlickException exception) {}

		plateforms=new ArrayList<Plateforms>();
		ennemies=new ArrayList<Ennemy>();
		hostileprojectiles=new ArrayList<Projectile>();
		alliedprojectiles=new ArrayList<Projectile>();



		plateforms.add(new Plateforms(2000,20,-20,600));
		plateforms.add(new Plateforms(100,20,500,200));
		plateforms.add(new Plateforms(300,20,900,400));
		plateforms.add(new Plateforms(300,20,1500,100));
		plateforms.add(new Plateforms(2000,20,-20,-500));
		plateforms.add(new Plateforms(150,20,150,-1000));
	}

	@Override
	public void enter(GameContainer container, StateBasedGame game) {
		/* Méthode exécutée à l'apparition de la page */
		if (this.state == 0) {
			this.play(container, game);
		} else if (this.state == 2) {
			this.resume(container, game);
		}
	}

	@Override
	public void leave(GameContainer container, StateBasedGame game) {
		/* Méthode exécutée à la disparition de la page */
		if (this.state == 1) {
			this.pause(container, game);
		} else if (this.state == 3) {
			this.stop(container, game);
			this.state = 0; // TODO: remove
		}
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) {
		/* Méthode exécutée environ 60 fois par seconde */
		Nico.render(arg0, arg1, arg2);
		for (int i=0;i<plateforms.size();i++){
			plateforms.get(i).render(arg0, arg1, arg2);
		}
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) {
		/* Méthode exécutée environ 60 fois par seconde */
		Input input = arg0.getInput();
		if (input.isKeyDown(Input.KEY_ESCAPE)) {
			this.setState(0);
			arg1.enterState(1, new FadeOutTransition(), new FadeInTransition());
		}
		Nico.update(arg0, arg1, arg2);

	}

	public void play(GameContainer container, StateBasedGame game) {
		/* Méthode exécutée une unique fois au début du jeu */
	}

	public void pause(GameContainer container, StateBasedGame game) {
		/* Méthode exécutée lors de la mise en pause du jeu */
	}

	public void resume(GameContainer container, StateBasedGame game) {
		/* Méthode exécutée lors de la reprise du jeu */
	}

	public void stop(GameContainer container, StateBasedGame game) {
		/* Méthode exécutée une unique fois à la fin du jeu */
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getState() {
		return this.state;
	}

	public void keyReleased(int key, char c) {
		Nico.keyReleased(key, c);
	}

	public void keyPressed(int key, char c) {
		Nico.keyPressed(key, c);
	}

	//Getters**********************************************************
	public Playertest getPlayer() {
		return Nico;
	}

	public ArrayList<Plateforms> getPlateforms() {
		return plateforms;
	}

	public ArrayList<Ennemy> getEnemies() {
		return ennemies;
	}

}
