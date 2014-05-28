/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sep.gameobjects;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import org.sep.drawable.Animation;
import org.sep.drawable.Sprite;
import org.sep.framework.Framework;
import org.sep.framework.Game;
import org.sep.framework.GameObject;
import org.sep.framework.Input;

/**
 *
 * @author davidma
 */
public class Player extends GameObject {

    private final float RUNNING_SPEED = 300;
    private final float WALKING_SPEED = 100;

    private final String playerResource = "/org/sep/res/player.png";

    private Animation current;
    private Animation walkingLeft, walkingRight;
    private Animation runningLeft, runningRight;
    private Animation standing;
    private Animation jumpingLeft, jumpingRight;

    private boolean isJumping = false;

    private Game game;

    private float x, y;

    private float vx, vy;

    private static final float g = 490.81f;

    public Player(float x, float y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void draw(Graphics2D g) {
        current.draw(g);
    }

    @Override
    public void update(float deltaTime) {

        if (game.getInput().isKeyDown(Input.KeyCode.UP) && !isJumping) {
            if (current != jumpingLeft) {
                if (vx < 0.0f) current = jumpingLeft;
                else current = jumpingRight;
                
                this.vy = - 300;
                isJumping = true;
            }
        } else if (isJumping) {
            if (vy == 0.0f)
                isJumping = false;
        }else if (game.getInput().isKeyDown(Input.KeyCode.RIGHT) && game.getInput().isKeyDown(Input.KeyCode.SPACE)) {
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
        } else {
            vx = 0.0f;
            current = standing;
        }

        if (isJumping)
            vy += g * deltaTime;

        x += vx * deltaTime;
        y += vy * deltaTime;

        if (x < -50) {
            x = -50;
        } else if (x > 750) {
            x = 750;
        }
        
        if (y > 450) {
            y = 450;
            vy = 0.0f;
        }

        current.setPosition((int) x, (int) y);

        current.update(deltaTime);
    }

    @Override
    public void initialize() {
        InputStream is = this.getClass().getResourceAsStream("/org/sep/res/player.png");
        BufferedImage playerImage = null;
        try {
            playerImage = ImageIO.read(is);

        } catch (IOException e) {
            System.err.printf("Resource not found: %s!", playerResource);
            System.exit(1);
        }

        walkingLeft = new Animation();
        walkingRight = new Animation();
        runningRight = new Animation();
        runningLeft = new Animation();
        standing = new Animation();
        jumpingLeft = new Animation();
        jumpingRight = new Animation();

        walkingRight.addFrame(new Sprite(playerImage, 1, 257, 78, 126), 0.2);
        walkingRight.addFrame(new Sprite(playerImage, 81, 257, 78, 126), 0.2);
        walkingRight.addFrame(new Sprite(playerImage, 161, 257, 78, 126), 0.2);

        walkingLeft.addFrame(new Sprite(playerImage, 241, 257, 78, 126), 0.2);
        walkingLeft.addFrame(new Sprite(playerImage, 321, 257, 78, 126), 0.2);
        walkingLeft.addFrame(new Sprite(playerImage, 401, 257, 78, 126), 0.2);

        runningRight.addFrame(new Sprite(playerImage, 1, 1, 78, 126), 0.18);
        runningRight.addFrame(new Sprite(playerImage, 81, 1, 78, 126), 0.18);
        runningRight.addFrame(new Sprite(playerImage, 161, 1, 78, 126), 0.18);
        runningRight.addFrame(new Sprite(playerImage, 241, 1, 78, 126), 0.18);
        runningRight.addFrame(new Sprite(playerImage, 321, 1, 78, 126), 0.18);
        runningRight.addFrame(new Sprite(playerImage, 401, 1, 78, 126), 0.18);
        runningRight.addFrame(new Sprite(playerImage, 481, 1, 78, 126), 0.18);

        runningLeft.addFrame(new Sprite(playerImage, 1, 129, 78, 126), 0.18);
        runningLeft.addFrame(new Sprite(playerImage, 81, 129, 78, 126), 0.18);
        runningLeft.addFrame(new Sprite(playerImage, 161, 129, 78, 126), 0.18);
        runningLeft.addFrame(new Sprite(playerImage, 241, 129, 78, 126), 0.18);
        runningLeft.addFrame(new Sprite(playerImage, 321, 129, 78, 126), 0.18);
        runningLeft.addFrame(new Sprite(playerImage, 401, 129, 78, 126), 0.18);
        runningLeft.addFrame(new Sprite(playerImage, 481, 129, 78, 126), 0.18);

        standing.addFrame(new Sprite(playerImage, 481, 257, 78, 126), 1.0);

        jumpingRight.addFrame(new Sprite(playerImage, 1, 389, 78, 126), 1.0);
        jumpingLeft.addFrame(new Sprite(playerImage, 81, 389, 78, 126), 1.0);

        current = standing;

        game = Framework.getInstance().getGame();
    }

    @Override
    public void dispose() {

    }

}
