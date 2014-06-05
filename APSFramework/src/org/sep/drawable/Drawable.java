package org.sep.drawable;

import java.awt.Graphics2D;

/**
 * Any component of a game that needs to be rendered during an iteration
 * should implement this interface.
 * 
 * @author David Marquant
 */
public interface Drawable {
    
    /**
     * Renders this Drawable.
     * 
     * @param g an AWT Graphics Context to render this drawable 
     */
    public void draw(Graphics2D g);
    
}
