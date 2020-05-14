/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fitnessab;

import java.util.Scanner;

/**
 *
 * @author s_ski
 */
public class UI {
    Scanner scan = new Scanner(System.in);
    Logic logic;
    public void UI(){
        System.out.println("1: lägg till ny medlem");
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
        }
    }
}
