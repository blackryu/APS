package org.sep.gameobjects;

import java.awt.Graphics2D;
import org.sep.drawable.Animation;
import org.sep.drawable.Sprite;
import org.sep.framework.Framework;
import org.sep.framework.Game;
import org.sep.framework.GameObject;
import org.sep.framework.Input;
import org.sep.jnrgame.Resources;

/**
 *
 * @author davidma
 */
public class Player extends GameObject {

    private final float RUNNING_SPEED = 300;
    private final float WALKING_SPEED = 100;

    private final int MAX_HEARTS = 10;

    private final String playerResources = "/org/sep/res/player.png";

    private Animation current;
    private Animation walkingLeft, walkingRight;
    private Animation runningLeft, runningRight;
    private Animation standing;
    private Animation jumpingLeft, jumpingRight;
    private Animation duck;

    private Sprite life;
    private Sprite halfLife;

    private int numHearts;

    private boolean isJumping = false;

    private Game game;

    private float x, y;

    private float vx, vy;

    private boolean isAlive;

    Level level;
    
    public boolean isIsAlive() {
        return isAlive;
    }

    private static final float g = 490.81f;

    public Player(float x, float y, Level level) {
        this.x = x;
        this.y = y;
        this.level = level;
    }

    @Override
    public void draw(Graphics2D g) {
        current.draw(g);

        for (int iHeart = 0; iHeart < MAX_HEARTS / 2; ++iHeart) {
            if (numHearts == iHeart * 2 + 1) {
                halfLife.setX(380 + 84 * iHeart);
                halfLife.draw(g);
            } else if (numHearts > iHeart * 2) {
                life.setX(380 + 84 * iHeart);
                life.draw(g);
            }
        }
    }

    @Override
    public void update(float deltaTime) {

        if (game.getInput().isKeyDown(Input.KeyCode.UP) && !isJumping) {
            if (current != jumpingLeft) {
                if (vx < 0.0f) {
                    current = jumpingLeft;
                } else {
                    current = jumpingRight;
                }

                this.vy = - 300;
                isJumping = true;
            }
        } else if (isJumping) {
            if (vy == 0.0f) {
                isJumping = false;
                current = standing;
            }
        } else if (game.getInput().isKeyDown(Input.KeyCode.RIGHT) && game.getInput().isKeyDown(Input.KeyCode.SPACE)) {
            current = runningRight;
            this.vx = RUNNING_SPEED;
        } else if (game.getInput().isKeyDown(Input.KeyCode.LEFT) && game.getInput().isKeyDown(Input.KeyCode.SPACE)) {
            current = runningLeft;
            this.vx = -RUNNING_SPEED;
        } else if (game.getInput().isKeyDown(Input.KeyCode.RIGHT)) {
            current = walkingRight;
            this.vx = WALKING_SPEED;
        } else if (game.getInput().isKeyDown(Input.KeyCode.LEFT)) {
            current = walkingLeft;
            this.vx = -WALKING_SPEED;
        } else if (game.getInput().isKeyDown(Input.KeyCode.DOWN)) {
            current = duck;
            this.vx = 0.0f;
        } else {
            vx = 0.0f;
            current = standing;
        }

        
            vy += g * deltaTime;
        

        float oldX = x;
        x += vx * deltaTime;
        
        if ((level.isPointInMap((int)x, (int)y) || level.isPointInMap((int)x+80, (int)y) 
                || level.isPointInMap((int)x+80, (int)y+126) || level.isPointInMap((int)x, (int)y+126)))
            x = oldX;
            
        float oldY = y;
        y += vy * deltaTime;

        if ((level.isPointInMap((int)x, (int)y) || level.isPointInMap((int)x+80, (int)y) 
                || level.isPointInMap((int)x+80, (int)y+126) || level.isPointInMap((int)x, (int)y+126)))
        {
            y = oldY;
            vy = 0.0f;
            if (isJumping) {
                isJumping = false;
                current = standing;
            }
        }
           x -= 1;
        
//        x -= 1;
        current.setPosition((int) x , (int) y);
        
        if (x < -60)
            die();
        
        current.update(deltaTime);
    }

    @Override
    public void initialize() {

        walkingLeft = new Animation();
        walkingRight = new Animation();
        runningRight = new Animation();
        runningLeft = new Animation();
        standing = new Animation();
        jumpingLeft = new Animation();
        jumpingRight = new Animation();
        duck = new Animation();

        walkingRight.addFrame(new Sprite(Resources.spriteSheet, 1, 257, 78, 126), 0.2);
        walkingRight.addFrame(new Sprite(Resources.spriteSheet, 81, 257, 78, 126), 0.2);
        walkingRight.addFrame(new Sprite(Resources.spriteSheet, 161, 257, 78, 126), 0.2);

        walkingLeft.addFrame(new Sprite(Resources.spriteSheet, 241, 257, 78, 126), 0.2);
        walkingLeft.addFrame(new Sprite(Resources.spriteSheet, 321, 257, 78, 126), 0.2);
        walkingLeft.addFrame(new Sprite(Resources.spriteSheet, 401, 257, 78, 126), 0.2);

        runningRight.addFrame(new Sprite(Resources.spriteSheet, 1, 1, 78, 126), 0.18);
        runningRight.addFrame(new Sprite(Resources.spriteSheet, 81, 1, 78, 126), 0.18);
        runningRight.addFrame(new Sprite(Resources.spriteSheet, 161, 1, 78, 126), 0.18);
        runningRight.addFrame(new Sprite(Resources.spriteSheet, 241, 1, 78, 126), 0.18);
        runningRight.addFrame(new Sprite(Resources.spriteSheet, 321, 1, 78, 126), 0.18);
        runningRight.addFrame(new Sprite(Resources.spriteSheet, 401, 1, 78, 126), 0.18);
        runningRight.addFrame(new Sprite(Resources.spriteSheet, 481, 1, 78, 126), 0.18);

        runningLeft.addFrame(new Sprite(Resources.spriteSheet, 1, 129, 78, 126), 0.18);
        runningLeft.addFrame(new Sprite(Resources.spriteSheet, 81, 129, 78, 126), 0.18);
        runningLeft.addFrame(new Sprite(Resources.spriteSheet, 161, 129, 78, 126), 0.18);
        runningLeft.addFrame(new Sprite(Resources.spriteSheet, 241, 129, 78, 126), 0.18);
        runningLeft.addFrame(new Sprite(Resources.spriteSheet, 321, 129, 78, 126), 0.18);
        runningLeft.addFrame(new Sprite(Resources.spriteSheet, 401, 129, 78, 126), 0.18);
        runningLeft.addFrame(new Sprite(Resources.spriteSheet, 481, 129, 78, 126), 0.18);

        standing.addFrame(new Sprite(Resources.spriteSheet, 481, 257, 78, 126), 1.0);

        jumpingRight.addFrame(new Sprite(Resources.spriteSheet, 1, 389, 78, 126), 1.0);
        jumpingLeft.addFrame(new Sprite(Resources.spriteSheet, 81, 389, 78, 126), 1.0);

        duck.addFrame(new Sprite(Resources.spriteSheet, 161, 389, 78, 126), 1.0);

        life = new Sprite(Resources.spriteSheet, 241, 389, 64, 64);
        halfLife = new Sprite(Resources.spriteSheet, 321, 389, 64, 64);

        life.setY(10);
        halfLife.setY(10);
        
        isAlive = true;

        numHearts = 6;
        
        current = standing;

        game = Framework.getInstance().getGame();
    }

    @Override
    public void dispose() {

    }

    /**
     * Increase/Decrease the number of hearts the player has.
     *
     * @param amount amount of hearts [-2;2], -2/2 for whole hearts
     */
    public void increaseHearts(int amount) {
        if (-2 <= amount && amount <= 2) {
            numHearts += amount;

            if (numHearts <= 0) {
                die();
            }
        }
    }

    private void die() {
        isAlive = false;
    }
    
}
