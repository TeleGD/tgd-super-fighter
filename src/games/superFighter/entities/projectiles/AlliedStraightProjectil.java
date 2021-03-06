package games.superFighter.entities.projectiles;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import games.superFighter.World;
import games.superFighter.entities.Ennemy;
import games.superFighter.entities.Projectile;

public class AlliedStraightProjectil extends Projectile {

	public AlliedStraightProjectil(World world,double x, double y, double speedX, double speedY) {
		super(world, 4);
		this.x=x;
		this.y=y;
		this.speedY=speedY;
		this.speedX=speedX;
		this.destroyed=false;
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) {
		g.setColor(Color.red);
		g.fillOval((float)(x-radius), (float)(y-radius), (float)(2*radius), (float)(2*radius));
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) {
		newx=x+speedX*delta;
		newy=y+speedY*delta;
		for(int i=0;i<world.getEnemies().size();i++){
			if(colEnnemy(world.getEnemies().get(i))){
				world.getEnemies().get(i).loselife();
				if(world.getEnemies().get(i).getLife()==0){
					world.getEnemies().get(i).scoring();
					world.getEnemies().remove(i);
				}
				this.destroyed=true;
			}
		}
		if((this.x>1300)||(this.x<-100)||(this.y>800)||(this.y<-100)){
			this.destroyed=true;
		}

		moveX(delta);
		moveY(delta);
	}


	/*public boolean colEnnemy(Ennemy enemy){
		if(enemy.getX()>this.newx+radius){return false;}

		if(enemy.getX()+enemy.getWidth()<this.newx-radius){return false;}

		if(enemy.getY()+enemy.getHeight()<this.getY()-radius){return false;}

		if(enemy.getY()>this.getY()+radius){return false;}
		return true;
	}*/
	public boolean colEnnemy(Ennemy enemy){
		if((this.x+radius<enemy.getX())&&(this.newx+radius<enemy.getX())){return false;}
		if((this.x-radius>enemy.getX()+enemy.getWidth())&&(this.newx-radius>enemy.getX()+enemy.getWidth())){return false;}
		if((this.y+radius<enemy.getY())&&(this.newy+radius<enemy.getY())){return false;}
		if((this.y-radius>enemy.getY()+enemy.getHeight())&&(this.newy-radius>enemy.getY()+enemy.getHeight())){return false;}

		return true;
	}

}
