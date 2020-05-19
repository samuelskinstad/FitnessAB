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
            System.out.println("1: Add Member\n"
                    + "2: Remove Member\n"
                    + "3: Create Class\n"
                    + "4: Reservation for Class\n"
                    + "5: Cancel reservation for Class\n"
                    + "6: Cancel Class\n"
                    + "7: Quit Program");
            String input = scan.nextLine().trim();
            switch(input){
                case "1":
                    logic.addMember();
                    break;
                    
                case "2":
                    logic.removeMember();
                    break;
                    
                case "3":
                    logic.createCourse();
                    break;
                    
                case "4":
                    logic.bookCourse();
                    break;
                    
                case "5":
                    logic.cancelBooking();
                    break;
                    
                case "6":
                    logic.cancelCourse();
                    break;
                    
                case "7":
                    quit = true;
                    break;
                    
                case "viewall":
                    logic.viewdata();
                default:
                    System.out.println("Felaktig input");
                    break;
            }
        }
    }
}