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

import games.superFighter.character.Perso1;
import games.superFighter.character.Player;
import games.superFighter.character.ennemies.Boss1;
import games.superFighter.character.ennemies.Ennemy;
import games.superFighter.character.ennemies.Ennemy1;
import games.superFighter.character.ennemies.Ennemy2;
import games.superFighter.character.ennemies.Ennemy3;
import games.superFighter.character.ennemies.Ennemy4;
import games.superFighter.decor.Plateforms;
import games.superFighter.projectiles.AlliedProjectil;
import games.superFighter.projectiles.AlliedStraightProjectil;
import games.superFighter.projectiles.Explosiveballs;
import games.superFighter.projectiles.Projectile;
import games.superFighter.projectiles.StraightProjectil;;

public class World extends BasicGameState {

	private int ID;
	private int state;

	private static Player Nico;
	private static ArrayList<Plateforms> plateforms = null;
	private static ArrayList<Ennemy> ennemies = null;
	public static StateBasedGame game;
	private static double score;
	private static ArrayList<Projectile> hostileprojectiles;
	private static ArrayList<AlliedProjectil> alliedprojectiles;
	private static Plateforms plateform;
	private static int number;
	private static double x, y;
	private static double stretch, xorigine;
	private static double compt;
	private Image img;
	//variables pour la creation de plateform x,y,deltaY,droite?gauche? width,oldwidth
	private static double deltaY;
	private static double width,oldwidth;
	private static double rand,frameapparition;
	private static int palier;

	public World(int ID) {
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
		palier=0;
		frameapparition=300;
		img=AppLoader.loadPicture("/images/superFighter/reticule.png");
		try {
			arg0.setMouseCursor(img, 8, 8);
		} catch (SlickException exception) {}
		World.game = arg1;
		plateforms = new ArrayList<Plateforms>();
		ennemies = new ArrayList<Ennemy>();
		Nico = new Perso1();
		hostileprojectiles = new ArrayList<Projectile>();
		alliedprojectiles=new ArrayList<AlliedProjectil>();
		createEnnemy1(384, 0, 100, 384, 568); createEnnemy2(-100, 150, 150,	200);

		newdecor();
		newOne();
		newOne();
		newOne();
		newOne();
		compt = 0;
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
		for (int i = 0; i < plateforms.size(); i++)
			plateforms.get(i).render(arg0, arg1, arg2);
		for (int i = 0; i < ennemies.size(); i++)
			ennemies.get(i).render(arg0, arg1, arg2);
		for (int i = 0; i < hostileprojectiles.size(); i++) {
			hostileprojectiles.get(i).render(arg0, arg1, arg2);
		}
		for (int i = 0; i < alliedprojectiles.size(); i++) {
			alliedprojectiles.get(i).render(arg0, arg1, arg2);
		}
		arg2.drawString(""+Nico.getlife(), 1200, 20);
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) {
		/* Méthode exécutée environ 60 fois par seconde */
		Input input = arg0.getInput();
		if (input.isKeyDown(Input.KEY_ESCAPE)) {
			this.setState(1);
			game.enterState(2, new FadeOutTransition(), new FadeInTransition());
		}
		Nico.update(arg0, arg1, arg2);
		for(int i=0;i<plateforms.size();i++){
			plateforms.get(i).update(arg0, arg1, arg2);
		}
		for (int i = 0; i < ennemies.size(); i++)
			ennemies.get(i).update(arg0, arg1, arg2);
		compt += 1;
		for (int i = 0; i < hostileprojectiles.size(); i++) {
			hostileprojectiles.get(i).update(arg0, arg1, arg2);
			if (hostileprojectiles.get(i).isDestroyed()) {
				hostileprojectiles.remove(i);
			}
		}
		for (int i = 0; i < alliedprojectiles.size(); i++) {
			alliedprojectiles.get(i).update(arg0, arg1, arg2);
			if (alliedprojectiles.get(i).isDestroyed()) {
				alliedprojectiles.remove(i);
			}
		}
		if (compt >= frameapparition) {
			compt = 0;
			newOne();
		}

		if((score>500)&&(palier<1)){frameapparition/=1.5;palier+=1;}
		if((score>1500)&&(palier<2)){frameapparition/=1.7;palier+=1;}
		if((score>2500)&&(palier<3)){frameapparition/=1.5;palier+=1;}
		if((score>4000)&&(palier<4)){frameapparition/=1.2;palier+=1;}
		if((score>6000)&&(palier<5)){frameapparition*=100;
		palier+=1;
		ennemies.add(new Boss1(0,10));
		ennemies.add(new Boss1(700,10));
		ennemies.add(new Boss1(350,10));
		}
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

	//Souris*****************************************************************************
	public void mousePressed(int button,int x,int y){
		Nico.mousePressed(button,x,y);
	}

	public void keyReleased(int key, char c) {
		Nico.keyReleased(key, c);
	}

	public void keyPressed(int key, char c) {
		Nico.keyPressed(key, c);
	}

	public static Player getPlayer() {
		return Nico;
	}

	public static void setPlayer(Player playerP) {
		Nico = playerP;
	}

	public static ArrayList<Plateforms> getPlateforms() {
		return plateforms;
	}

	public static ArrayList<Ennemy> getEnemies() {
		return ennemies;
	}

	// Score*****************************************************************************
	public static void increaseScore(double variation) {
		score += variation;
	}

	public static double getScore() {
		return score;
	}

	// Reset*****************************************************************************
	public static void reset() {
		palier=0;
		frameapparition=120;
		hostileprojectiles = new ArrayList<Projectile>();
		Nico.reset();
		ennemies = new ArrayList<Ennemy>();
		plateforms = new ArrayList<Plateforms>();
		alliedprojectiles=new ArrayList<AlliedProjectil>();
		score = 0;
		//createRotationgPlateform();
		newdecor();
		newOne();
		newOne();
		newOne();
		newOne();
	}

	// G�n�ration d'ennemis**************************************************************

	public static void newOne() {
		// Choix de la plateforme pour positionner le nouvel ennemi
		plateform = plateforms.get((int) (Math.floor(Math.random() * plateforms.size()) % plateforms.size()));

		// choix du xorigine
		xorigine = plateform.getX() + 15 + (Math.random() * (plateform.getWidth() - 30));
		// Il faut xorigine dans l'�cran
		if ((xorigine < 0)&&(plateform.getX()+plateform.getWidth()>100)) {
			xorigine = 50;
		}
		if ((xorigine > 1200)&&(plateform.getX()<1100)) {
			xorigine = 1150;
		}
		// choix des coordonn�es pour la cr�ation d'ennemis
		if (Math.random() < 0.5) {
			if (plateform.getX() + plateform.getWidth() > 1200) {
				y = plateform.getY();
				x = plateform.getX() + 1200;
			} else if (plateform.getX() < -100) {
				y = plateform.getY();
				x = plateform.getX();
			} else {
				y = -300;
				x = 0;
			}
		} else {
			if (plateform.getX() < -100) {
				y = plateform.getY();
				x = plateform.getX();
			} else if (plateform.getX() + plateform.getWidth() > 1200) {
				y = plateform.getY();
				x = plateform.getX() + 1200;
			} else {
				y = -300;
				x = xorigine;
			}
		}

		// Choix du type d'ennemi
		number = (int) Math.floor(Math.random() * 7) % 7 + 1;
		if ((plateform.getX() > 0) && (plateform.getX() + plateform.getWidth() < 1200)) {
			stretch = Math.random() * Math.min(Math.abs(xorigine - plateform.getX()),
					Math.abs(xorigine - plateform.getX() - plateform.getWidth()));
		} else {
			stretch = Math.random() * Math.min(Math.abs(xorigine), Math.abs(xorigine - 700));
		}
		if (number<3) createEnnemy1((int) xorigine, (int) y, stretch, xorigine, plateform.getY() - 32);
		if((number >=3)&&(number<5)) createEnnemy2((int) x, (int) (plateform.getY() - 120), stretch, xorigine);
		if((number>=5)&&(number<7)){
			if (plateform.getY() > 200) {
				createEnnemy3((int) x, (int) y, stretch, xorigine);
			}
		}
		if(number==7) createEnnemy4((int) xorigine, (int) y, xorigine, plateform.getY() - 32);

	}

	// Creation des ennemis de maniere determinee
	public static void createEnnemy1(int xinit, int yinit, double stretch, double xorigine, double yorigine) {
		ennemies.add(new Ennemy1(xinit, yinit, stretch, xorigine, yorigine));
	}

	public static void createEnnemy2(int xinit, int yinit, double stretch, double xorigine) {
		ennemies.add(new Ennemy2(xinit, yinit, stretch, xorigine));
	}

	public static void createEnnemy3(int xinit, int yinit, double stretch, double xorigine) {
		ennemies.add(new Ennemy3(xinit, yinit, stretch, xorigine));
	}

	public static void createEnnemy4(int xinit, int yinit, double xorigine, double yorigine) {
		ennemies.add(new Ennemy4(xinit, yinit, xorigine, yorigine));
	}

	// Creation des projectiles de maniere determinee****************************************
	public static void createStraightProjectil(double x, double y, double speedx, double speedy) {
		hostileprojectiles.add(new StraightProjectil(x, y, speedx, speedy));
	}

	public static void createExplosiveBalls(double x,double y, double speedX, double speedY, double timeexp){
		hostileprojectiles.add(new Explosiveballs(x,y,speedX,speedY,timeexp));
	}

	public static void createAlliedStraightProjectil(double x, double y, double speedx, double speedy){
		alliedprojectiles.add(new AlliedStraightProjectil(x, y, speedx, speedy));
	}

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
