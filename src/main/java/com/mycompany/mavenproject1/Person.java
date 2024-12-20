
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
    private List<Book>Reserved_Book ;
    private List<Message>MY_Messages;
    public  Person(){
        Reserved_Book=new ArrayList <Book>();
        MY_Messages=new ArrayList <Message>();
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

    
    public List<Book> getReserve_Book() {
        return Reserved_Book;
    }

    public void setReserve_Book(List<Book> Reserve_Book) {
        this.Reserved_Book = Reserve_Book;
    }
    public void addBookToReservedBook(Book B) {
        Reserved_Book.add(B);
    }
    public List<Message> getMY_Messages() {
        return MY_Messages;
    }

    public void setMY_Messages(List<Message> MY_Messages) {
        this.MY_Messages = MY_Messages;
    }

    public List<Book> getReserved_Book() {
        return Reserved_Book;
    }

    public void setReserved_Book(List<Book> Reserved_Book) {
        this.Reserved_Book = Reserved_Book;
    }
    
    
    
}
class User extends Person
{
    private int balance;
    private boolean IsLatePrevious;
    private boolean IsBlocked;    
    private List<Book>MyBook;
    public User(int balance, boolean IsLatePrevious, boolean IsBlocked, List<Book> MyBook) {
        this.balance = balance;
        this.IsLatePrevious = IsLatePrevious;
        this.IsBlocked = IsBlocked;
        this.MyBook = MyBook;
      
    } 
    public List<Book> getMyBook() {
        return MyBook;
    }
    public void setMyBook(List<Book> MyBook) {
        this.MyBook = MyBook;
    }   
    public void addBookToMyBook(Book B) {
        MyBook.add(B);
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
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
