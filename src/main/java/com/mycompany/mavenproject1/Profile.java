package com.mycompany.mavenproject1;
import java.util.*;
/**
 *
 * @author AliRagab313
 */

public class Profile {
    
    private Profile(){}
    private static HashMap<String,User>ProfileList;   
    public static User GetProfile (String UserName)
    {
        return ProfileList.get(UserName);
    }
    public static void Add(User U)
    {
        ProfileList.put(U.getUserName(), U);
    }
}
