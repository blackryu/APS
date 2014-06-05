/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sep.gameobjects;

import java.awt.Graphics2D;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

import org.sep.drawable.Drawable;

import static org.sep.jnrgame.Resources.blocks;

/**
 * @author davidma
 */
public abstract class Level implements Drawable {

	int xPos;

	public abstract byte[][] getMap();

	public boolean isPointInMap(int x, int y) {
		for (int yMap = 0; yMap < getMap().length; ++yMap) {
			for (int xMap = 0; xMap < getMap()[yMap].length; ++xMap) {
				if (getMap()[yMap][xMap] < 22) {
					if (x >= xPos + (xMap - 1) * 64 && xPos + (xMap) * 64 < x
									&& y >= (yMap - 1) * 64 && (yMap) * 64 < y) {
						return true;
					}
				}
			}
		}

		return false;
	}

	public void initialize() {
		xPos = 0;
		if (blocks == null) {
			try {
				InputStream is = this.getClass().getResourceAsStream("/org/sep/res/smario_sprites.png");

				blocks = ImageIO.read(is);
			} catch (IOException ex) {
				System.err.println("Unable to load resource: " + "/org/sep/res/smario_sprites");
			}
		}
	}

	@Override
	public void draw(Graphics2D g) {
		for (int i = 0; i < getMap().length; ++i) {
			for (int j = 0; j < getMap()[i].length; ++j) {
				if (getMap()[i][j] > 21) {
					;
				} else {
					g.drawImage(blocks, xPos + j * 64, i * 64, xPos + j * 64 + 64, i * 64 + 64,
									64 * (getMap()[i][j] % 6), 64 * (getMap()[i][j] / 6),
									64 * (getMap()[i][j] % 6) + 64, 64 * (getMap()[i][j] / 6) + 64,
									null);
				}
			}
		}
	}

	public int getXPos() {
		return xPos;
	}

	public void setXPos(int x) {
		xPos = x;
	}
}
