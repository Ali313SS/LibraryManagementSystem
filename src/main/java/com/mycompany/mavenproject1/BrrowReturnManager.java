/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.mavenproject1;

/**
 *
 * @author 20101
 */
public interface BrrowReturnManager {
    public boolean borrowBook(String userName, int id, int numberOfDays, Double amountPaid) ;
    public boolean returnBook(String UserName,int id);
    public boolean reserveBook(String UserName,int id);
    public boolean unreserveBook(String UserName,int id);
           
}
