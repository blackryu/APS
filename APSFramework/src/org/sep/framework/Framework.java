package org.sep.framework;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Framework {
    
    private static Framework instance;
    
    private Game game;
    
    private Thread gameThread;
    
    public static Framework getInstance() {
        if (instance == null) {
            instance = new Framework();
        }
        return instance;
    }
    
    public void setGame(Game game) {
        this.game = game;
    }
    
    public Game getGame() {
        return game;
    }
    
    public void start() {
        game.initialize();
        
        gameThread = new Thread(new Runnable() {

            @Override
            public void run() {
                Screen screen;
                
                long lastFrame = System.currentTimeMillis();
                float deltaTime;
                
                while (game.isRunning()) {
                    deltaTime = (System.currentTimeMillis() - lastFrame) / 1000.0f;
                    lastFrame = System.currentTimeMillis();
                    
                    screen = game.getScreen();
                    
                    screen.update(deltaTime);
                    screen.draw();
                    
                    try {
                        Thread.sleep(16);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Framework.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
                System.exit(0);
            }
            
        });
        
        gameThread.start();
    }
    
    private Framework() {
    }
}
