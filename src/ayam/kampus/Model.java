/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ayam.kampus;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author XENON
 */

public class Model {
    Koneksi conn = new Koneksi();
    
    public ResultSet login(String username, String password){
        boolean hasil = true;
        ResultSet rs = null;
        String sql = "SELECT * FROM user WHERE username = ? and password = ?";

        try {
            PreparedStatement st = conn.connect().prepareStatement(sql);
            st.setString(1, username);
            st.setString(2, password);
            
            rs = st.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return rs;
    }
    
    public boolean input(String username, String title, String content){
        boolean hasil = false;
        String sql = "INSERT INTO timeline(username, title, content) VALUES (?, ?, ?)";
        try {
            PreparedStatement st = conn.connect().prepareStatement(sql);
            st.setString(1, username);
            st.setString(2, title);
            st.setString(3, content);
            st.execute();
            hasil = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            conn.disconnect();
        }
        return hasil;
    }
    
    public boolean input_comment(String username, int id, String content){
        boolean hasil = false;
        String sql = "INSERT INTO comment(username, id_timeline, content) VALUES (?, ?, ?)";
        try {
            PreparedStatement st = conn.connect().prepareStatement(sql);
            st.setString(1, username);
            st.setString(2, Integer.toString(id));
            st.setString(3, content);
            st.execute();
            hasil = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            conn.disconnect();
        }
        return hasil;
    }
    
    public ResultSet dataTimeline(){
        ResultSet rs = null;
        String sql = "SELECT * FROM timeline ORDER BY id_timeline DESC ";

        try {
            PreparedStatement st = conn.connect().prepareStatement(sql);
            rs = st.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return rs;
    }
    
    public ResultSet dataComment(int id){
        ResultSet rs = null;
        String sql = "SELECT * FROM comment WHERE id_timeline = ? ORDER BY id_comment DESC ";

        try {
            PreparedStatement st = conn.connect().prepareStatement(sql);
            st.setString(1, Integer.toString(id));
            rs = st.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return rs;
    }
    
    public ResultSet detailTimeline(int id){
        ResultSet rs = null;
        String sql = "SELECT * FROM timeline WHERE id_timeline = ?";
        try {
            PreparedStatement st = conn.connect().prepareStatement(sql);
            st.setString(1, Integer.toString(id));
            rs = st.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return rs;
    }
    
    public ResultSet getUsers(){
        ResultSet rs = null;
        String sql = "SELECT * FROM user";
        try {
            PreparedStatement st = conn.connect().prepareStatement(sql);
            rs = st.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return rs;
    }
    
    public ResultSet getQuestions(){
        ResultSet rs = null;
        String sql = "SELECT * FROM timeline";
        try {
            PreparedStatement st = conn.connect().prepareStatement(sql);
            rs = st.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return rs;
    }
    
    public ResultSet getAnswers(){
        ResultSet rs = null;
        String sql = "SELECT * FROM Comment";
        try {
            PreparedStatement st = conn.connect().prepareStatement(sql);
            rs = st.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return rs;
    }
    
    public boolean register(String username, String password, int level){
        boolean hasil = false;
        String sql = "INSERT INTO user VALUES (NULL, ?, ?, ?)";
        try {
            PreparedStatement st = conn.connect().prepareStatement(sql);
            st.setString(1, username);
            st.setString(2, password);
            st.setString(3, Integer.toString(level));
            st.execute();
            hasil = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return hasil;
    }

}
