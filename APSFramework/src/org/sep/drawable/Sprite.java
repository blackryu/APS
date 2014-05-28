/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sep.drawable;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author davidma
 */
public class Sprite implements Drawable {
    
    private BufferedImage sprite;
    private final int srcX, srcY, width, height;
    
    private int x, y;
    
    public Sprite(BufferedImage image) {
        this(image, 0, 0, image.getWidth(), image.getHeight());
    }
    
    public Sprite(BufferedImage image, int srcX, int srcY, int width, int height) {
        this.srcX = srcX;
        this.srcY = srcY;
        this.sprite = image;
        this.width = width;
        this.height = height;
    }

    @Override
    public void draw(Graphics2D g) {
        g.drawImage(sprite, x, y, x + width, y + height, srcX, srcY, srcX + width, srcY + height, null);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
}
