package games.superFighter;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import games.superFighter.World2;
import games.superFighter.util.Movable;

public class Camera extends Movable{

	private World2 world2;

	public Camera(World2 world2){
		this.world2 = world2;
		this.x=world2.getPlayer().getX();
		this.y=world2.getPlayer().getY();
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) {
		g.translate((float)container.getWidth() / 2 - (float)this.x,
	            container.getHeight() / 2 - (float)this.y);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) {
		this.x=world2.getPlayer().getspeedX();
		this.y=world2.getPlayer().getspeedY();
	}
}
