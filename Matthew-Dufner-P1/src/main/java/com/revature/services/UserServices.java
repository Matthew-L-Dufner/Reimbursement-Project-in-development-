package com.revature.services;

import com.revature.daos.UserOptions;
import com.revature.pojos.User;

import java.util.List;

public class UserServices {
    private UserOptions UOpts;

    public UserServices() {
        this.UOpts = new UserOptions();
    }

    public void saveUser(User user){
        UOpts.create(user);
    }

    public User getUser(int id){
        return  UOpts.read(id);
    }

    public List<User> getAllUsers(){
        return UOpts.readAll();
    }

    public void updateUser(User user){
        UOpts.update(user);
    }

    public void deleteUser(int id){
        UOpts.delete(id);
    }
    public User authenticate(String email, String userPass){
        return UOpts.authenticate(email, userPass);
    }

    public User getUserEm(String email) {
        return UOpts.getUserEm(email);
    }
    public User getUpdate(String firstName, String lastName, String email) {
        return UOpts.getUpdate(firstName, lastName, email);
    }

}
