
package com.mycompany.mavenproject1;
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/**
 *
 * @author AliRagab313
 */
public class LSystem implements Manager,Sender,BrrowReturnManager {
    private static List<String> AllUser;
    private static List<Integer> allBook;
    private static List<Message> All_Messages;
    public static HashMap<Integer, String> Category;
    private static DataBase DB = null;

    private LSystem() {
        DB = DataBase.getInstance();        
        All_Messages = DB.getAllMessages();
        Category = DB.Category();
        allBook = DB.getAllBooks();
        AllUser = DB.getAllUsers();
        DB.getAllReservedBooks();


    }

    private static LSystem instance;

    public static LSystem getInstance() {
        if (instance == null)
            instance = new LSystem();
        return instance;
    }

    public List<Integer> getAllBook() {
        return allBook;
    }

    public void setAllBook(List<Integer> allBook) {
        this.allBook = allBook;
    }

    public void addBookToallBook(Book B) {
        if (allBook == null)
            allBook = new ArrayList<Integer>();
        allBook.add(B.getId());
        BookProfile.Add(B);
    }

    public List<String> getBlockList() {
        List<String> BlockList = new ArrayList<String>();
        for (String U : AllUser)
            if (Profile.GetProfile(U).isIsBlocked())
                BlockList.add(U);
        return BlockList;
    }

    public List<String> getAllUser() {
        return AllUser;
    }

    public void setAllUser(List<String> AllUser) {
        this.AllUser = AllUser;
    }

    public static List<Message> getAll_Messages() {
        return All_Messages;
    }

    public static void setAll_Messages(List<Message> All_Messages) {
        LSystem.All_Messages = All_Messages;
    }

    public static HashMap<Integer, String> getCategory() {
        return Category;
    }

    public static void setCategory(HashMap<Integer, String> Category) {
        LSystem.Category = Category;
    }

    public static DataBase getDB() {
        return DB;
    }

    public static void setDB(DataBase DB) {
        LSystem.DB = DB;
    }

    public static void addNewMessage(Message M) {
        All_Messages.add(M);
    }

    public List<Message> GetAllMessageForUSer(String UserName) {
        List<Message> ret = new ArrayList<Message>();
        for (Message M : All_Messages) {
            if (M.getReciver().equals(UserName)) {
                ret.add(M);
            }
        }
        return ret;
    }

    @Override
    public void BlockUser(String UserName) {
        Profile.GetProfile(UserName).setIsBlocked(true);
        DB.blockUser(UserName);
    }

    @Override
    public void UNBlockUser(String UserName) {
        Profile.GetProfile(UserName).setIsBlocked(false);
        DB.unblockUser(UserName);

    }

    @Override
    public void RemoveUser(String UserName) {
        DB.removeUser(UserName);
        Profile.removeProfile(UserName);
    }

    @Override
    public void sendMessage(String Sender, String receiverUserName, String Content) {
        DB.insertMessage(Sender, receiverUserName, Content);
    }

    @Override
    public boolean borrowBook(String userName, int id, int numberOfDays, Double amountPaid) {
        // Check if the user is blocked
        if (Profile.GetProfile(userName).isIsBlocked()) {
            return false;
        }
        if(Profile.GetProfile(userName).getBalance()<amountPaid||Profile.GetProfile(userName).getBalance()<numberOfDays* BookProfile.GetProfile(id).getCostForDay())
        {
            return false;
        }

        // Assign the book to the user
        unreserveBook(userName,id);
        BookProfile.GetProfile(id).setWhoTakeThisBook(userName);
        BookProfile.GetProfile(id).setStatus(false);
        
        // Get the current date and convert it to java.sql.Date
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date startDate = new java.sql.Date(utilDate.getTime());

        // Calculate the end date
        Calendar cal = Calendar.getInstance();
        cal.setTime(utilDate);
        cal.add(Calendar.DAY_OF_MONTH, numberOfDays);
        java.sql.Date endDate = new java.sql.Date(cal.getTimeInMillis());
     
        // Insert the borrow record into the database
        DB.insertBorrow(userName, id, startDate, numberOfDays, endDate, amountPaid);
        DB.updateBookUsername(id, userName);             
        AddMoneySystem(userName,-amountPaid);
        return true;
    }


    @Override
    public boolean returnBook(String userName, int id) {
        DB.removeBorrow(userName,id);
        // Check if the book is with the user
        if (!BookProfile.GetProfile(id).isWithUser(userName)) {
            return false;
        }

        // Update the book status and remove the user from WhoTakeThisBook
        BookProfile.GetProfile(id).setWhoTakeThisBook(null);
        BookProfile.GetProfile(id).setStatus(true);

        // Update the book record in the database
        DB.updateBookUsername(id, null);
      
 
        // Notify waiting users that the book is available
        BookProfile.GetProfile(id).notifyu();

        return true;
    }

    @Override
    public boolean reserveBook(String UserName, int id) {
        if (Profile.GetProfile(UserName).isIsBlocked()) {
            return false;

        }
        BookProfile.GetProfile(id).addToWait(UserName);
        DB.insertReservedBook(UserName, id);
        return true;
    }
    @Override
    public boolean unreserveBook(String UserName, int id) {   
        BookProfile.GetProfile(id).RemoveFromWait(UserName);
        DB.removeReservedBook(UserName, id);
        return true;
    }


    @Override
    public boolean AddMoneyAdmin(String UserName, double Money) {
        if (Money < 0)
            return false;
        if (Profile.GetProfile(UserName) == null)
            return false;
        Profile.GetProfile(UserName).setBalance(Money + Profile.GetProfile(UserName).getBalance());
        
        DB.updateBalance(UserName, Money);
        return true;
    }
     @Override
    public boolean AddMoneySystem(String UserName, double Money) {
        if (Profile.GetProfile(UserName) == null)
            return false;
        Profile.GetProfile(UserName).setBalance(Money + Profile.GetProfile(UserName).getBalance());
        DB.updateBalance(UserName, Money);
        return true;
    }
    public void  warning(String UserNAme)
    {
        if(Profile.GetProfile(UserNAme).isIsLatePrevious())
        {
           BlockUser(UserNAme);
           return;
        }
        Profile.GetProfile(UserNAme).setIsLatePrevious(true);
    }
    public static List<Integer>MYBook(String UserName)
    {
       List<Integer>MY=new ArrayList<Integer>();
       for(var ID : allBook)
       {
           if(BookProfile.GetProfile(ID).getWhoTakeThisBook()!=null&&BookProfile.GetProfile(ID).getWhoTakeThisBook().equals(UserName))
           {
               MY.add(ID);
           }
       }
       return MY;
    }
    public static List<Integer>MYReseveBook(String UserName)
    {
        return DB.getAllReservedBooksForUser(UserName);
    }
}