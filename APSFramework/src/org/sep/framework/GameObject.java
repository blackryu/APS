package org.sep.framework;

import org.sep.drawable.Drawable;

/**
 * An extension point for GameObjects.
 *
 * @author David Marquant
 */
public abstract class GameObject implements Updateable, Drawable {

	/**
	 * Initializes the GameObject.
	 */
	public abstract void initialize();

	/**
	 * Disposes the GameObject.
	 */
	public abstract void dispose();
}
