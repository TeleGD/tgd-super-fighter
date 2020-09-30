package games.superFighter.character;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;

import games.superFighter.World2;
import games.superFighter.util.Movable;
import games.superFighter.util.Rectangle;

public class Playertest extends Movable implements Rectangle{

	protected double width,height;
	private boolean upPress,downPress,rightPress,leftPress,droitegauche,hautbas;
	private int cameraX,cameraY;
	private int bas=600;
	private boolean colplat;// y a t il eu une coll avec une plateforme a la
	// derniere frame
	private boolean vertcolthis;// y a t il eu col avec une plateforme a cette
	// frame
	private double xvid,yvid;
	public Playertest(){
		this.width=32;
		this.height=32;
		this.xvid=400;
		this.yvid=400;
		this.dir=0;
		this.cameraX=0;
		this.jumppower*=1.7;
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
		g.drawString(""+x+" "+y, (float)x+10, (float)y-15);
		g.fillRect((float)x, (float)y, (float)width, (float)height);
		g.translate(-cameraX,0);
		if(cameraX<0) g.translate(0, 0);
		g.drawString(""+cameraX,cameraX+500, 100);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) {
		horizontalMove();
		verticalMove();
		moveX(delta);
		moveY(delta);
		this.cameraX-=-speedX*delta;



	}



	private void horizontalMove() {
		speedX = 0;
		dir=0;
		if ((leftPress && !rightPress) || (leftPress && rightPress && !droitegauche)) {
			if (x > 0) {
				dir=-1;
				speedX = -0.5;
			}

		}
		if ((!leftPress && rightPress) || (leftPress && rightPress && droitegauche)) {
			if (x < 5000) {
				dir=1;
				speedX = 0.5;
			}
		}
	}

	public void verticalMove() {
		this.vertcolthis = false;
		posjump = false;
		for (int i = 0; i < World2.getPlateforms().size(); i++) {
			if ((World2.getPlateforms().get(i).collPlayer(this)) && (!downPress)) {
				this.y = World2.getPlateforms().get(i).getY() - this.height;
				this.accelY = 0;
				this.speedY = 0;
				colplat = true;
				vertcolthis = true;
				posjump = true;
			}
		}
		/*if (!invincible) {
			for (int i = 0; i < World.getEnemies().size(); i++) {
				World.getEnemies().get(i).collPlayer(this);
				if (World.getEnemies().get(i).getLife() == 0) {
					World.getEnemies().remove(i);
				}
			}
		}*/
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



}
