/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sep.drawable;

import org.junit.After;
import org.junit.AfterClass;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * This is a set of tests to test the functionality of the Animation class
 *
 * @author Cody Hamilton
 */
public class AnimationTest {

	public AnimationTest() {
	}

	@BeforeClass
	public static void setUpClass() {
	}

	@AfterClass
	public static void tearDownClass() {
	}

	@Before
	public void setUp() {
	}

	@After
	public void tearDown() {
	}

	@Test
	public void testSettingPosition() {
		Animation animation = new Animation();
		assertEquals("Expect animation to have default X", 0, animation.getX());
		assertEquals("Expect animation to have default Y", 0, animation.getY());
		animation.setPosition(3, 4);
		assertEquals("Expect animation to have updated X", 3, animation.getX());
		assertEquals("Expect animation to have updated Y", 4, animation.getY());
	}

	@Test
	public void testAddingFrame() {
		Sprite sprite = new Sprite(null, 0, 0, 100, 100);
		Animation animation = new Animation();
		assertEquals("Expect no frames to exist", 0, animation.frames.size());
		//adding frames
		animation.addFrame(sprite, 1.0f);
		animation.addFrame(sprite, 1.0f);
		animation.addFrame(sprite, 1.0f);
		animation.addFrame(sprite, 1.0f);
		assertEquals("Expect 4 frames to have been created",
						4, animation.frames.size());
	}

	@Test
	public void testUpdatingFrame() {
		Sprite sprite = new Sprite(null, 0, 0, 100, 100);
		Animation animation = new Animation();
		assertEquals("Expect currentFrame to be default initialized",
						0, animation.currentFrame);
		//add 2 frames to work with, 0 and 1
		animation.addFrame(sprite, 2.0f);
		animation.addFrame(sprite, 2.0f);
		animation.update(2.5f);
		assertEquals("Expect currentFrame to have been updated",
						1, animation.currentFrame);
		animation.update(2.5f);
		assertEquals("Expect currentFrame to have been set to 0 again",
						0, animation.currentFrame);
	}


}
