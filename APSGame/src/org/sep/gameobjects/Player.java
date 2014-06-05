package org.sep.gameobjects;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.sep.drawable.Animation;
import org.sep.drawable.Sprite;
import org.sep.framework.Framework;
import org.sep.framework.Game;
import org.sep.framework.GameObject;
import org.sep.framework.Input;
import org.sep.jnrgame.HighScore;
import org.sep.jnrgame.Resources;

/**
 * @author davidma
 */
public class Player extends GameObject {

	private static final float g = 490.81f;
	private final float RUNNING_SPEED = 300;
	private final float WALKING_SPEED = 100;
	private final int MAX_HEARTS = 10;
	private final String playerResources = "/org/sep/res/player.png";
	Level level;
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
	private int score = 0;
	private boolean isAlive;

	public Player(float x, float y, Level level) {
		this.x = x;
		this.y = y;
		this.level = level;
	}

	public int getScore() {
		return score;
	}

	public boolean isIsAlive() {
		return isAlive;
	}

	@Override
	public void draw(Graphics2D g) {
		current.draw(g);
		//zeichnet die Herzen
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
		//doppelsprung verhindern
		if (game.getInput().isKeyDown(Input.KeyCode.UP) && !isJumping) {
			if (current != jumpingLeft) {
				if (vx < 0.0f) {
					current = jumpingLeft;
				} else {
					current = jumpingRight;
				}

				this.vy = -300;
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
		//überprüfen ob nächste x Position ein Hindernis ist
		if ((level.isPointInMap((int) x, (int) y) || level.isPointInMap((int) x + 80, (int) y)
						|| level.isPointInMap((int) x + 80, (int) y + 126) || level.isPointInMap((int) x, (int) y + 126))) {
			x = oldX;
		}

		float oldY = y;
		y += vy * deltaTime;
		//überprüfen ob nächste y Position ein Hindernis ist
		if ((level.isPointInMap((int) x, (int) y) || level.isPointInMap((int) x + 80, (int) y)
						|| level.isPointInMap((int) x + 80, (int) y + 126) || level.isPointInMap((int) x, (int) y + 126))) {
			y = oldY;
			vy = 0.0f;
			if (isJumping) {
				isJumping = false;
				current = standing;
			}
		}
		while (level.isPointInMap((int) x, (int) y) || level.isPointInMap((int) x + 80, (int) y)
						|| level.isPointInMap((int) x + 80, (int) y + 126) || level.isPointInMap((int) x, (int) y + 126)) {
			y++;
		}
		x -= 1;

//        
		current.setPosition((int) x, (int) y);
		score++;

		if (x < -60) {
			die();
		}

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

		bulidDialog();
		//HighScore.scoreSchreiben("Dav", 40000000);
		isAlive = false;
	}

	private void bulidDialog() {

		final int punkte = this.score;
		final JDialog gameover = new JDialog();
		gameover.setLayout(new BorderLayout());
		gameover.setTitle("GameOver");
		gameover.setSize(800, 600);
		gameover.getContentPane().setBackground(Color.black);

		Dimension d = new Dimension(50, 25);
		final JTextField player = new JTextField();
		player.setPreferredSize(d);
		player.setToolTipText("Bitte Spielernamen eingeben");

		final JLabel points = new JLabel("Punkte: ");
		final JLabel score = new JLabel();
		score.setText(punkte + "");

		JButton save = new JButton();
		save.setText("speichern");
		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				HighScore.scoreSchreiben(player.getText(), Integer.parseInt(score.getText()));
				gameover.setVisible(false);
				gameover.dispose();
			}
		});

		JButton drop = new JButton();
		drop.setText("verwerfen");
		drop.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				gameover.setVisible(false);
				gameover.dispose();
			}
		});

		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		panel.add(player);
		panel.add(points);
		panel.add(score);
		panel.add(save);
		panel.add(drop);

		JLabel background = new JLabel();
		background.setText("GAME OVER");
		background.setFont(new Font("Arial", Font.BOLD, 64));
		background.setForeground(Color.red);
		background.setHorizontalAlignment(JLabel.CENTER);
		background.setVerticalAlignment(JLabel.CENTER);

		gameover.add(background, BorderLayout.CENTER);
		gameover.add(panel, BorderLayout.SOUTH);

		gameover.setModal(true);
		gameover.setVisible(true);
	}

}
