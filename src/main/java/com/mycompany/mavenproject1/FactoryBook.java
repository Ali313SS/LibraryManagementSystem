/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;

/**
 *
 * @author AliRagab313
 */
public class FactoryBook {
    public static Book createBook(String category, String title, String author, int numberPage, int costForDay) {
        switch (category) {
            case "SoftWareEngineering":
                return new SoftWareEngineeringBook(title, author, numberPage, costForDay);
            case "AI":
                return new AIBook(title, author, numberPage, costForDay);
            case "DataStructure":
                return new DataStructureBook(title, author, numberPage, costForDay);
            case "Algorithm":
                return new AlgorithmBook(title, author, numberPage, costForDay);
            default:
                throw new IllegalArgumentException("Unknown category: " + category);
        }
    }
}
