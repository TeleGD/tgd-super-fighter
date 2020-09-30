package games.superFighter.entities.ennemies;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;

import app.AppLoader;

import games.superFighter.World;

public class Ennemy4 extends Ennemy1{

//	private double x, y, xorigine, yorigine;
	private boolean arrived=false;
	private double compt;
	private Image img;
	//Paramètres de tir
	public double xtir,ytir,norm;
	//On prend une vitesse de projectiles de 0.3

	public Ennemy4(World world, int x, int y, double xorigine, double yorigine) {
		super(world,x,y,0,xorigine,yorigine);
		img=AppLoader.loadPicture("/images/superFighter/Images ennemis/Ennemytourelles/mage.png");
		this.score = 300;
		this.speedX = 0.1;
		this.speedY=0;
		this.life = 2;
		this.arrived = false;
		this.compt=0;
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) {
		g.drawString(""+life, (float)(x+4), (float)(y-20));
		g.drawImage(img, (float)x, (float)y);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) {
		if (!arrived) {
			if (x != xorigine) {
				this.speedX = speedX * (xorigine - x) / Math.abs(xorigine - x);
				moveX(delta);
				if (Math.abs(xorigine - x) < 2) {
					this.x = xorigine;
				}
			}
			if (y != yorigine) {
				this.speedY = 0.1 * (yorigine - y) / Math.abs(yorigine - y);
				y+=delta*speedY;
				if (Math.abs(yorigine - y) < 5) {
					this.y = yorigine;
				}

			}

			if (x == xorigine && y == yorigine) {
				arrived = true;
			}
		}
		compt+=1;
		if(compt>50){
			compt=0;
			this.xtir=(world.getPlayer().getnewX()-(x+this.width/2));
			this.ytir=(world.getPlayer().getnewY()-(y+height/4));
			norm=Math.sqrt(xtir*xtir+ytir*ytir);
			xtir=0.3*xtir/norm;
			ytir=0.3*ytir/norm;
			world.createStraightProjectil((this.x+this.width/2),this.y+this.height/4, xtir, ytir);
		}

	}

}
