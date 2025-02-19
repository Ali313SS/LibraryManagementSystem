/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;

import java.util.*;
/**
 *
 * @author AliRagab313
 */
public class UserBuilder {
    private String userName;
    private String passWord;
    private String fName;
    private String lName;
    private String email;
    private Double balance;
    private boolean isLatePrevious;
    private boolean isBlocked;
    private List<Book> myBook = new ArrayList<>();
    private List<Book> reservedBook = new ArrayList<>();
    private List<Message> myMessages = new ArrayList<>();

    public UserBuilder setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public UserBuilder setPassWord(String passWord) {
        this.passWord = passWord;
        return this;
    }

    public UserBuilder setFName(String fName) {
        this.fName = fName;
        return this;
    }

    public UserBuilder setLName(String lName) {
        this.lName = lName;
        return this;
    }

    public UserBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder setBalance(Double balance) {
        this.balance = balance;
        return this;
    }

    public UserBuilder setIsLatePrevious(boolean isLatePrevious) {
        this.isLatePrevious = isLatePrevious;
        return this;
    }

    public UserBuilder setIsBlocked(boolean isBlocked) {
        this.isBlocked = isBlocked;
        return this;
    }

    public UserBuilder setMyBook(List<Book> myBook) {
        this.myBook = myBook;
        return this;
    }

    public UserBuilder setReservedBook(List<Book> reservedBook) {
        this.reservedBook = reservedBook;
        return this;
    }

    public UserBuilder setMyMessages(List<Message> myMessages) {
        this.myMessages = myMessages;
        return this;
    }

    public  User build() {
        return new User(userName, passWord, fName, lName, email , balance, isLatePrevious, isBlocked);
    }
}