
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
    private Double CostForDay;   
    private String WhoTakeThisBook;   
    private HashMap<String,Boolean>WaitingForBookCome;
    private boolean Status=true;
    protected String Description;
    public Book(){}
    public boolean isAvailable()
    {
     return Status;         
    }
    public void setStatus(boolean Status) {
        this.Status = Status;
    }
    public boolean isWithUser(String UserName)
    {
       return UserName.equals(WhoTakeThisBook);
    }
    public String getDescription() {
        return Description;
    }
  
    
    public Book(int id, String Title, String Author, String Category, int NumberPage, Double CostForDay, String WhoTakeThisBook, HashMap<String, Boolean> WaitingForBookCome) {
        this.id = id;
        this.Title = Title;
        this.Author = Author;
        this.Category = Category;
        this.NumberPage = NumberPage;
        this.CostForDay = CostForDay;
        this.WhoTakeThisBook = WhoTakeThisBook;
        this.WaitingForBookCome = WaitingForBookCome;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWhoTakeThisBook() {
        return WhoTakeThisBook;
    }

    public void setWhoTakeThisBook(String  WhoTakeThisBook) {
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

    public Double getCostForDay() {
        return CostForDay;
    }

    public void setCostForDay(Double CostForDay) {
        this.CostForDay = CostForDay;
    }
    public String getCategory() {
        return Category;
    }

    public void setCategory(String Category) {
        this.Category = Category;
    }
    public void addToWait(String UserName)
    {
        if(WaitingForBookCome==null)
            WaitingForBookCome=new HashMap<String,Boolean>();
         WaitingForBookCome.put(UserName,true);
    }
     public void RemoveFromWait(String UserName)
    {
        
        if(WaitingForBookCome!=null&&WaitingForBookCome.containsKey(UserName))
                WaitingForBookCome.remove(UserName);
    }
     public boolean IsWait(String UserName)
    {
        if(WaitingForBookCome==null)
            return false;
         return WaitingForBookCome.containsKey(UserName);
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
    
    public void notifyu ()
    {
        if(WaitingForBookCome==null)
        {
            return;
        }
        LSystem Sys=LSystem.getInstance();
        for(Map.Entry<String, Boolean> entry : WaitingForBookCome.entrySet()) {
        String key = entry.getKey();
        String Title=this.Title;
        Sys.sendMessage("System", key, "Book "+Title+"  back");
        
        }
       

    
    }
    
}
class SoftWareEngineeringBook extends Book
{

    public SoftWareEngineeringBook() {
    }

    public SoftWareEngineeringBook(String Title, String Author, int NumberPage, int CostForDay) {     
        super.setCategory("SoftWareEngineering");        
    }
    public void setDescription(String Description) {
        super.Description = Description;
    }
    
        
}
class AIBook extends Book
{

  
    public AIBook() {
        
    }
    public AIBook(String Title, String Author, int NumberPage, int CostForDay) {      
        super.setCategory("AI");
        
    }
    public void setDescription(String Description) {
        super.Description = Description;
    }
    
        
}
class DataStructureBook extends Book
{

    public DataStructureBook() {
    }

    public DataStructureBook(String Title, String Author, int NumberPage, int CostForDay) {
        
    }   
    public void setDescription(String Description) {
        super.Description = Description;
    }
}

class AlgorithmBook extends Book
{

    public AlgorithmBook() {
        
    }
    public AlgorithmBook(String Title, String Author, int NumberPage, int CostForDay) {   
        super.setCategory("Algorithm");
        
    }
    public void setDescription(String Description) {
        super.Description = Description;
    }
            
}