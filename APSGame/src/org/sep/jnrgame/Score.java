/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sep.jnrgame;

/**
 *
 * @author N.Bausch
 */
public class Score implements Comparable<Score>{
    
    private String name;
    private int highscore;

    public Score(String name, int highscore) {
        this.name = name;
        this.highscore = highscore;
    }

    public String getName() {
        return name;
    }

    public int getHighscore() {
        return highscore;
    }

    @Override
    public String toString() {
        return name + "    :" + highscore;
    }

    @Override
    public int compareTo(Score o) {
    
        if(highscore > o.getHighscore()){
            return -1;
        }else if(highscore == o.getHighscore()){
            return 0;
        }else{
            return 1;
        }
    }
    
    
}
