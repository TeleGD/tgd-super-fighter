package games.superFighter.character.ennemies;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import games.superFighter.World;
import games.superFighter.character.Player;
import games.superFighter.util.Rectangle;

public class Ennemy2 extends Ennemy implements Rectangle {

	private double stretch;
	private boolean arrived;

	public Ennemy2(int x, int y, double stretch, double xorigine) {
		this.score = 60;
		this.x = x;
		this.xorigine = xorigine;
		this.y = y;
		this.height = 16;
		this.width = 32;
		this.speedX = 0.1;
		this.life = 1;
		this.stretch = stretch;
		this.arrived = false;
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) {
		g.setColor(Color.pink);
		g.fillRect((float) x, (float) y, (float) width, (float) height);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) {
		if (!arrived) {
			this.speedX = speedX * (xorigine - x) / Math.abs(xorigine - x);
			moveX(delta);
			if (Math.abs(xorigine - x) < 2) {
				arrived = true;
			}
		} else {

			if (Math.abs(this.x - this.xorigine) > stretch) {
				speedX *= -1;
			}
			moveX(delta);
		}
	}


	// Collision avec le joueur comme le premier ennemi
	public boolean playerwins(Player player) {
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
