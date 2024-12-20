package com.mycompany.mavenproject1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author AliRagab313
 */
public class DataBase {
    // Singleton Design Pattern
    private static DataBase MYDBinstance = null;
    private Connection connection;
    
    
    private DataBase() {
        //Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        
//        try {
//            
//            String url = "jdbc:sqlserver://DESKTOP-VNM1EDO:1433;databaseName=SoftWareEngineering";
//            String username = "sa";
//            String password = "AliRagab313";
//            connection = DriverManager.getConnection(url, username, password);
//        } catch (SQLException e) {
//            e.printStackTrace();
//            throw new RuntimeException("Failed to connect to the database");
//        }
    }

    public static DataBase getInstance() {
        if (MYDBinstance == null) {
            MYDBinstance = new DataBase();
        }
        return MYDBinstance;
    }

    // Insert a new user
    public void insertUser(String username, String password, String fname, String lname, String email, double balance, boolean isLatePrevious, boolean isBlocked) {
        String query = "INSERT INTO Reg_user (username, Pass, fname, lname, Email, balance, isLatePrevious, isblocked) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, fname);
            stmt.setString(4, lname);
            stmt.setString(5, email);
            stmt.setDouble(6, balance);
            stmt.setBoolean(7, isLatePrevious);
            stmt.setBoolean(8, isBlocked);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // Check if username exists
    public boolean checkUserNameFound(String username) {
        
        String query = "SELECT 1 FROM Reg_user WHERE username = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
      public boolean checkEmailFound(String Email) {
        String query = "SELECT 1 FROM Reg_user WHERE Email = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, Email);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Check login credentials
    public boolean checkLogin(String username, String password) {
        String query = "SELECT 1 FROM Reg_user WHERE username = ? AND Pass = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Get all users
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM Reg_user";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
//                User user = new User(
//                        rs.getString("username"),
//                        rs.getString("Pass"),
//                        rs.getString("fname"),
//                        rs.getString("lname"),
//                        rs.getString("Email"),
//                        rs.getDouble("balance"),
//                        rs.getBoolean("isLatePrevious"),
//                        rs.getBoolean("isblocked")
//                );
//                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    // Get all books
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String query = "SELECT * FROM Book";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Book book = new Book();
//                Book book = new Book(
//                        rs.getInt("id"),
//                        rs.getString("name"),
//                        rs.getDouble("costforDay"),
//                        rs.getInt("NumberOFPage"),
//                        rs.getInt("CategoryID"),
//                        rs.getString("Author")
//                );
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    // Get block list
    public List<String> getBlockList() {
        List<String> blockList = new ArrayList<>();
        String query = "SELECT username FROM Reg_user WHERE isblocked = true";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                blockList.add(rs.getString("username"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return blockList;
    }
  public void insertBook(String name, double costForDay, int numberOfPages, int categoryId, String author) {
        String query = "INSERT INTO Book (name, costforDay, NumberOFPage, CategoryID, Author) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setDouble(2, costForDay);
            stmt.setInt(3, numberOfPages);
            stmt.setInt(4, categoryId);
            stmt.setString(5, author);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
  public void removeBook(int bookId) {
        String query = "DELETE FROM Book WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, bookId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
  public void insertMessage(String sendUserName, String receiverUserName, String content, Timestamp sendDate) {
        String query = "INSERT INTO Messages (SendUserName, receiver_userName, content, SendDate) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, sendUserName);
            stmt.setString(2, receiverUserName);
            stmt.setString(3, content);
            stmt.setTimestamp(4, sendDate);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
     public void insertBorrow(String username, int bookId, Date startDate, int numberOfDays, Date endDate, double amountPaid) {
        String query = "INSERT INTO UserBorrowBook (username, Book_id, startDate, numberOfDays, endDate, AmountPaid) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setInt(2, bookId);
            stmt.setDate(3, startDate);
            stmt.setInt(4, numberOfDays);
            stmt.setDate(5, endDate);
            stmt.setDouble(6, amountPaid);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
public void removeBorrow(String username, int bookId) {
        String query = "DELETE FROM UserBorrowBook WHERE username = ? AND Book_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setInt(2, bookId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Insert a new reserved book
    public void insertReservedBook(String username, int bookId) {
        String query = "INSERT INTO UserResrveBOOK (username, bookId) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setInt(2, bookId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Remove a reserved book
    public void removeReservedBook(String username, int bookId) {
        String query = "DELETE FROM UserResrveBOOK WHERE username = ? AND bookId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setInt(2, bookId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get all messages
    public List<Message> getAllMessages() {
        List<Message> messages = new ArrayList<>();
        String query = "SELECT * FROM Messages";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
//                Message message = new Message(
//                        rs.getInt("id"),
//                        rs.getString("SendUserName"),
//                        rs.getString("receiver_userName"),
//                        rs.getString("content"),
//                        rs.getTimestamp("SendDate")
//                );
//                messages.add(message);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return messages;
    } 
 
    
    
}

