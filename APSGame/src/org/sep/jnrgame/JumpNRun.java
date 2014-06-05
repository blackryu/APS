package org.sep.jnrgame;

import org.sep.framework.Framework;
import org.sep.framework.Game;
import org.sep.framework.Screen;


public class JumpNRun extends Game {

	public static void main(String[] argv) {
		Game game = new JumpNRun();

		Framework.getInstance().setGame(game);

		Framework.getInstance().start();
	}

	@Override
	public String getName() {
		return "Jump 'n Run";
	}

	@Override
	public Screen getStartScreen() {
		return new StartScreen();
	}
}
