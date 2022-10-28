package com.revature.pojos;

public class User {

    private Integer userID;
    private String firstName;
    private String lastName;
    private String userPass;
    private boolean userAdmin = false;
    private String email;

    public User(){

    }

    public User(Integer userID, String firstName, String lastName, String userPass, boolean userAdmin, String email) {
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userPass = userPass;
        this.userAdmin = userAdmin;
        this.email = email;
    }

    public User(String userID, String firstName, String lastName, String userPass, boolean userAdmin, String email){
        this.userID = null;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userPass = userPass;
        this.userAdmin = userAdmin;
        this.email = email;
    }

    public User(String firstName, String lastName, String userPass, String email){
        this.firstName = firstName;
        this.lastName = lastName;
        this.userPass = userPass;
        this.email = email;
    }

    public User(Integer userID, String firstName, String lastName, String userPass, String email){
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userPass = userPass;
        this.email = email;
    }

    public User(String email, String userPass){
        this.email = email;
        this.userPass = userPass;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public Boolean isUserAdmin() {
        //String userAdString = userAdmin;

        return userAdmin;
    }

    public void setUserAdmin(boolean userAdmin) {
        this.userAdmin = userAdmin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}
