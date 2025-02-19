
package com.mycompany.mavenproject1;

import java.util.*; 
/**
 *
 * @author AliRagab313
 */
abstract  class Person {
    private String UserName;
    private String PassWord;
    private String FName;
    private String LName;
    private String Email;
    public  Person(){        
    }
    public Person(String UserName, String PassWord, String FName, String LName, String Email) {
        this.UserName = UserName;
        this.PassWord = PassWord;
        this.FName = FName;
        this.LName = LName;
        this.Email = Email;
    }
    
    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getPassWord() {
        return PassWord;
    }

    public void setPassWord(String PassWord) {
        this.PassWord = PassWord;
    }

    public String getFName() {
        return FName;
    }

    public void setFName(String FName) {
        this.FName = FName;
    }

    public String getLName() {
        return LName;
    }

    public void setLName(String LName) {
        this.LName = LName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }
}
class User extends Person
{
    private Double balance;
    private boolean IsLatePrevious;
    private boolean IsBlocked;      
    private HomePage HomeP;
    public User(String UserName, String PassWord, String FName, String LName, String Email,Double balance, boolean IsLatePrevious, boolean IsBlocked) {
        super(UserName, PassWord, FName, LName, Email);
        this.balance = balance;
        this.IsLatePrevious = IsLatePrevious;
        this.IsBlocked = IsBlocked;
            
    }    

    public HomePage getHomeP() {
        return HomeP;
    }

    public void setHomeP(HomePage HomeP) {
        this.HomeP = HomeP;
    }
    
    public Double getBalance() {        
     
        return balance;
    }

    public void setBalance(Double _balance) {
        this.balance =_balance;
        if(this.HomeP!=null)
              this.HomeP.updateUserBalnce();
        System.out.println( "Balance = "+this.balance);
        
        
    }

    public boolean isIsLatePrevious() {
        return IsLatePrevious;
    }

    public void setIsLatePrevious(boolean IsLatePrevious) {
        this.IsLatePrevious = IsLatePrevious;
    }

    public boolean isIsBlocked() {
        return IsBlocked;
    }

    public void setIsBlocked(boolean IsBlocked) {
        this.IsBlocked = IsBlocked;
    }

    
}

class Admin extends Person 
{     

    public Admin() {
    }

    public Admin(String UserName, String PassWord, String FName, String LName, String Email, List<Book> Reserved_Book, List<Message> MY_Messages) {
        super(UserName, PassWord, FName, LName, Email);
    }
        
    public void getBooksForAllBook()
    {
        
    }
    public void getBooksForReservedBook()
    {
        
    }
    public void getMessages()
    {
           
    }
    public void  AddnewBook(Book B)
    {
       // Sys.addBookToallBook(B);
    }
  
}
