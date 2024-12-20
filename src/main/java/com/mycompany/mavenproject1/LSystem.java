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
public class LSystem implements Manager,Sender {
    private List<String>BlockList;    
    private List<String>AllUser;    
    private List<Book>allBook;
    private List<Message>All_Messages;
    private LSystem()
    {}
    private static LSystem instance;
    public static LSystem getInstance(){
        if(instance==null)
            instance=new LSystem();
        return instance;
    }
    public void sendMessage(String receiverUserName,String Content){
        
    }     
    public void BlockUser(String UserName){
        
    }
    public void RemoveUser(String UserName){
        
    }
    public void addBookToallBook(Book B)
    {
        allBook.add(B);
    }

    @Override
    public void addUserToblackList(String UserName) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void ReomveUserFromblackList(String UserName) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void AddBalnce(String UserName) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
