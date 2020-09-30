package games.superFighter.character.ennemies;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;

import app.AppLoader;

import games.superFighter.World;
import games.superFighter.character.Player;
import games.superFighter.util.Rectangle;

public class Ennemy1 extends Ennemy implements Rectangle {

	protected double stretch, yorigine;
	private boolean arrived;
	private Image imagegauchePD, imagegauchestop, imagedroitestop, imagegauchePG, imagecentrale, imagedroitePD,
			imagedroitePG, image;
	private int lastimg;
	private int compteur;

	public Ennemy1(int x, int y, double stretch, double xorigine, double yorigine) {
		this.compteur = 0;
		this.score = 50;
		this.x = x;
		this.xorigine = xorigine;
		this.yorigine = yorigine;
		this.y = y;
		this.height = 32;
		this.width = 16;
		this.speedX = 0;
		this.life = 1;
		this.stretch = stretch;
		this.arrived = false;
		this.imagecentrale = AppLoader.loadPicture("/images/superFighter/Images ennemis/ennemi1imgcentrale.png");
		this.imagegauchePD = AppLoader.loadPicture("/images/superFighter/Images ennemis/ennemy1gauchePD.png");
		this.imagegauchePG = AppLoader.loadPicture("/images/superFighter/Images ennemis/ennemy1gauchePG.png");
		this.imagegauchestop = AppLoader.loadPicture("/images/superFighter/Images ennemis/ennemy1gauchestop.png");
		this.imagedroitePD = AppLoader.loadPicture("/images/superFighter/Images ennemis/ennemy1droitePD.png");
		this.imagedroitePG = AppLoader.loadPicture("/images/superFighter/Images ennemis/ennemy1droitePG.png");
		this.imagedroitestop = AppLoader.loadPicture("/images/superFighter/Images ennemis/ennemy1droitestop.png");
		this.image = imagecentrale;
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) {
		g.drawImage(image, (float) x - 12, (float) y - 4);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) {
		if (!arrived) {
			if (x != xorigine) {
				this.speedX = speedX * (xorigine - x) / Math.abs(xorigine - x);
				moveX(delta);

			}
			if (Math.abs(xorigine - x) < 2) {
				this.x = xorigine;
			}
			if (y != yorigine) {
				this.speedY = 0.3 * (yorigine - y) / Math.abs(yorigine - y);
				moveY(delta);
			}
			if (Math.abs(yorigine - y) < 5) {
				this.y = yorigine;
			}
			if (x == xorigine && y == yorigine) {
				arrived = true;
				speedX=0.1;
			}
		} else {

			if (Math.abs(this.x - this.xorigine) > stretch) {
				speedX *= -1;
				this.image=chooseimg();
			}

			moveX(delta);
		}
		if (compteur >= 10) {
			this.image = chooseimg();
			compteur = 0;
		} else {
			compteur += 1;
		}
	}

	/*
	 * collisions avec le joueur le joueur tue si il arrive par le haut et il
	 * saute le joueur moeurs si il arrive par autre part
	 */
	public boolean playerwins(Player player) {
		// coll par dessus=rebond (et destruction de l'ennemi)

		if (player.getspeedY() <= 0) {
			return false;
		}
		if (player.getnewY() + player.getHeight() < this.y) {
			return false;
		}
		if (player.getY() > this.y) {
			return false;
		}
		if (player.getX() > this.x + this.width) {
			return false;
		}
		if (player.getX() + player.getWidth() < this.x) {
			return false;
		}
		return true;
	}

	// gestion image, voir si il faut pas la mettre dan mere
	private Image chooseimg() {
		if(this.speedX==0){return imagecentrale;}
		if (this.speedX > 0) {
			if (this.image.equals(imagedroitePD)) {
				this.lastimg = 4;
				return imagedroitestop;
			} else if (this.image.equals(imagedroitePG)) {
				this.lastimg = 7;
				return imagedroitestop;
			} else {
				if (lastimg == 4) {
					return imagedroitePG;
				} else {
					return imagedroitePD;
				}
			}
		} else if (this.speedX < 0) {
			if (this.image.equals(imagegauchePD)) {
				this.lastimg = 4;
				return imagegauchestop;
			} else if (this.image.equals(imagegauchePG)) {
				this.lastimg = 7;
				return imagegauchestop;
			} else {
				if (lastimg == 4) {
					return imagegauchePG;
				} else {
					return imagegauchePD;
				}
			}
		}
		return imagecentrale;
	}

	public boolean playerloose(Player player) {
		if (player.getX() + player.getWidth() < this.x) {
			return false;
		}
		if (player.getX() > this.x + this.width) {
			return false;
		}
		if (player.getY() > this.y + this.height) {
			return false;
		}
		if (player.getY() + player.getHeight() < this.y) {
			return false;
		}
		return true;
	}

	public void collPlayer(Player player) {
		if (playerwins(player)) {
			this.life -= 1;
			player.jump();
			if (life == 0) {
				World.increaseScore(this.score);
			}
		} else if (playerloose(player)) {
			player.lifelost();
		}
	}

}
