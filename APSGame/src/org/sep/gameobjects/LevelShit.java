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
import org.sep.framework.GameObject;
import static org.sep.jnrgame.Resources.blocks;

/**
 *
 * @author davidma
 */
public class LevelShit extends GameObject {

    public final byte map[][] = {
        {34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34},
        {34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34},
        {34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34},
        {34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34},
        {34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34},
        {34, 34, 34, 34, 34, 34, 34, 34, 34, 34,  0,  1,  1,  1,  1, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34},
        {34, 34, 34, 34, 34, 34, 34, 34, 34, 34,  6,  7,  7,  7,  7, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34},
        { 0,  1,  1,  1,  1,  2, 34, 34, 34, 34,  6,  7,  7,  7,  7, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34},
        { 6,  7,  7,  7,  7,  8, 34, 34, 34, 34,  6,  7,  7,  7,  7, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34},
        { 6,  7,  7,  7,  7,  8, 34, 34, 34, 34,  6,  7,  7,  7,  7, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34}
    };

    @Override
    public void initialize() {

        
        
    }

    @Override
    public void dispose() {
    }

    @Override
    public void update(float deltaTime) {
    }

    @Override
    public void draw(Graphics2D g) {
        for (int i = 0; i < map.length; ++i) {
            for (int j = 0; j < map[i].length; ++j) {
                if (map[i][j] > 21) {
                    ;
                } else {
                    g.drawImage(blocks, j * 64, i * 64, j * 64 + 64, i * 64 + 64,
                        64 * (map[i][j] % 6), 64 * (map[i][j] / 6),
                        64 * (map[i][j] % 6) + 64, 64 * (map[i][j] / 6) + 64,
                        null);
                }               
            }
        }

    }

}
