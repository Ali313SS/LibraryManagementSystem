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
   
    public void BlockUser(String UserName);  
    public void RemoveUser(String UserName);            
    public void addUserToblackList(String UserName);
    public void ReomveUserFromblackList(String UserName);
    public void AddBalnce(String UserName);
    
}
