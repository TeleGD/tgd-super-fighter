package games.superFighter.entities.projectiles;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import games.superFighter.World;

public class Explosiveballs extends StraightProjectil {
//Balles qui tirent 3 autre balles après un certain temps

	private double time;
	private double timeexp;

	public Explosiveballs(World world,double x,double y,double speedX,double speedY, double timeexp){
		super(world,x,y,speedX,speedY);
		this.time=System.currentTimeMillis();
		this.timeexp=timeexp;
	}

	//@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) {
		arg2.setColor(Color.cyan);
		arg2.fillOval((float)(x-radius), (float)(y-radius), (float)(2*radius), (float)(2*radius));
	}
	//@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) {
		newx=x+speedX*arg2;
		newy=y+speedY*arg2;
		if(colPlayer(world.getPlayer())){
			if(!world.getPlayer().isInvincible()){
				world.getPlayer().lifelost(arg1);
				this.destroyed=true;
			}
		}
		if((this.x>1300)||(this.x<-100)||(this.y>800)||(this.y<-100)){
			this.destroyed=true;
		}

		moveX(arg2);
		moveY(arg2);
		if(System.currentTimeMillis()-time>500){
			double a=(Math.sqrt(2)/2*(speedX-speedY))/Math.sqrt(Math.pow(speedX,2)+Math.pow(speedY,2));
			double b=Math.sqrt(2)/2*(speedX+speedY)/Math.sqrt(Math.pow(speedX,2)+Math.pow(speedY,2));
			double c=Math.pow(a, 2)+Math.pow(b, 2);
			a=a/c;
			b=b/c;
			a=0.3*a;
			b=0.3*b;
			world.createStraightProjectil(this.x,this.y,speedX,this.speedY);
			world.createStraightProjectil(this.x,this.y,a,b);
			world.createStraightProjectil(this.x,this.y,b,-a);
			this.destroyed=true;
		}
	}

	@Override
	public double getRadius() {
		return radius;
	}
	public boolean isDestroyed(){
		return destroyed;
	}

}
