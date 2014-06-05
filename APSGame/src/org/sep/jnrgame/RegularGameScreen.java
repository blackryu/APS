package org.sep.jnrgame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import org.sep.drawable.Sprite;
import org.sep.framework.Framework;
import org.sep.framework.Game;
import org.sep.framework.Input;
import org.sep.framework.Screen;
import org.sep.gameobjects.Level;
import org.sep.gameobjects.Level1;
import org.sep.gameobjects.Player;

public class RegularGameScreen implements Screen {

    Game game;

    Player player;

    int score;
    Font scoreFont;

    boolean paused;
    boolean pausePressed;

    boolean pressedDown;
    boolean pressedUp;

    int selection;

    Level level;

    Sprite background;

    @Override
    public void initialize() {
        game = Framework.getInstance().getGame();

        score = 0;
        scoreFont = new Font("Arial", Font.PLAIN, 64);

        InputStream is = this.getClass().getResourceAsStream("/org/sep/res/spritesheet.png");

        try {
            Resources.spriteSheet = ImageIO.read(is);
            is.close();
        } catch (IOException ex) {
            throw new RuntimeException("Unable to load resource: spritesheet.png");
        }

        is = this.getClass().getResourceAsStream("/org/sep/res/background.png");

        try {
            Resources.background = ImageIO.read(is);
            is.close();
        } catch (IOException ex) {
            throw new RuntimeException("Unable to load resource: background.png");
        }

        background = new Sprite(Resources.background);

        paused = false;
        pausePressed = false;

        pressedDown = false;
        pressedUp = false;

        selection = 0;

        level = new Level1();
        level.initialize();

        player = new Player(60, 300, level);
        player.initialize();
    }

    @Override
    public void update(float deltaTime) {
        if (!paused) {
            if (player.isIsAlive()) {
                player.update(deltaTime);
            } else {
                game.setScreen(new StartScreen());
            }

            if ((game.getInput().isKeyDown(Input.KeyCode.P)
                    || game.getInput().isKeyDown(Input.KeyCode.ESCAPE))
                    && !pausePressed) {
                paused = true;
                pausePressed = true;
            }
        } else {
            if ((game.getInput().isKeyDown(Input.KeyCode.P)
                    || game.getInput().isKeyDown(Input.KeyCode.ESCAPE))
                    && !pausePressed) {
                paused = false;
                pausePressed = true;
            }

            if (game.getInput().isKeyDown(Input.KeyCode.UP) && !pressedUp) {
                selection--;
                pressedUp = true;
            } else if (game.getInput().isKeyDown(Input.KeyCode.DOWN) && !pressedDown) {
                selection++;
                pressedDown = true;
            } else if (game.getInput().isKeyDown(Input.KeyCode.ENTER)) {
                select();
            }

            if (!game.getInput().isKeyDown(Input.KeyCode.UP)) {
                pressedUp = false;
            }

            if (!game.getInput().isKeyDown(Input.KeyCode.DOWN)) {
                pressedDown = false;
            }

            if (selection < 0) {
                selection = 0;
            } else if (selection > 2) {
                selection = 2;
            }
        }

        if (!game.getInput().isKeyDown(Input.KeyCode.P) && !game.getInput().isKeyDown(Input.KeyCode.ESCAPE)) {
            pausePressed = false;
        }
        
        level.setXPos(level.getXPos()-1);

//        background.setX(background.getX() - 1);

    }

    @Override
    public void draw() {
        Graphics2D g = game.getWindow().getGraphicsContext();

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, game.getWindow().getWidth(), game.getWindow().getHeight());

        background.draw(g);

        player.draw(g);

        level.draw(g);

        g.setFont(scoreFont);
        g.setColor(Color.WHITE);
        g.drawString((new Integer(score)).toString(), 10, 60);

        if (paused) {
            Color c = new Color(0, 0, 0, 0.5f);
            g.setColor(c);

            g.fillRect(0, 0, 800, 600);

            g.setColor(Color.WHITE);
            if (selection == 0) {
                g.setColor(Color.RED);
            }
            g.drawString("Fortsetzen", 200, 200);

            g.setColor(Color.WHITE);
            if (selection == 1) {
                g.setColor(Color.RED);
            }
            g.drawString("Hauptmenü", 200, 300);

            g.setColor(Color.WHITE);
            if (selection == 2) {
                g.setColor(Color.RED);
            }
            g.drawString("Beenden", 200, 400);
        }
        game.getWindow().swapBuffer();
    }

    @Override
    public void dispose() {

    }

    private void select() {
        if (selection == 0) {
            paused = false;
        } else if (selection == 1) {
            game.setScreen(new StartScreen());
        } else if (selection == 2) {
            game.quit();
        }
    }
}
