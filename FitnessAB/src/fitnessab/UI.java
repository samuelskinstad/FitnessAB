/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fitnessab;

import java.sql.SQLException;
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
    public void UI() throws SQLException{
        while(!quit){

            System.out.println("1: Add Member\n" 
                    + "2: Log In\n"
                    + "3: Remove Member\n"
                    + "4: Create Class\n"
                    + "5: Reservation for Class\n"
                    + "6: Cancel reservation for Class\n"
                    + "7: Cancel class\n"
                    + "8: View All Members\n"
                    + "9: update member\n"
                    + "10: check in\n"
                    + "11: Quit");
            String input = scan.nextLine().trim();
            switch(input){
                case "1":
                    logic.addMember();
                    break;
                    
                case "2":
                    logic.checkLogin();
                    break;
                    
                case "3":
                    logic.removeMember();
                    break;
                    
                case "4":
                    logic.createCourse();
                    break;
                    
                case "5":
                    logic.bookCourse();
                    break;
                    
                case "6":
                    logic.cancelBooking();
                    break;
                    
                case "7":
                    logic.cancelCourse();
                    break;
                    
                case "8":
                    logic.viewdata();
                    break;
                 
                case "9":
                    logic.updateMember();
                    break;
                    
                case "10":
                    logic.checkIn();
                    break;
                    
                case "11":
                    quit = true;
                    break;
                    
                default:
                    System.out.println("Incorrect input");
                    break;
            }
        }
    }
}
