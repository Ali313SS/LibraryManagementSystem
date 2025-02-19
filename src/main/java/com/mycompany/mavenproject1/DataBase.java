package com.mycompany.mavenproject1;

import java.sql.*;
import java.sql.Date;
import java.util.*;
import java.text.*;

/**
 *
 * @author AliRagab313
 */
public class DataBase {
    // Singleton Design Pattern
    private static DataBase MYDBinstance = null;
    private Connection connection;

    private DataBase() {

        try {

            String url = "jdbc:sqlserver://localhost:1433;databaseName=SoftWareEngineering;encrypt=false";
            String username = "sa";
            String password = "AliRagab313";
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to connect to the database");
        }
    }

    public static DataBase getInstance() {
        if (MYDBinstance == null) {
            MYDBinstance = new DataBase();
        }
        return MYDBinstance;
    }

    // Insert a new user
    public void insertUser(String username, String password, String fname, String lname, String email, Double balance, boolean isLatePrevious, boolean isBlocked) {

        UserBuilder UB = new UserBuilder();
        UB.setBalance(balance).setEmail(email).setFName(fname).setIsBlocked(isBlocked).setIsLatePrevious(isLatePrevious).setLName(lname).setUserName(username).setPassWord(password);
        UB.setMyMessages(new ArrayList<Message>());
        UB.setMyBook(new ArrayList<Book>());
        UB.setReservedBook(new ArrayList<Book>());
        User U = UB.build();
        Profile P = Profile.getInstance();
        P.Add(U);
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
      public boolean checkLoginAdmin(String username, String password) {
        String query = "SELECT 1 FROM Admin WHERE username = ? AND Pass = ?";
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
    public List<String> getAllUsers() {
        List<String> Users = new ArrayList<>();
        String query = "SELECT * FROM Reg_user";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                UserBuilder UB = new UserBuilder();
                UB.setBalance(rs.getDouble("balance")).setEmail(rs.getString("Email")).setFName(rs.getString("fname")).setIsBlocked(rs.getBoolean("isblocked")).setIsLatePrevious(rs.getBoolean("isLatePrevious")).setLName(rs.getString("lname")).setUserName(rs.getString("username")).setPassWord(rs.getString("Pass"));
                UB.setMyMessages(new ArrayList<Message>());
                UB.setMyBook(new ArrayList<Book>());
                UB.setReservedBook(new ArrayList<Book>());
                User U = UB.build();
                Profile P = Profile.getInstance();
                P.Add(U);
                Users.add(U.getUserName());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Users;
    }

    // Get all books
    public List<Integer> getAllBooks() {
        List<Integer> books = new ArrayList<>();
        String query = "SELECT * FROM Book";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Book book = new Book(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("Author"),
                        LSystem.Category.get(rs.getInt("CategoryID")),
                        rs.getInt("NumberOFPage"),
                        rs.getDouble("costforDay"),
                        rs.getString("UserName"),
                        null
                );
                if (book.getWhoTakeThisBook() != null)
                    book.setStatus(false);
                books.add(book.getId());
                BookProfile.Add(book);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    public HashMap<Integer, String> Category() {
        HashMap<Integer, String> MapOFCategory = new HashMap<Integer, String>();
        String query = "SELECT * FROM category";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                MapOFCategory.put(rs.getInt("id"), rs.getString("name"));


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return MapOFCategory;
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

    public void insertBook(Book B) {
        String query = "INSERT INTO Book (name, costforDay, NumberOFPage, CategoryID, Author,Description) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, B.getTitle());
            stmt.setDouble(2, B.getCostForDay());
            stmt.setInt(3, B.getNumberPage());
            stmt.setInt(4,CategoryID(B.getCategory()));
            stmt.setString(5,B.getAuthor());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int CategoryID(String CategoryName) {
    String query = "SELECT id FROM category WHERE name = ?";
    int categoryId = -1; // Default value if not found
    
    try (PreparedStatement stmt = connection.prepareStatement(query)) {
        stmt.setString(1, CategoryName);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                categoryId = rs.getInt("id");
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    return categoryId;
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

    public void insertMessage(String sendUserName, String receiverUserName, String content) {
        Timestamp sendDate = new Timestamp(System.currentTimeMillis()); // Current Date and Time
        String query = "INSERT INTO Messages (SendUserName, receiver_userName, content, SendDate) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, sendUserName);
            stmt.setString(2, receiverUserName);
            stmt.setString(3, content);
            stmt.setTimestamp(4, sendDate);
            stmt.executeUpdate();

            // Retrieve the generated ID

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int id = generatedKeys.getInt(1);
                    Message M = new Message(id, sendUserName, receiverUserName, content, sendDate);
                    LSystem.addNewMessage(M);

                } else {
                    throw new SQLException("Creating message failed, no ID obtained.");
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();

        }


    }


    public void insertBorrow(String username, int bookId, Date startDate, int numberOfDays, Date endDate, Double amountPaid) {
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
        System.out.println("fdsfs");
        // Get the borrow record details
        LSystem Sys = LSystem.getInstance();
        String selectQuery = "SELECT startDate, endDate, numberOfDays, AmountPaid FROM UserBorrowBook WHERE username = ? AND Book_id = ?";
        try (PreparedStatement selectStmt = connection.prepareStatement(selectQuery)) {
            selectStmt.setString(1, username);
            selectStmt.setInt(2, bookId);

            try (ResultSet rs = selectStmt.executeQuery()) {
                if (rs.next()) {
                    Date startDate = rs.getDate("startDate");
                    Date endDate = rs.getDate("endDate");
                    int numberOfDays = rs.getInt("numberOfDays");
                    double amountPaid = rs.getDouble("AmountPaid");

                    // Calculate the difference between the current date and the start date
                    java.util.Date currentDate = new java.util.Date();
                    long differenceInMilliSeconds = currentDate.getTime() - startDate.getTime();
                    int differenceInDays = (int) (differenceInMilliSeconds / (1000 * 60 * 60 * 24));
                    System.out.println(differenceInDays);
                    // Get the cost per day
                    double costPerDay = BookProfile.GetProfile(bookId).getCostForDay();
                    System.out.println(costPerDay);
                    // Calculate the balance update
                    int effectiveDays = Math.max(numberOfDays, differenceInDays);
                    Double balanceUpdate = (effectiveDays * costPerDay) - amountPaid;
                    System.out.println(balanceUpdate);
                    // Update the user's balance
                    Sys.AddMoneySystem(username, -balanceUpdate);

                    // If the current date is greater than the end date, call the warning method
                    if (currentDate.after(endDate)) {
                        Sys.warning(username);
                    }

                    // Remove the borrow record from the database
                    String deleteQuery = "DELETE FROM UserBorrowBook WHERE username = ? AND Book_id = ?";
                    try (PreparedStatement deleteStmt = connection.prepareStatement(deleteQuery)) {
                        deleteStmt.setString(1, username);
                        deleteStmt.setInt(2, bookId);
                        deleteStmt.executeUpdate();
                    }
                }
            }
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
                Message message = new Message(
                        rs.getInt("id"),
                        rs.getString("SendUserName"),
                        rs.getString("receiver_userName"),
                        rs.getString("content"),
                        rs.getTimestamp("SendDate")
                );
                messages.add(message);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return messages;
    }

    // Get all user reserved books
    public void blockUser(String username) {
        String query = "UPDATE Reg_User SET isblocked = ? WHERE username = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setBoolean(1, true); // Set isblocked to true
            stmt.setString(2, username);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void unblockUser(String username) {
        String query = "UPDATE Reg_User SET isblocked = ? WHERE username = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setBoolean(1, false); // Set isblocked to false
            stmt.setString(2, username);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateBalance(String username, Double newBalance) {
        String query = "UPDATE Reg_User SET balance += ? WHERE username = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setDouble(1, newBalance); // Set the new balance
            stmt.setString(2, username);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUser(String username) {
        String query = "DELETE FROM Reg_User WHERE username = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateBookUsername(int bookId, String newUsername) {
        String query = "UPDATE Book SET UserName = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, newUsername);
            stmt.setInt(2, bookId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public List<Integer> getAllReservedBooksForUser(String username) {
        List<Integer> reservedBooks = new ArrayList<>();
        String query = "SELECT bookid FROM UserResrveBOOK WHERE username = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int bookId = rs.getInt("bookid");
                    reservedBooks.add(bookId);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservedBooks;
    }

    public void getAllReservedBooks() {
        String query = "SELECT bookid,username FROM UserResrveBOOK";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int bookId = rs.getInt("bookid");
                    String UserName = rs.getString("username");
                    BookProfile.GetProfile(bookId).addToWait(UserName);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}