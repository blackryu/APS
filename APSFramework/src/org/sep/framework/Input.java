package org.sep.framework;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author davidma
 */
public class Input implements KeyListener {

    public static enum KeyCode {

        UP, DOWN, LEFT, RIGHT, ENTER, SPACE
    }

    private final boolean[] keyState;

    public Input() {
        keyState = new boolean[KeyCode.values().length];
    }

    public boolean isKeyDown(KeyCode keyCode) {
        return keyState[keyCode.ordinal()];
    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        switch (ke.getKeyCode()) {
            case KeyEvent.VK_UP:
                keyState[KeyCode.UP.ordinal()] = true;
                break;

            case KeyEvent.VK_DOWN:
                keyState[KeyCode.DOWN.ordinal()] = true;
                break;

            case KeyEvent.VK_LEFT:
                keyState[KeyCode.LEFT.ordinal()] = true;
                break;

            case KeyEvent.VK_RIGHT:
                keyState[KeyCode.RIGHT.ordinal()] = true;
                break;

            case KeyEvent.VK_ENTER:
                keyState[KeyCode.ENTER.ordinal()] = true;
                break;

             case KeyEvent.VK_SPACE:
                keyState[KeyCode.SPACE.ordinal()] = true;
                break;

            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        switch (ke.getKeyCode()) {
            case KeyEvent.VK_UP:
                keyState[KeyCode.UP.ordinal()] = false;
                break;

            case KeyEvent.VK_DOWN:
                keyState[KeyCode.DOWN.ordinal()] = false;
                break;

            case KeyEvent.VK_LEFT:
                keyState[KeyCode.LEFT.ordinal()] = false;
                break;

            case KeyEvent.VK_RIGHT:
                keyState[KeyCode.RIGHT.ordinal()] = false;
                break;

            case KeyEvent.VK_ENTER:
                keyState[KeyCode.ENTER.ordinal()] = false;
                break;

            case KeyEvent.VK_SPACE:
                keyState[KeyCode.SPACE.ordinal()] = false;
                break;

            default:
                break;
        }
    }

}
