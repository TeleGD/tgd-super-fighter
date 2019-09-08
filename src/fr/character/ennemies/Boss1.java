package fr.character.ennemies;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.util.Rectangle;

public class Boss1 extends Ennemy implements Rectangle{
	private int compt;
	private double xtir,ytir,norm;
	public Boss1(double x,double y){
		this.life=20;
		this.score=500;
		this.x=x;
		this.y=y;
		this.height=30;
		this.width=150;
		compt=0;
	}
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.setColor(Color.pink);
		g.fillRect((float)x, (float)y, (float)width, (float)height);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		compt+=1;
		if(compt>50){
			compt=0;
			this.xtir=(fr.main.World.getPlayer().getnewX()-(x+this.width/4));
			this.ytir=(fr.main.World.getPlayer().getnewY()-(y+height/2));
			norm=Math.sqrt(xtir*xtir+ytir*ytir);
			xtir=0.3*xtir/norm;
			ytir=0.3*ytir/norm;
			fr.main.World.createExplosiveBalls((this.x+this.width/4),this.y+this.height/2, xtir, ytir, 500);
			this.xtir=(fr.main.World.getPlayer().getnewX()-(x+3*this.width/4));
			this.ytir=(fr.main.World.getPlayer().getnewY()-(y+height/2));
			norm=Math.sqrt(xtir*xtir+ytir*ytir);
			xtir=0.3*xtir/norm;
			ytir=0.3*ytir/norm;
			fr.main.World.createExplosiveBalls((this.x+3*this.width/4),this.y+this.height/2, xtir, ytir, 500);
		}
	}

}
