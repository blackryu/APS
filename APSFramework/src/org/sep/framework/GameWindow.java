package org.sep.framework;

import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;

public class GameWindow extends JFrame {

    GameWindow() {
        setResizable(false);
        setIgnoreRepaint(true);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        
        if (visible)
            createBufferStrategy(2);
    }
    
    public Graphics2D getGraphicsContext() {
        BufferStrategy bs = getBufferStrategy();
        return (Graphics2D)bs.getDrawGraphics();
    }
    
    public void swapBuffer() {
        BufferStrategy bs = getBufferStrategy();
        
        if (!bs.contentsLost()) {
            bs.show();
        }
    }
}
