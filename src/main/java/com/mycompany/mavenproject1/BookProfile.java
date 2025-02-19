/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;

import java.util.*;

/**
 *
 * @author AliRagab313
 */
public class BookProfile {
   private BookProfile(){}
    private static HashMap<Integer,Book>ProfileList;   
    public static Book GetProfile (int ID)
    {
        return ProfileList.get(ID);
    }
    public static void Add(Book B)   
    {
        if(ProfileList==null)
            ProfileList=new  HashMap<Integer,Book>();
        ProfileList.put(B.getId(), B);
    }
    public static HashMap<Integer,Book> getProfileList ()
    {
        return ProfileList;
    }
}
