/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.multiplayergame.beans;

import java.util.*;

/**
 *
 * @author greenhill zone
 */
public class UserBean
{
    int id;
    String account;
    String password;
    String name;
    int score;
    int time;
    String sessionId;

    public ArrayList<String> words = null;
    
    public UserBean(){
        id = -1;
        words = new ArrayList<String>();
    }

    public void getToNextPic()
    {
        if(words != null)
        {
            words.clear();
        }
    }

    public void addAWord(String word)
    {
        if(words != null)
        {
            words.add(word);
            System.out.println("add a word : "+word);
        }
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

}
