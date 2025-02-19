
package com.mycompany.mavenproject1;

import java.util.regex.*;

/**
 *
 * @author AliRagab313
 */
public class Validation {
    private Validation(){}
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";

    public static boolean CheckEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean CheckPassword(String Password)
    {
        
        return Password.length()>2;
    }
   public static boolean CheckField(String text)
   {
       return !text.isEmpty();
   }
}
