/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sep.framework;

/**
 * This interface should be implemented by classes that need to be updated
 * each iteration.
 *
 * @author David Marquant
 */
public interface Updateable {

	/**
	 * Update the game logic here.
	 *
	 * @param deltaTime time that has passed since last iteration in seconds
	 */
	public void update(float deltaTime);

}
