package games.superFighter.menus;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import games.superFighter.World;

public class MenuFinPartie extends BasicGameState {

	private int ID;
	private int state;

	GameContainer container;
	StateBasedGame game;

	public MenuFinPartie(int ID) {
		this.ID = ID;
		this.state = 0;
	}

	@Override
	public int getID() {
		return this.ID;
	}

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) {
		container=arg0;
		game=arg1;
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) {

		arg2.drawString("Tu as perdu, ton score n'est que de ", 500, 300);
		arg2.drawString(""+World.getScore(),600,400);
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) {

	}

	@Override
	public void keyPressed(int key, char c) {
		switch (key) {
		case Input.KEY_ENTER:
			game.enterState(1 /* Choice */, new FadeOutTransition(),
					new FadeInTransition());
			break;

		case Input.KEY_ESCAPE:
			this.container.exit();
			break;
		}
	}

}
