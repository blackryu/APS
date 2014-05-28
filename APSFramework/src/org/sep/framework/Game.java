package org.sep.framework;

public abstract class Game {

    private Input input;
    
    private GameWindow gameWindow;
    
    private Screen currentScreen;

    private boolean running;
    
    private static final int WIDTH  = 800;
    private static final int HEIGHT = 600;

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
     * Sets a new screen to the game. Disposes the old screen and initializes
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
    
    public GameWindow getWindow() {
        return gameWindow;
    }
    
    public Input getInput() {
        return input;
    }
    
    public boolean isRunning() {
        return running;
    }

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
    
    public void quit() {
        running = false;
    }
}
