package tests;
import java.io.File;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Main2 extends StateBasedGame {


	public static void main(String[] args) throws SlickException {

		System.setProperty("org.lwjgl.librarypath", new File("natives").getAbsolutePath());
		AppGameContainer app = new AppGameContainer(new Main2(),1280,720,false);
		app.setTargetFrameRate(60);
		app.setVSync(true);
		app.setShowFPS(true);
		app.start();
	}





	public Main2() {
		super("Ouep");
		}



		@Override
		public void initStatesList(GameContainer container) throws SlickException {
			addState(new World2());
		}


	}
