
package com.mycompany.mavenproject1;

import java.sql.Timestamp;

/**
 *
 * @author AliRagab313
 */
public class Message {
    private int id;
    private String Sender;
    private String Reciver;
    private String content;    
    private Timestamp Date;

    public Message(int id,String Sender, String Reciver, String content,  Timestamp Date) {
        this.id=id;
        this.Sender = Sender;
        this.Reciver = Reciver;
        this.content = content;
        this.Date = Date;
    }
    
    public String getSender() {
        return Sender;
    }

    public void setSender(String Sender) {
        this.Sender = Sender;
    }
    public  Timestamp  getDate() {
        return Date;
    }

    public void setDate( Timestamp  Date) {
        this.Date = Date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReciver() {
        return Reciver;
    }

    public void setReciver(String Reciver) {
        this.Reciver = Reciver;
    }
    
    @Override
    public String toString() {
        return "Message{" + "Sender=" + Sender + ", Date=" + Date + ", content=" + content + '}';
    }
    
   
   
}
