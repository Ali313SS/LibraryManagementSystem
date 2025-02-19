/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.mavenproject1;

/**
 *
 * @author AliRagab313
 */
public interface Manager {
   
    public  void BlockUser(String UserName);  
    public  void UNBlockUser(String UserName);  
    public void RemoveUser(String UserName);            
    public boolean AddMoneyAdmin(String UserName,double Money);
    public boolean AddMoneySystem(String UserName,double Money);
    
}
