/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.multiplayergame.sessionUtil;

import beans.*;
import java.util.ArrayList;
/**
 *
 * @author greenhill zone
 */
public class GamePair
{
    public UserBean user1, user2;
    public ArrayList<String> images;
    public int currentIndex = 0;

    public int isOver = 0;
    public int score = 0;

    public GamePair(UserBean user1, UserBean user2)
    {
        this.user1 = user1;
        this.user2 = user2;

        DatabaseBean dbBean = new DatabaseBean();
        this.images = dbBean.getUrls(10);
        dbBean.close();

        printImgUrls();
    }

    public void printImgUrls()
    {
        for(int i=0; i<images.size(); ++i)
        {
            System.out.println(images.get(i));
        }
    }

    public void addAWord(String sessionId, String word)
    {
        if(sessionId.equals(user1.getSessionId()))
        {
            user1.addAWord(word);
        }
        else if(sessionId.equals(user2.getSessionId()))
        {
            user2.addAWord(word);
        }
        judgeTheSameWord();
    }

    public boolean judgeTheSameWord()
    {
        for(int i=0; i<user1.words.size(); ++i)
        {
            if(user2.words.contains(user1.words.get(i)))
            {
                this.isOver = 2;
                System.out.println("Words are the same!!!");

                DatabaseBean db = new DatabaseBean();
                this.score+=db.getScoreByPicAndLabel(this.images.get(this.currentIndex),  user1.words.get(i));
                //System.out.println("Score : "+score);
                db.saveGameInfo(user1.getId(), user2.getId(), score,100, this.images.get(this.currentIndex), user1.words.get(i));
                db.close();
                user1.getToNextPic();
                user2.getToNextPic();
                return true;
            }
        }
        return false;
    }
}
