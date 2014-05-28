package org.sep.jnrgame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import org.sep.drawable.Animation;
import org.sep.drawable.Sprite;
import org.sep.framework.Framework;
import org.sep.framework.Game;
import org.sep.framework.Input;
import org.sep.framework.Screen;
import org.sep.gameobjects.Player;

public class RegularGameScreen implements Screen {

    Game game;
    
    Player player;
    
    @Override
    public void initialize() {
        game = Framework.getInstance().getGame();
        
        player = new Player(50, 450);
        player.initialize();
       
    }

    @Override
    public void update(float deltaTime) {
        player.update(deltaTime);
    }

    @Override
    public void draw() {
        Graphics2D g = game.getWindow().getGraphicsContext();
        
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, game.getWindow().getWidth(), game.getWindow().getHeight());
        
        player.draw(g);
        
        game.getWindow().swapBuffer();
    }

    @Override
    public void dispose() {
   
    }
    
}
