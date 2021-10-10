package com.example.registration;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class User {

    private SimpleIntegerProperty userid;
    private SimpleStringProperty firstname;
    private SimpleStringProperty lastname;
    private SimpleStringProperty username;
    private SimpleStringProperty password;
    private SimpleStringProperty gender;
    private SimpleStringProperty number;

    public User(Integer userId, String firstName, String lastName, String username, String password, String gender, String number){
        this.userid = new SimpleIntegerProperty(userId);
        this.firstname = new SimpleStringProperty(firstName);
        this.lastname = new SimpleStringProperty(lastName);
        this.username = new SimpleStringProperty(username);
        this.password = new SimpleStringProperty(password);
        this.gender = new SimpleStringProperty(gender);
        this.number = new SimpleStringProperty(number);
    }

    public User() {
    }

    public int getUserId() {
        return userid.get();
    }

    public SimpleIntegerProperty userIdProperty() {
        return userid;
    }

    public void setUserId(int userId) {
        this.userid.set(userId);
    }

    public String getFirstName() {
        return firstname.get();
    }

    public SimpleStringProperty firstNameProperty() {
        return firstname;
    }

    public void setFirstName(String firstName) {
        this.firstname.set(firstName);
    }

    public String getLastName() {
        return lastname.get();
    }

    public SimpleStringProperty lastNameProperty() {
        return lastname;
    }

    public void setLastName(String lastName) {
        this.lastname.set(lastName);
    }

    public String getUsername() {
        return username.get();
    }

    public SimpleStringProperty usernameProperty() {
        return username;
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public String getPassword() {
        return password.get();
    }

    public SimpleStringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public String getGender() {
        return gender.get();
    }

    public SimpleStringProperty genderProperty() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender.set(gender);
    }

    public String getNumber() {
        return number.get();
    }

    public SimpleStringProperty numberProperty() {
        return number;
    }

    public void setNumber(String number) {
        this.number.set(number);
    }
}
