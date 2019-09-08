package fr.character;

import org.newdawn.slick.Input;

public class Player2 extends Player{


	public Player2(){
		this.height=32;
		this.width=16;
		this.y=600-height;
		this.x=1100;
		this.speedX = 0;
		this.speedY = 0;
		this.accelY = gravity;
		this.accelX = 0;
		this.colplat = false;
		this.invincible = false;
		this.timeOfDeath = -3000;
		this.life=3;
	}

	//touches
	public void keyReleased(int key, char c) {

		switch (key) {
		case Input.KEY_UP:
			upPress = false;
			break;

		case Input.KEY_DOWN:
			downPress = false;
			break;

		case Input.KEY_LEFT:
			leftPress = false;
			break;

		case Input.KEY_RIGHT:
			rightPress = false;
			break;
		}
	}

	public void keyPressed(int key, char c) {
		switch (key) {
		case Input.KEY_UP:
			upPress = true;
			break;

		case Input.KEY_DOWN:
			downPress = true;
			break;

		case Input.KEY_LEFT:
			leftPress = true;
			droitegauche = false;
			break;
		case Input.KEY_RIGHT:
			rightPress = true;
			droitegauche = true;
			break;
		}
	}



	//Mvt vertical
	public void verticalMove() {
		this.vertcolthis = false;
		posjump = false;
		for (int i = 0; i < fr.main.World.getPlateforms().size(); i++) {
			if ((fr.main.World.getPlateforms().get(i).collPlayer(this)) && (!downPress)) {
				this.y = fr.main.World.getPlateforms().get(i).getY() - this.height;
				this.accelY = 0;
				this.speedY = 0;
				colplat = true;
				vertcolthis = true;
				posjump = true;
			}
		}
		if (!invincible) {
			if(fr.util.collisions.isCollisionPlayerFromAbove(this, fr.main.World.getPlayer1())){
				this.speedY=0;
				this.accelY=-jumppower;
				this.vertcolthis = true;
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
}
