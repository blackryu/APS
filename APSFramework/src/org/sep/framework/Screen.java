package org.sep.framework;

/**
 * A screen is a section of a game like a menu or the actual game.
 * Only one screen can be current to a game.
 * 
 * @author David Marquant
 */
public interface Screen {
    
    /**
     * Override this method to initialize the screen and load resources for it.
     */
    public void initialize();
    
    /**
     * Override this method to update the game logic of the screen.
     * 
     * @param deltaTime the time passed since last frame
     */
    public void update(float deltaTime);
    
    /**
     * Render the screen.
     */
    public void draw();
    
    /**
     * Override this method to free resources of the screen.
     */
    public void dispose();
}
