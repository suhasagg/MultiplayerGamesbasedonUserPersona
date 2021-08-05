/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.multiplayergame.beans;

/**
 *
 * @author greenhill zone
 */
public class PairBean {
    int user1Id;
    int user2Id;
    String u1name;
    String u2name;
    int score;

    public String getU1name() {
        return u1name;
    }

    public void setU1name(String u1name) {
        this.u1name = u1name;
    }

    public String getU2name() {
        return u2name;
    }

    public void setU2name(String u2name) {
        this.u2name = u2name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getUser1Id() {
        return user1Id;
    }

    public void setUser1Id(int user1Id) {
        this.user1Id = user1Id;
    }

    public int getUser2Id() {
        return user2Id;
    }

    public void setUser2Id(int user2Id) {
        this.user2Id = user2Id;
    }
    
}
