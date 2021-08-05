/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.multiplayergame.sessionUtil;

import java.util.*;
import beans.*;
/**
 *
 * @author greenhill zone
 */
public class SessionManager
{
    private static SessionManager sm = null;
    public ArrayList<GamePair> pairs  = null;

    public Stack<UserBean> userStack = null;

    public final static int NOT_GAME = 0;
    public final static int IN_STACK    = 1;
    public final static int IN_PAIR       = 2;

    private SessionManager()
    {
        pairs= new ArrayList<GamePair>();
        userStack = new Stack<UserBean>();
    }

    public static SessionManager getInstance()
    {
        if(sm == null)
            sm = new SessionManager();
        return sm;
    }

    //true means a pair is available, false means you must wait
    public boolean joinInGame(UserBean user)
    {
        if(userStack.empty())
        {
            userStack.add(user);
            return false;
        }
        else
        {
            GamePair gp = new GamePair(user, userStack.peek());
            pairs.add(gp);
            userStack.removeAllElements();
            return true;
        }
    }

    //1 means int waiting stack, 2 means in pairs , 0 means not in the game
    public int hasJoinedGame(UserBean user)
    {
        if(!userStack.empty())
        {
            if(userStack.peek().getSessionId().equals(user.getSessionId()))
                return this.IN_STACK;
        }
        for(int i=0; i<pairs.size(); ++i)
        {
            GamePair gp = pairs.get(i);
            if(gp.user1.getSessionId().equals(user.getSessionId()))
                return this.IN_PAIR;
            if(gp.user2.getSessionId().equals(user.getSessionId()))
                return this.IN_PAIR;
        }
        return this.NOT_GAME;
    }

    public GamePair findPairFromSessionId(String sessionId)
    {
        for(int i=0; i<pairs.size(); ++i)
        {
             GamePair gp = pairs.get(i);
             if(gp.user1.getSessionId().equals(sessionId) || gp.user2.getSessionId().equals(sessionId))
                 return gp;
        }
        return null;
    }
    

}
