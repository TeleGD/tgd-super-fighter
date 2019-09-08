package fr.camera;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.util.Movable;

public class Camera extends Movable{

	public Camera(){
		this.x=tests.World2.getPlayer().getX();
		this.y=tests.World2.getPlayer().getY();
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.translate((float)container.getWidth() / 2 - (float)this.x,
	            container.getHeight() / 2 - (float)this.y);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		this.x=tests.World2.getPlayer().getspeedX();
		this.y=tests.World2.getPlayer().getspeedY();
	}
}
