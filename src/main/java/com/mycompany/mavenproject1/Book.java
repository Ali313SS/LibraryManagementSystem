
package com.mycompany.mavenproject1;
import java.util.*;

/**
 *
 * @author AliRagab313
 */
public class  Book {
    private int id;
    private String Title;
    private String Author;
    private String Category;
    private int NumberPage;
    private int CostForDay;   
    private User WhoTakeThisBook;
    private HashMap<String,Boolean>WaitingForBookCome;
    public Book(){}
    public Book(String Title, String Author, int NumberPage, int CostForDay) {
        this.Title = Title;
        this.Author = Author;
        this.NumberPage = NumberPage;
        this.CostForDay = CostForDay;
    
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getWhoTakeThisBook() {
        return WhoTakeThisBook;
    }

    public void setWhoTakeThisBook(User WhoTakeThisBook) {
        this.WhoTakeThisBook = WhoTakeThisBook;
    }

    public HashMap<String, Boolean> getWaitingForBookCome() {
        return WaitingForBookCome;
    }

    public void setWaitingForBookCome(HashMap<String, Boolean> WaitingForBookCome) {
        this.WaitingForBookCome = WaitingForBookCome;
    }
    
    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String Author) {
        this.Author = Author;
    }

    public int getNumberPage() {
        return NumberPage;
    }

    public void setNumberPage(int NumberPage) {
        this.NumberPage = NumberPage;
    }

    public int getCostForDay() {
        return CostForDay;
    }

    public void setCostForDay(int CostForDay) {
        this.CostForDay = CostForDay;
    }
    public String getCategory() {
        return Category;
    }

    public void setCategory(String Category) {
        this.Category = Category;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Book other = (Book) obj;
        if (this.NumberPage != other.NumberPage) {
            return false;
        }
        if (this.CostForDay != other.CostForDay) {
            return false;
        }
        if (!Objects.equals(this.Title, other.Title)) {
            return false;
        }
        return Objects.equals(this.Author, other.Author);
    }

    @Override
    public String toString() {
        return "Book{" + "Title=" + Title + ", Author=" + Author + ", NumberPage=" + NumberPage + ", CostForDay=" + CostForDay  + '}';
    }
    
    
    
}
class SoftWareEngineeringBook extends Book
{

    public SoftWareEngineeringBook() {
    }

    public SoftWareEngineeringBook(String Title, String Author, int NumberPage, int CostForDay) {
        super(Title, Author, NumberPage, CostForDay);
        super.setCategory("SoftWareEngineering");
        
    }
    
        
}
class AIBook extends Book
{

    public AIBook() {
    }

    public AIBook(String Title, String Author, int NumberPage, int CostForDay) {
        super(Title, Author, NumberPage, CostForDay);
        super.setCategory("AI");
        
    }
    
        
}
class DataStructureBook extends Book
{

    public DataStructureBook() {
    }

    public DataStructureBook(String Title, String Author, int NumberPage, int CostForDay) {
        super(Title, Author, NumberPage, CostForDay);
        super.setCategory("DataStructure");
        
    }   
}

class AlgorithmBook extends Book
{

    public AlgorithmBook() {
        
    }
    public AlgorithmBook(String Title, String Author, int NumberPage, int CostForDay) {
        super(Title, Author, NumberPage, CostForDay);
        super.setCategory("Algorithm");
        
    }
            
}