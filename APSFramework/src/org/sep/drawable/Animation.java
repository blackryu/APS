package org.sep.drawable;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import org.sep.framework.Updateable;

/**
 * This class can be used to render a sprite animation.
 *
 * @author David Marquant
 */
public class Animation implements Drawable, Updateable {

    // a list of all sprites rendered during a whole animation
    List<Sprite> frames;

    // the durations of the single frames
    List<Double> frameTimes;

    // the index of the current frame
    int currentFrame;

    // the positon where the animation is rendered on screen
    int x, y;

    // the duration that the current frame was current 
    float currentFrameTime;

    /**
     * Creates an empty animation at the screens origin
     */
    public Animation() {
        this(0, 0);
    }

    /**
     * Creates an empty animation at the given position.
     *
     * @param x the x coordinate of the animation
     * @param y the y coordinate of the animation
     */
    public Animation(int x, int y) {
        frames = new ArrayList<>();
        frameTimes = new ArrayList<>();

        this.x = x;
        this.y = y;

        for (Sprite s : frames) {
            s.setX(x);
            s.setY(y);
        }
    }

    /**
     * Appends a frame to the animation.
     *
     * @param frame the sprite of the new frame
     * @param frameTime the duration of the new frame
     */
    public void addFrame(Sprite frame, double frameTime) {
        frames.add(frame);
        frameTimes.add(frameTime);

        frame.setX(x);
        frame.setY(y);
    }

    /**
     * Updates the position of the animation.
     * 
     * @param x the x coordinate of the animation
     * @param y the y coordinate of the animation
     */
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;

        for (Sprite s : frames) {
            s.setX(x);
            s.setY(y);
        }
    }

    /**
     * Get the x coordinate of the animation.
     * 
     * @return x coordinate of the animation
     */
    public int getX() {
        return x;
    }

    /**
     * Set the y coordinate of the animation.
     * 
     * @return the y coordinate of the animation
     */
    public int getY() {
        return y;
    }

    @Override
    public void draw(Graphics2D g) {
        frames.get(currentFrame).draw(g);
    }

    @Override
    public void update(float deltaTime) {
        currentFrameTime += deltaTime;

        if (currentFrameTime >= frameTimes.get(currentFrame)) {
            currentFrameTime -= frameTimes.get(currentFrame);
            currentFrame++;

            if (frames.size() <= currentFrame) {
                currentFrame = 0;
            }
        }
    }
}
