package games.superFighter.entities.projectiles;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import games.superFighter.World;
import games.superFighter.entities.Player;
import games.superFighter.entities.Projectile;

public class StraightProjectil extends Projectile {

	public StraightProjectil(World world,double x,double y,double speedX,double speedY){
		super(world, 5);
		this.x=x;
		this.y=y;
		this.speedX=speedX;
		this.speedY=speedY;
		this.destroyed=false;
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) {
		g.setColor(Color.cyan);
		g.fillOval((float)(x-radius), (float)(y-radius), (float)(2*radius), (float)(2*radius));
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) {
		newx=x+speedX*delta;
		newy=y+speedY*delta;
		if(colPlayer(world.getPlayer())){
			if(!world.getPlayer().isInvincible()){
				world.getPlayer().lifelost(game);
				this.destroyed=true;
			}
		}
		if((this.x>1300)||(this.x<-100)||(this.y>800)||(this.y<-100)){
			this.destroyed=true;
		}

		moveX(delta);
		moveY(delta);
	}


	public boolean colPlayer(Player player){
		if(player.getX()>this.newx+radius){return false;}

		if(player.getX()+player.getWidth()<this.newx-radius){return false;}

		if(player.getY()+player.getHeight()<this.getY()-radius){return false;}

		if(player.getY()>this.getY()+radius){return false;}
		return true;
	}

}
