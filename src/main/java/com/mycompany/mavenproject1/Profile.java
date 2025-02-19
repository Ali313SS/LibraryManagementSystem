package com.mycompany.mavenproject1;
import java.util.*;
/**
 *
 * @author AliRagab313
 */

public class Profile {
    
    private static Profile Instance;
    
    private static HashMap<String,User>ProfileList;   
    public static Profile getInstance()
    {
        
        if(Instance==null)
            Instance=new Profile();
        return Instance;
    }
    private Profile(){
        ProfileList=new HashMap<String,User>();
    }
    
    public static User GetProfile (String UserName)
    {
        return ProfileList.get(UserName);
    }
    public static void removeProfile (String UserName)
    {
        ProfileList.remove(UserName);
    }
    public static void Add(User U)
    {
        ProfileList.put(U.getUserName(), U);
    }
    
    public static HashMap<String,User> getProfileList(){
        return ProfileList;
    }
}
