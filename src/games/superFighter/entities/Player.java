package games.superFighter.entities;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import games.superFighter.World;
import games.superFighter.util.Movable;
import games.superFighter.util.Rectangle;

public abstract class Player extends Movable implements Rectangle {

	protected World world;
	protected double width, height;
	private boolean colplat;// y a t il eu une coll avec une plateforme a la
	// derniere frame
	private boolean vertcolthis;// y a t il eu col avec une plateforme a cette
	// frame
	private boolean upPress, leftPress, rightPress, droitegauche, downPress;
	private int bas = 600;
	private int life;
	private boolean invincible=true;// le joueur est invincible (après avoir perdu
	// une vie par exemple)
	private long timeInvincibleDying=3000;// temps d'invincibilité après une mort
	private long timeOfDeath;
	private double comptInvincible;//compteur servant au clignotement durant l'invincibilité après la mort
	protected boolean leftclick=false;

	public Player(World world) {
		this.world = world;
		this.x = 150;
		this.y = 0;// 250-32;
		this.width = 16;
		this.height = 32;
		this.speedX = 0;
		this.speedY = 0;
		this.accelY = gravity;
		this.accelX = 0;
		this.colplat = false;
		this.invincible = false;
		this.timeOfDeath = -3000;
		this.life=3;
	}

	@Override
	public double getWidth() {
		return width;
	}

	@Override
	public double getHeight() {
		return height;
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) {
		if((System.currentTimeMillis()-this.timeOfDeath<this.timeInvincibleDying)&&(this.comptInvincible>=5)){
			if((this.comptInvincible>=10)){
				this.comptInvincible=0;
			}
		}else{
			g.setColor(Color.green);
			g.fillRect((float) x, (float) y, (float) width, (float) height);
		}
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) {
		if(System.currentTimeMillis()-this.timeOfDeath<this.timeInvincibleDying){invincible=true;}else{invincible=false;}
		if(!invincible){this.comptInvincible=0;}else{this.comptInvincible+=1;}

		horizontalMove();
		moveX(delta);
		this.newx = x + speedX * delta;
		this.newy = y + speedY * delta;
		this.speedY += accelY;
		verticalMove(game);
		moveY(delta);

	}

	// Mouvemnts************************************************************************
	private void horizontalMove() {
		speedX = 0;
		if ((leftPress && !rightPress) || (leftPress && rightPress && !droitegauche)) {
			if (x > 0) {
				speedX = -0.5;
			}

		}
		if ((!leftPress && rightPress) || (leftPress && rightPress && droitegauche)) {
			if (x < 1280 - width) {

				speedX = 0.5;
			}
		}
	}

	public void verticalMove(StateBasedGame game) {
		this.vertcolthis = false;
		posjump = false;
		for (int i = 0; i < world.getPlateforms().size(); i++) {
			if ((world.getPlateforms().get(i).collPlayer(this)) && (!downPress)) {
				this.y = world.getPlateforms().get(i).getY() - this.height;
				this.accelY = 0;
				this.speedY = 0;
				colplat = true;
				vertcolthis = true;
				posjump = true;
			}
		}
		if (!invincible) {
			for (int i = 0; i < world.getEnemies().size(); i++) {
				world.getEnemies().get(i).collPlayer(game, this);
				if (world.getEnemies().get(i).getLife() == 0) {
					world.getEnemies().remove(i);
				}
			}
		}
		if (isTooLow()) {
			y = bas - height;
			speedY = 0;
			vertcolthis = true;
			posjump = true;
		}
		if (posjump && upPress) {
			jump();
		}
		if (!vertcolthis) {
			this.accelY = gravity;
			colplat = false;
		}
		this.speedY += this.accelY;
	}

	public double getnewY() {
		return newy;
	}

	public double getnewX() {
		return newx;
	}

	public boolean getcolplat() {
		return this.colplat;
	}

	public void jump() {
		this.speedY = 0;
		this.accelY = -this.jumppower;
		this.vertcolthis = true;
	}

	private boolean isTooLow() {
		if (speedY < 0) {
			return false;
		}
		if (newy + height < bas) {
			return false;
		}
		return true;
	}

	// Les touches*******************************************************
	public void keyReleased(int key, char c) {

		switch (key) {
		case Input.KEY_Z:
			upPress = false;
			break;

		case Input.KEY_S:
			downPress = false;
			break;

		case Input.KEY_Q:
			leftPress = false;
			break;

		case Input.KEY_D:
			rightPress = false;
			break;
		}
	}

	public void keyPressed(int key, char c) {
		switch (key) {
		case Input.KEY_Z:
			upPress = true;
			break;

		case Input.KEY_S:
			downPress = true;
			break;

		case Input.KEY_Q:
			leftPress = true;
			droitegauche = false;
			break;
		case Input.KEY_D:
			rightPress = true;
			droitegauche = true;
			break;
		}
	}

	//souris**********************************************************************
	public void mousePressed(int button, int x,int y){
	}

	// Autres
	// **************************************************************************
	public void lifelost(StateBasedGame game) {
		this.life -= 1;
		this.comptInvincible=0;
		this.timeOfDeath = System.currentTimeMillis();
		if (life == 0) {
			game.enterState(5 /* MenuFinPartie */, new FadeOutTransition(),
					new FadeInTransition());
		}
	}

	public boolean isInvincible(){
		return invincible;
	}

	public int getlife(){
		return life;
	}

	public void reset(){
		this.x = 150;
		this.y = 0;// 250-32;
		this.width = 16;
		this.height = 32;
		this.speedX = 0;
		this.speedY = 0;
		this.accelY = gravity;
		this.accelX = 0;
		colplat = false;
		this.invincible = false;
		this.timeOfDeath = -3000;
		this.life=3;
	}
}
