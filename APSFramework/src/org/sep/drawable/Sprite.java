package org.sep.drawable;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * This class is used to render a single image on screen.
 * 
 * @author David Marquant
 */
public class Sprite implements Drawable {
    
    // the spritesheet
    private BufferedImage sprite;
    
    // rect of pixels which belong to this sprite
    private final int srcX, srcY, width, height;
    
    // position on screen where the sprite is rendered
    private int x, y;
    
    /**
     * Creates a new sprite using the whole image data. 
     * 
     * @param image sprite sheet for this sprite
     */
    public Sprite(BufferedImage image) {
        this(image, 0, 0, image.getWidth(), image.getHeight());
    }
    
    /**
     * Creates a new sprite but only uses a part of the sprite sheet.
     * 
     * @param image the sprite sheet
     * @param srcX sprite's x position in the sprite sheet
     * @param srcY sprite's y position in the sprite sheet
     * @param width sprite's width
     * @param height sprite's height
     */
    public Sprite(BufferedImage image, int srcX, int srcY, int width, int height) {
        this.srcX = srcX;
        this.srcY = srcY;
        this.sprite = image;
        this.width = width;
        this.height = height;
    }
    
    /**
     * Get the x coordinate of the sprite
     * 
     * @return x coordinate of the sprite
     */
    public int getX() {
        return x;
    }

    /**
     * Set the x coordinate of the sprite
     * 
     * @param x coordinate of the sprite
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Get the y coordinate of the sprite
     * 
     * @return y coordinate of the sprite
     */
    public int getY() {
        return y;
    }

    /**
     * Set the y coordinate of the sprite
     * 
     * @param y coordinate of the sprite
     */
    public void setY(int y) {
        this.y = y;
    }
    
    
    @Override
    public void draw(Graphics2D g) {
        g.drawImage(sprite, x, y, x + width, y + height, srcX, srcY, srcX + width, srcY + height, null);
    }    
}
