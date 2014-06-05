package org.sep.framework;

/**
 * The framework manages the lifecycle of the game. 
 * 
 * This is a Singleton class.
 * 
 * @author David Marquant
 */
public class Framework {
    
    // the singleton's instance
    private static Framework instance;
    
    // the managed game
    private Game game;
    
    // this thread is responsible for executing the game loop
    private Thread gameThread;
    
    /**
     * Give access to the Singleton. 
     * 
     * @return singleton's instance
     */
    public static Framework getInstance() {
        if (instance == null) {
            instance = new Framework();
        }
        return instance;
    }
    
    /**
     * Sets the game managed by the framework.
     * 
     * Calling this method on a running framework will do nothing.
     * 
     * @param game game managed by the framework.
     */
    public void setGame(Game game) {
        if (gameThread == null || !gameThread.isAlive())
            this.game = game;
    }
    
    /**
     * Gets the game managed by this framework.
     * 
     * @return the game managed by this framework 
     */
    public Game getGame() {
        return game;
    }
    
    /**
     * Starts the execution of the game loop.
     * 
     * If game is not set this method will result in a NullPointerException
     */
    public void start() {
        game.initialize();
        
        gameThread = new Thread(new Runnable() {

            @Override
            public void run() {
                Screen screen;
                
                long lastFrame = System.currentTimeMillis();
                float deltaTime;
                
                while (game.isRunning()) {
                    
                    // Calculate the seconds that have passed since the last iteration
                    deltaTime = (System.currentTimeMillis() - lastFrame) / 1000.0f;
                    lastFrame = System.currentTimeMillis();
                    
                    // Get the current screen of the game
                    screen = game.getScreen();
                    
                    screen.update(deltaTime);
                    screen.draw();
                    
                    // Let the thread sleep so the main thread does not hang up
                    try {
                        Thread.sleep(6);
                    } catch (InterruptedException ex) {
                        System.err.printf("An InterruptedException occured: %s\n", ex.getMessage());
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
