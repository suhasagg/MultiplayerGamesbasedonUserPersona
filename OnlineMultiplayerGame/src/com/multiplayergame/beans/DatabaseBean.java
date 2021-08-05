/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.multiplayergame.beans;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author greenhill zone
 */
public class DatabaseBean{
     static String mySqlDriver = "com.mysql.jdbc.Driver";
    static String url = "jdbc:mysql://localhost:3306/multiplayerpuzzleGame?useUnicode=true&characterEncoding=utf8";
    static String username = "root";
    static String userpass = "admin";
    private ResultSet rs;
    private Connection conn;
    private Statement s;


    public UserBean getUserById(int i){
        return getUser("select * form user where id="+i);
    }
    public UserBean getUser(String sql){
        UserBean user = new UserBean();
        try {
            rs = s.executeQuery(sql);
            if (rs.next()) {
               user.setId(rs.getInt("id"));
               user.setAccount(rs.getString("account"));
               user.setName(rs.getString("name"));
               user.setScore(rs.getInt("score"));
               user.setPassword(rs.getString("password"));
               user.setTime(rs.getInt("time"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return user;
    }


    public DatabaseBean(){
             try {
            Class.forName(mySqlDriver).newInstance();
            conn = DriverManager.getConnection(url, username, userpass);
            s = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            conn.setAutoCommit(false);
            rs = null;
           
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }
    public void close(){
          try {
            try{
                //if(rs != null)
                  //  rs.updateRow();
                if(conn != null)
                    conn.commit();
            }catch(Exception e){
                e.printStackTrace();
            }
            s.close();
            if(rs != null)
                rs.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }
     public ArrayList<PairBean> getTopPair(int n){
         ArrayList<PairBean> topp = new ArrayList<PairBean>();
        try {

            String sql = "select * from pair order by score desc";
            rs = s.executeQuery(sql);
           
            int temp =0;
            while (rs.next()) {
                 PairBean p = new PairBean();
              p.setUser1Id(rs.getInt("user1_id"));
              p.setUser2Id(rs.getInt("user2_id"));
              p.setScore(rs.getInt("score"));
             // p.setU1name(getUser("select * from user where id="+p.getUser1Id()).getName());
             // p.setU2name(getUser("select * from user where id="+p.getUser1Id()).getName());
               topp.add(p);
               temp++;
               if(temp>n)break;
            }
            for(int i =0;i<topp.size();i++){
               PairBean tp = topp.get(i);
               String na = getUser("select * from user where id="+tp.getUser1Id()).getName();
               tp.setU1name(na);
               String na2 = getUser("select * from user where id="+tp.getUser2Id()).getName();
               tp.setU2name(na2);
               topp.set(i, tp);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseBean.class.getName()).log(Level.SEVERE, null, ex);
        }
         return topp;
    }
    public ArrayList<UserBean> getTop(int n){
         ArrayList<UserBean> top = new ArrayList<UserBean>();
        try {

            String sql = "select * from user order by score desc";
            rs = s.executeQuery(sql);
           
            int temp =0;
            while (rs.next()) {
                 UserBean u = new UserBean();
               u.setId(rs.getInt("id"));
               u.setAccount(rs.getString("account"));
               u.setName(rs.getString("name"));
               u.setScore(rs.getInt("score"));
               u.setPassword(rs.getString("password"));
               u.setTime(rs.getInt("time"));
               top.add(u);
               temp++;
               if(temp>n)break;
            }

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseBean.class.getName()).log(Level.SEVERE, null, ex);
        }
         return top;
    }
    public void saveGameInfo(int user1id,int user2id,int score,int time,String url,String label){
        try {
            PairBean p = new PairBean();
            p.setUser1Id(user1id);
            p.setUser2Id(user2id);
            p.setScore(score);
            savePair(p);
            int lid = -1;
             int picid = -1;
             String sql = "select * from photos where url='" + url + "'";
            rs = s.executeQuery(sql);
            if(rs.next()){
            picid = rs.getInt("id");
            }
            sql = "select * from label where descripiton='" + label + "'";
            rs = s.executeQuery(sql);
            if (rs.next()) {
                lid = rs.getInt("id");
            }else{
                sql = "insert into label (descripiton) values ('"+label+"') ";
                s.execute(sql);
                sql = "select * from label where descripiton='" + label + "'";
                rs = s.executeQuery(sql);
                if (rs.next()) {
                    lid = rs.getInt("id");
                }
            }
            sql = "select * from label_photos where p_id="+picid+" and l_id="+lid;
            rs = s.executeQuery(sql);
            if(rs.next()){
                int prescore = rs.getInt("score");
                sql = "update label_photos set score="+(int)(prescore*0.95)+" where p_id="+picid+" and l_id="+lid;
                s.execute(sql);
            }else{
                sql = "insert into label_photos values ("+picid+","+lid+",200)";
                s.execute(sql);
            }
            sql = "insert into log ( p_id,l_id,u_id,time ) values ("+picid+","+lid+","+user1id+","+(int)(System.currentTimeMillis() / 1000)+")";
            s.execute(sql);
            sql = "insert into log ( p_id,l_id,u_id,time ) values ("+picid+","+lid+","+user2id+","+(int)(System.currentTimeMillis() / 1000)+")";
            s.execute(sql);
            
            //sql = "update user set score="+getUserById(user1id).getScore()++

            
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public int getScoreByPicAndLabel(String url,String label){
        int lid = -1;
        int pid = -1;
        int score = 200;
        try {
            String sql = "select * from label where descripiton='" + label + "'";
            rs = s.executeQuery(sql);
            if(rs.next()){
            lid = rs.getInt("id");
            }

            sql = "select * from photos where url='" + url + "'";
            rs = s.executeQuery(sql);
            if(rs.next()){
            pid = rs.getInt("id");
            }
            sql = "select * from label_photos where p_id="+pid+" and l_id="+lid;
            rs = s.executeQuery(sql);
            if(rs.next()){
                score = rs.getInt("score");
            }
        } catch (SQLException ex) {
           score = 200;
        }
        return score;
    }

    ArrayList<String> getLimitedWords(String url){
        ArrayList<String> limitedWords = new ArrayList<String>();
        ArrayList<Integer> lids = new ArrayList<Integer>();
        try {
            int pid = -1;
            String sql = "select * from photos where url='" + url + "'";
            rs = s.executeQuery(sql);
            if(rs.next()){
            pid = rs.getInt("id");
            }
            
            sql = "select * from label_photos where p_id="+pid;
            rs = s.executeQuery(sql);
            while(rs.next()){
                lids.add(rs.getInt("l_id"));
            }
            for(int i =0;i<Math.min(lids.size(),5);i++){
                int lid = lids.get(i);
                sql = "select * from label where id="+lid;
                rs = s.executeQuery(sql);
                if(rs.next()){
                    limitedWords.add(rs.getString("description"));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return limitedWords;

    }

    public void saveUser(UserBean user) {
        try {
            String sql = "insert into user ( account , name , password ,score,time) values ('"+
                    user.getAccount()+"','"+user.getName()+"','"+user.getPassword()+"',0,0)";
            s.execute(sql);
        } catch (SQLException ex) {
           ex.printStackTrace();
        }
    }
    public void savePair(PairBean p ){
        boolean find = false;
        try {
           String sql = "select * from pair where user1_id="+p.getUser1Id()+" and user2_id="+p.getUser2Id();
           rs = s.executeQuery(sql);
           if(rs.next()){
                find = true;
                int prescore = rs.getInt("score");
                String sql2 = "update pair set score="+(prescore+p.getScore())+" where user1_id="+p.getUser1Id()+" and user2_id="+p.getUser2Id();
                s.execute(sql2);
           }
           sql = "select * from pair where user1_id="+p.getUser2Id()+" and user2_id="+p.getUser1Id();
           rs = s.executeQuery(sql);
           if(rs.next()){
                find = true;
                int prescore = rs.getInt("score");
                String sql2 = "update pair set score="+(prescore+p.getScore())+" where user1_id="+p.getUser2Id()+" and user2_id="+p.getUser1Id();
                s.execute(sql2);
           }
           if(!find){
                 String sql2 = "insert into pair values ("+p.getUser1Id()+" , "+p.getUser2Id()+" , "+p.getScore()+")";
                 s.execute(sql2);
           }
        } catch (SQLException ex) {
           ex.printStackTrace();
        }
    }
    public ArrayList<String> getUrls(int num){
        if(num<1||num>1000)return null;
        ArrayList<Integer> temp = new ArrayList<Integer>();
        for(int i=0;i<1000;i++){
            temp.add(i+1);
        }
       // ArrayList<Integer> atemp = new ArrayList<Integer>();
        Collections.shuffle(temp);
        ArrayList<String> al = new ArrayList<String>();
        for(int i=0;i<num;i++){
            String url = getPicUrl(temp.get(i));
            al.add(url);
        }
        return al;
    }

    private String getPicUrl(int i) {
        String aurl="";
        try {
            String sql = "select * from photos where id=" + i;
            //System.out.println("i:"+i);
            rs = s.executeQuery(sql);
            if(rs.next()){
                aurl= rs.getString("url");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return aurl;
    }
}
