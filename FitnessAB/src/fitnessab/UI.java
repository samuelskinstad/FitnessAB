/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fitnessab;

import java.util.Scanner;
import java.util.Random; 

/**
 *
 * @author s_ski
 */
public class UI {
    Scanner scan = new Scanner(System.in);
    Logic logic = new Logic();
    boolean quit = false;
    Random randomID = new Random();
    public void UI(){
        while(!quit){
            System.out.println("1: lägg till ny medlem\n"
                    + "2: Ta bort medlem\n"
                    + "3: Skapa pass\n"
                    + "4: Boka pass\n"
                    + "5: Avboka pass\n"
                    + "6: Avsluta programmet");
            String input = scan.nextLine();
            switch(input){
                case "1":
                    logic.addMember();
                    break;
                    
                case "2":
                    System.out.println("MedlemsID:");
                    int id = scan.nextInt();
                    logic.removeMember(id);
                    break;
                    
                case "3":
                    logic.createCourse();
                    
                case "4":
                    logic.bookCourse();
                    
                case "5":
                    logic.cancelCourse();
                    
                case "6":
                    quit = true;
                    
                default:
                    System.out.println("Felaktig input");
                    break;
            }
        }
    }
}