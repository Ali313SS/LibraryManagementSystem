
package com.mycompany.mavenproject1;

/**
 *
 * @author AliRagab313
 */
public class Message {
    private String Sender;
    private String Date;
    private String content;
    private String Reciver;
    public String getSender() {
        return Sender;
    }

    public void setSender(String Sender) {
        this.Sender = Sender;
    }
    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Message{" + "Sender=" + Sender + ", Date=" + Date + ", content=" + content + '}';
    }
    
   
   
}
