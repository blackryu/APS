package org.sep.framework;

import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;

/**
 * A Window that serves as render target for a Game.
 *
 * @author David Marquant
 */
public class GameWindow extends JFrame {

	/**
	 * Creates a GameWindow.
	 */
	GameWindow() {
		setResizable(false);
		setIgnoreRepaint(true);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * Get the AWT Graphics context.
	 *
	 * @return AWT graphics context
	 */
	public Graphics2D getGraphicsContext() {
		BufferStrategy bs = getBufferStrategy();
		return (Graphics2D) bs.getDrawGraphics();
	}

	/**
	 * Swaps the back buffer with the front buffer.
	 */
	public void swapBuffer() {
		BufferStrategy bs = getBufferStrategy();

		if (!bs.contentsLost()) {
			bs.show();
		}
	}

	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);

		// Enable double buffering
		if (visible) {
			createBufferStrategy(2);
		}
	}
}
