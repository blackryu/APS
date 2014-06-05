package org.sep.framework;

public abstract class Game {

    // input module the game exposes to components that may need it
    private Input input;
    
    // the render target of the game
    private GameWindow gameWindow;
    
    // screen currently rendered by the game 
    private Screen currentScreen;

    // this value is checked each iteration to decide whether to end or continue the game
    private boolean running;
    
    // constant window size
    private static final int WIDTH  = 800;
    private static final int HEIGHT = 600;

    /**
     * Initializes the games modules.
     */
    public Game() {
        input = new Input();
    }

    /**
     * Provide this method to give the games name.
     *
     * @return name of the game.
     */
    public abstract String getName();

    /**
     * Provide this method to set the start screen for your game.
     *
     * @return first screen of the game
     */
    public abstract Screen getStartScreen();

    /**
     * Sets a new screen to the game. 
     * Disposes the old screen and initializes
     * the new one.
     *
     * @param screen the new screen
     */
    public void setScreen(Screen screen) {
        currentScreen.dispose();

        currentScreen = screen;
        currentScreen.initialize();
    }

    /**
     * Get the current screen.
     *
     * @return current screen
     */
    public Screen getScreen() {
        return currentScreen;
    }
    
    /**
     * Get the game's window.
     * 
     * @return game's window
     */
    public GameWindow getWindow() {
        return gameWindow;
    }
    
    /**
     * Get the game's input module.
     * 
     * @return game's input module
     */
    public Input getInput() {
        return input;
    }
    
    /**
     * Is the game still running?
     * 
     * @return running status of the game
     */
    public boolean isRunning() {
        return running;
    }

    /**
     * Initializes this game instance.
     */
    public void initialize() {
        currentScreen = getStartScreen();
        currentScreen.initialize();
        
        gameWindow = new GameWindow();
        gameWindow.setTitle(getName());
        gameWindow.setSize(WIDTH, HEIGHT);
        
        gameWindow.addKeyListener(input);
        
        gameWindow.setVisible(true);
        
        
        
        running = true;
    }
    
    /**
     * Requests the game to terminate.
     */
    public void quit() {
        running = false;
    }
}
