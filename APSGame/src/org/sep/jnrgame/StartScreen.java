/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sep.jnrgame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import org.sep.drawable.Sprite;

import org.sep.framework.Framework;
import org.sep.framework.Game;
import org.sep.framework.Input;
import org.sep.framework.Screen;

/**
 *
 * @author davidma
 */
public class StartScreen implements Screen {

    Sprite sprite;

    Font menuFont;
    Font selectionFont;
    Font titleFont;

    int x, y;
    int menuY = 400;

    int selection = 0;

    Game game;

    @Override
    public void initialize() {
        x = 100;
        y = 100;

        InputStream is = this.getClass().getResourceAsStream("/org/sep/res/batman.jpg");
        try {
            BufferedImage background = ImageIO.read(is);
            sprite = new Sprite(background);
            
        } catch (IOException ex) {
            System.out.println("Could not load resource " + ex.getMessage());
        }

        game = Framework.getInstance().getGame();

        menuFont = new Font("Arial", Font.PLAIN, 32);
        selectionFont = new Font("Arial", Font.BOLD, 32);
        titleFont = new Font("Arial", Font.BOLD, 64);
    }
    
    boolean pressedUp = false;
    boolean pressedDown = false;

    @Override
    public void update(float deltaTime) {

        if (game.getInput().isKeyDown(Input.KeyCode.UP) && !pressedUp) {
            selection--;
            pressedUp = true;
        } else if (game.getInput().isKeyDown(Input.KeyCode.DOWN) && !pressedDown) {
            selection++;
            pressedDown = true;
        } else if (game.getInput().isKeyDown(Input.KeyCode.ENTER)) {
            select();
        }
        
        if (!game.getInput().isKeyDown(Input.KeyCode.UP))
            pressedUp = false;
        
        if (!game.getInput().isKeyDown(Input.KeyCode.DOWN))
            pressedDown = false;

        if (selection < 0) {
            selection = 0;
        } else if (selection > 3) {
            selection = 3;
        }
    }

    @Override
    public void draw() {
        Graphics2D g = game.getWindow().getGraphicsContext();

        g.setColor(Color.black);

        g.fillRect(0, 0, game.getWindow().getWidth(), game.getWindow().getHeight());

        sprite.draw(g);
        
        g.setColor(Color.RED);
        g.setFont(titleFont);
        g.drawString(game.getName(), 50, 80);

        //g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g.setColor(Color.RED);
        g.setFont(menuFont);
        if (selection == 0) {
            g.setColor(Color.WHITE);
            g.setFont(selectionFont);
        }
        g.drawString("Reguläres Spiel", 50, menuY);

        g.setColor(Color.RED);
        g.setFont(menuFont);
        if (selection == 1) {
            g.setColor(Color.WHITE);
            g.setFont(selectionFont);
        }
        g.drawString("Endlos Spiel", 50, menuY + 60);

        g.setColor(Color.RED);
        g.setFont(menuFont);
        if (selection == 2) {
            g.setColor(Color.WHITE);
            g.setFont(selectionFont);
        }
        g.drawString("Highscore", 50, menuY + 120);

        g.setColor(Color.RED);
        g.setFont(menuFont);
        if (selection == 3) {
            g.setColor(Color.WHITE);
            g.setFont(selectionFont);
        }
        g.drawString("Exit", 50, menuY + 180);

        game.getWindow().swapBuffer();
    }

    @Override
    public void dispose() {

    }

    private void select() {
        if (selection == 3) {
            game.quit();
        } else if (selection == 0) {
            game.setScreen(new RegularGameScreen());
        }
    }
}
