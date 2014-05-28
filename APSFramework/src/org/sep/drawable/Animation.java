package org.sep.drawable;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import org.sep.framework.Updateable;

public class Animation implements Drawable, Updateable {

    List<Sprite> frames;
    List<Double> frameTimes;
    
    int currentFrame;
    
    int x, y;
    
    float currentFrameTime;
    
    public Animation() {
        this(0, 0);
    }
    
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
    
    public void addFrame(Sprite frame, double frameTime) {
        frames.add(frame);
        frameTimes.add(frameTime);
        
        frame.setX(x);
        frame.setY(y);
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
    
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
        
        for (Sprite s : frames) {
            s.setX(x);
            s.setY(y);
        }
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }    
}
