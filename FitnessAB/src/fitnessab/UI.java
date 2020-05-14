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
                    + "2: Information om medlem\n"
                    + "3: Avsluta");
            String input = scan.nextLine();
            switch(input){
                case "1":
                    System.out.println("Namn:");
                    String namn = scan.nextLine();
                    System.out.println("Efternamn:");
                    String eNamn = scan.nextLine();
                    System.out.println("PNummer (ÅÅMMDD):");
                    int pNr = scan.nextInt();
                    logic.addMember(namn, eNamn, pNr);
                    break;
                    
                case "2":
                    System.out.println("MedlemsID:");
                    int id = scan.nextInt();
                    logic.memberLookup(id);
                    break;
                    
                case "3":
                    quit = true;
                    
                default:
                    System.out.println("Felaktig input");
                    break;
            }
        }
    }
}