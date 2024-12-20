/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;
import java.util.*;
import java.util.concurrent.TimeUnit;
/**
 *
 * @author AliRagab
 */
public class CalcDate {

    private CalcDate(){}
    // Method to subtract two dates and return the difference in days
    public static int getDateDifference(Date date1, Date date2) {
        long diffInMillis = date2.getTime() - date1.getTime();
        return (int) TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS);
    }

    // Method to add a number of days to a date and return the new date
    public static Date addDaysToDate(Date date, int daysToAdd) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, daysToAdd);
        return calendar.getTime();
    }

}
