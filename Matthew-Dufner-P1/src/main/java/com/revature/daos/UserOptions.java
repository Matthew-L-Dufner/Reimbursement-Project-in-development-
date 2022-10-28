package com.revature.daos;

import com.revature.pojos.User;
import com.revature.services.ConnectionManager;

import java.nio.file.AccessDeniedException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class UserOptions implements DatabaseCRUD<User>{

    Connection connection;

    public UserOptions(Connection connection){
        this.connection = connection;
    }

    public UserOptions() {
        connection = ConnectionManager.getConnection();
    }

    @Override
    public void create(User user){
        try{
            String sql = "INSERT INTO users (first_name, last_name, user_pass, user_admin, email) " +
                    "VALUES (?, ?, ?, false, ?)";
            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setString(1, user.getFirstName());
            pstmt.setString(2, user.getLastName());
            pstmt.setString(3, user.getUserPass());
            pstmt.setString(4, user.getEmail());

            pstmt.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    @Override
    public void create(User user,int requestID){
    }

    @Override
    public User read(int id) {
        User user = new User();

        try{
            String sql = "SELECT * FROM users WHERE user_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet results = pstmt.executeQuery();


            if(results.next()){
                user.setUserID(results.getInt("user_id"));
                user.setFirstName(results.getString("first_name"));
                user.setLastName(results.getString("last_name"));
                user.setUserPass(results.getString("user_pass"));
                user.setUserAdmin(results.getBoolean("user_admin"));
                user.setEmail(results.getString("email"));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User read(int requestID, int userID){
        User user = new User();

        try{
            String sql = "SELECT * FROM users WHERE user_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, userID);
            ResultSet results = pstmt.executeQuery();


            if(results.next()){
                user.setUserID(results.getInt("user_id"));
                user.setFirstName(results.getString("first_name"));
                user.setLastName(results.getString("last_name"));
                user.setUserPass(results.getString("user_pass"));
                user.setUserAdmin(results.getBoolean("user_admin"));
                user.setEmail(results.getString("email"));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return user;
    }
    @Override
    public List<User> readAll() {
        List<User> userList = new LinkedList<>();

        try{
            String sql = "SELECT * FROM users";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet results = pstmt.executeQuery();

            while(results.next()){
                User user = new User();
                user.setUserID(results.getInt("user_id"));
                user.setFirstName(results.getString("first_name"));
                user.setLastName(results.getString("last_name"));
                user.setUserPass(results.getString("user_pass"));
                user.setUserAdmin(results.getBoolean("user_admin"));
                user.setEmail(results.getString("email"));
                userList.add(user);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

        return userList;
    }

    @Override
    public void update(User user) {

        try{

            String sql = "UPDATE users SET first_name = ?, last_name = ?, user_pass = ?, user_admin = ?," +
                    " email = ? WHERE user_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setString(1, user.getFirstName());
            pstmt.setString(2, user.getLastName());
            pstmt.setString(3, user.getUserPass());
            pstmt.setBoolean(4, user.isUserAdmin());
            pstmt.setString(5, user.getEmail());
            pstmt.setInt(6, user.getUserID());

            pstmt.executeUpdate();

        }catch (SQLException e){
            throw new RuntimeException(e);
        }

    }

    @Override
    public void delete(int id) {
        try{
            String sql = "DELETE FROM users WHERE user_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }

    }

    public User authenticate(String email, String userPass){
        User user = new User();
        try{
            String sql = "SELECT * FROM users WHERE email = ? AND user_pass = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, email);
            pstmt.setString(2, userPass);

            ResultSet results = pstmt.executeQuery();


            if(results.next()){
                user.setUserID(results.getInt("user_id"));
                user.setFirstName(results.getString("first_name"));
                user.setLastName(results.getString("last_name"));
                user.setUserPass(results.getString("user_pass"));
                user.setUserAdmin(results.getBoolean("user_admin"));
                user.setEmail(results.getString("email"));
            }else {
                throw new AccessDeniedException("Access Denied!");
            }
        } catch (SQLException e){
            e.printStackTrace();
        } catch (AccessDeniedException e) {
            throw new RuntimeException(e);
        }
        return user;
    }
    public User getUpdate(String firstName, String lastName, String email){
        User user = new User();

        try{
            String sql = "SELECT * FROM users WHERE first_name = ? AND last_name = ? AND email = ? ";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, email);

            ResultSet results = pstmt.executeQuery();


            if(results.next()){
                user.setUserID(results.getInt("user_id"));
                user.setFirstName(results.getString("first_name"));
                user.setLastName(results.getString("last_name"));
                user.setUserPass(results.getString("user_pass"));
                user.setUserAdmin(results.getBoolean("user_admin"));
                user.setEmail(results.getString("email"));
            }else {
                throw new AccessDeniedException("Access Denied!");
            }
        } catch (SQLException e){
            e.printStackTrace();
        } catch (AccessDeniedException e) {
            throw new RuntimeException(e);
        }
        return user;
    }
    public User getUserEm(String email){
        User user = new User();

        try{
            String sql = "SELECT * FROM users WHERE email = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, email);

            ResultSet results = pstmt.executeQuery();


            if(results.next()){
                user.setUserID(results.getInt("user_id"));
                user.setFirstName(results.getString("first_name"));
                user.setLastName(results.getString("last_name"));
                user.setUserPass(results.getString("user_pass"));
                user.setUserAdmin(results.getBoolean("user_admin"));
                user.setEmail(results.getString("email"));
            }else {
                throw new AccessDeniedException("Access Denied!");
            }
        } catch (SQLException e){
            e.printStackTrace();
        } catch (AccessDeniedException e) {
            throw new RuntimeException(e);
        }
        return user;
    }
    /*public Boolean checkAdmin(User user){



        try{
            String sql = "SELECT * FROM users WHERE user_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setObject(1, user);
            ResultSet results = pstmt.executeQuery();

            user.setUserAdmin(results.getBoolean("user_admin"));

        } catch (SQLException e){
            e.printStackTrace();
        }
    }*/
    @Override
    public List<User> readAll(int userID) {
        List<User> userList = new LinkedList<>();

        try{
            String sql = "SELECT * FROM users WHERE user_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet results = pstmt.executeQuery();

            while(results.next()){
                User user = new User();
                user.setUserID(results.getInt("user_id"));
                user.setFirstName(results.getString("first_name"));
                user.setLastName(results.getString("last_name"));
                user.setUserPass(results.getString("user_pass"));
                user.setUserAdmin(results.getBoolean("user_admin"));
                user.setEmail(results.getString("email"));
                userList.add(user);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

        return userList;
    }

}
