/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sep.jnrgame;

import org.sep.drawable.Sprite;
import org.sep.framework.Framework;
import org.sep.framework.Game;
import org.sep.framework.Input;
import org.sep.framework.Screen;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author N.Bausch
 */
public class HighScore implements Screen {

	static String[] scores;
	Sprite sprite;
	Font menuFont;
	Font selectionFont;
	Font titleFont;
	Font highScoreFont;
	int x, y;
	int menuY = 580;
	int selection = 0;
	boolean enterPressed;
	Game game;
	private Scanner eingabe = new Scanner(System.in);

	static String[] einlesen() throws IOException {    //liest die scores aus der Datei

		File datei = new File("src\\highscore.txt");

		if (!datei.exists())
			datei.createNewFile();

		FileReader eingabestrom = new FileReader(datei);
		BufferedReader eingabe = new BufferedReader(eingabestrom);

		String line = eingabe.readLine();

		//Als erstes laufen wird die ganzen Zeilen einmal durch, um zu erfahren wie groß unser Array sein muss
		int zeilenZähler = 0;
		while (line != null) {    //Durchlaufend der Zeilen
			line = eingabe.readLine();
			zeilenZähler++;
		}

		eingabe.close();    //Schließen des BufferedReader
		eingabestrom.close();  //Schließen des Eingabestroms

		zeilenZähler = zeilenZähler;  //teilt die anzahl der zeilen durch 2 um die anzahl der scores zu erhalten

		if (zeilenZähler == 0) {
			befuelleLehreDatei();
			zeilenZähler = 10;
		}
		scores = new String[zeilenZähler];    //erstellt den score-array

		eingabestrom = new FileReader(datei);    //man benötigt nochmal den eingabestrom
		eingabe = new BufferedReader(eingabestrom);  //und einen BufferedReader

		int i = 1;
		while (i <= zeilenZähler) {  //läuft wieder die Zeilen durch
			scores[i - 1] = eingabe.readLine();  //schreibt die Zeilen in den Array
			i++;
		}

		eingabe.close();    //Schließen des BufferedReader
		eingabestrom.close();  //Schließen des Eingabestroms

		return scores;    //rückgabe des score Arrays

	}

	/*
	*@param name= name des Spielers
	* highscore = aktuell erreichter Highscore
	* schreibt actuellen score in die bestenliste
	*/
	public static void scoreSchreiben(String name, int highscore) {
		List<Score> scoreList = new LinkedList<Score>();
		try {
			scores = einlesen();
		} catch (IOException ex) {
			Logger.getLogger(HighScore.class.getName()).log(Level.SEVERE, null, ex);
		}
		for (int i = 0; i < scores.length; i++) {
			String[] tempEingabe;
			tempEingabe = scores[i].split(":");
			String nameTemp = tempEingabe[0].replaceAll(" ", "");
			int scoreTemp = Integer.parseInt(tempEingabe[1]);
			Score tempScore = new Score(nameTemp, scoreTemp);
			scoreList.add(tempScore);
		}

		Score neuerScore = new Score(name, highscore);
		scoreList.add(neuerScore);

		Collections.sort(scoreList);

		FileWriter fw;
		try {
			fw = new FileWriter("src\\highscore.txt");

			// n textzeilen auf die datei schreiben:
			for (int i = 0; i < 10; i++) {
				fw.write(scoreList.get(i).toString() + "\n");
			}

			fw.close();
		} catch (IOException e) {
			System.out.println("Fehler beim Schreiben der Datei ");
			System.out.println(e.toString());
		}
	}

	/*
	*befüllt die Datei wenn es noch keinen Score giebt mit leeren Feldern
	*/
	private static void befuelleLehreDatei() {
		FileWriter fw;
		try {
			fw = new FileWriter("src\\highscore.txt");

			// n textzeilen auf die datei schreiben:
			for (int i = 0; i < 10; i++) {
				fw.write(new Score("---", 0000).toString() + "\n");
			}

			fw.close();
		} catch (IOException e) {
			System.out.println("Fehler beim Schreiben der Datei ");
			System.out.println(e.toString());
		}
	}

	@Override
	public void initialize() {

		enterPressed = true;

		x = 100;
		y = 100;

		InputStream is = this.getClass().getResourceAsStream("/org/sep/res/batman.jpg");
		try {
			BufferedImage background = ImageIO.read(is);
			sprite = new Sprite(background);

		} catch (IOException ex) {
			System.out.println("Could not load resource " + ex.getMessage());
		}

		game = Framework.getInstance().getGame();

		menuFont = new Font("Arial", Font.PLAIN, 32);
		selectionFont = new Font("Arial", Font.BOLD, 32);
		titleFont = new Font("Arial", Font.BOLD, 64);
		highScoreFont = new Font("Arial", Font.PLAIN, 25);

	}

	@Override
	public void update(float deltaTime) {
		if (game.getInput().isKeyDown(Input.KeyCode.ENTER) && !enterPressed) {
			select();
		} else if (!game.getInput().isKeyDown(Input.KeyCode.ENTER)) {
			enterPressed = false;
		}

		if (selection < 0) {
			selection = 0;
		} else if (selection > 0) {
			selection = 0;
		}

	}

	@Override
	public void draw() {

		Graphics2D g = game.getWindow().getGraphicsContext();

		g.setColor(Color.black);

		g.fillRect(0, 0, game.getWindow().getWidth(), game.getWindow().getHeight());

		sprite.draw(g);

		g.setColor(Color.RED);
		g.setFont(titleFont);
		g.drawString("Besten Liste", 50, 80);

		try {
			einlesen();
			for (int i = 0; i < einlesen().length; i++) {

				g.setColor(Color.YELLOW);
				g.setFont(highScoreFont);
				g.drawString(scores[i], 60, 150 + (i * 25));
			}
		} catch (IOException ex) {
			Logger.getLogger(HighScore.class.getName()).log(Level.SEVERE, null, ex);
		}
		// --- setzten des Exit Buttons ---
		g.setColor(Color.RED);
		g.setFont(menuFont);
		if (selection == 0) {
			g.setColor(Color.WHITE);
			g.setFont(selectionFont);
		}
		g.drawString("Exit to StartScreen", 50, menuY);

		game.getWindow().swapBuffer();
	}

	@Override
	public void dispose() {

	}

	private void select() {
		if (selection == 0) {
			game.setScreen(new StartScreen());
		}
	}
}
