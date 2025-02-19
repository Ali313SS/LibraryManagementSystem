/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;


/**
 *
 * @author AliRagab313
 */
public class Main {
    public static void main(String args [])
    {
       DataBase DB=DataBase.getInstance();
       //DB.getBlockList();
       LSystem Sys=LSystem.getInstance();
       new Login().setVisible(true);        
      
    }
}
