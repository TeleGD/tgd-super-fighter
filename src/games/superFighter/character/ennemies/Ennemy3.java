package games.superFighter.character.ennemies;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import games.superFighter.World;
import games.superFighter.character.Player;
import games.superFighter.decor.Plateforms;
import games.superFighter.util.Rectangle;

public class Ennemy3 extends Ennemy implements Rectangle{

	private boolean ydone;
	private double stretch;
	private boolean arrived;

	public Ennemy3(int x, int y, double stretch, double xorigine) {
		this.score = 100;
		this.x = x;
		this.xorigine = xorigine-1;
		this.y = y-1;
		this.height = 32;
		this.width = 32;
		this.speedX = 0.2;
		this.speedY=0;
		this.life=1;
		this.gravity*=0.9;
		this.stretch = stretch;
		this.arrived = false;
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) {
		g.setColor(Color.yellow);
		g.fillRect((float) x, (float) y, (float) width, (float) height);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) {
		if (!arrived) {
			this.speedX = 0.2 * (xorigine - x) / Math.abs(xorigine - x);
			moveX(delta);
			if (Math.abs(xorigine - x) < 2) {
				arrived = true;
			}
		} else {



		//Mouvements en X
		if (Math.abs(this.x - this.xorigine) > stretch) {
			speedX *= -1;
		}
		moveX(delta);
		}
		//Mouvement en Y
		ydone=false;
		this.newy = y + speedY * delta;
		this.speedY += accelY;
		for (int i=0; i<World.getPlateforms().size();i++){
			if(colPlat(World.getPlateforms().get(i))){
				this.speedY=0;
				this.accelY=-this.jumppower;
				this.y=World.getPlateforms().get(i).getY()-this.height;
				ydone=true;
				//System.out.println("bonjour");
			}
		}
		if(!ydone){
			this.accelY=gravity;
		}
		this.speedY+=this.accelY;
		moveY(delta);
	}



	private boolean colPlat(Plateforms plat){

		if(this.getspeedY()<0){return false;}
		if(this.getY()+this.getHeight()<plat.getY()){return false;}
		if(this.getnewY()>plat.getY()){return false;}
		if(this.getX()>plat.getX()+plat.getWidth()){return false;}
		if(this.getX()+this.getWidth()<plat.getX()){return false;}
		return true;
	}

	public boolean playerwins(Player player) {
		//coll par dessus=rebond (et destruction de l'ennemi)

		if(player.getspeedY()<=0){return false;}
		if(player.getnewY()+player.getHeight()<this.y){return false;}
		if(player.getY()>this.y){return false;}
		if(player.getX()>this.x+this.width){return false;}
		if(player.getX()+player.getWidth()<this.x){return false;}
		return true;
	}

	public boolean playerloose(Player player){
		if(player.getX()+player.getWidth()<this.x){return false;}
		if(player.getX()>this.x+this.width){return false;}
		if(player.getY()>this.y+this.height){return false;}
		if(player.getY()+player.getHeight()<this.y){return false;}
		return true;
	}

	public void collPlayer(Player player){
		if (playerwins(player)){
			this.life-=1;
			player.jump();
			if(life==0){
				World.increaseScore(this.score);
			}
		}else if (playerloose(player)){
			player.lifelost();
		}
	}

}
