/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fitnessab;

/**
 *
 * @author s_ski
 */
import java.util.Scanner;
import java.util.Random;

        
public class Logic {
    String fName;
    String sName;
    int peronNr;
    String address; 
    String addressNr;
    int phoneNr; 
    Scanner sc = new Scanner();
    Random ranMember = new Random();
    
    public Logic() {}
            
    public void addMember(){
        //Create Random number 
        System.out.println("Enter your first name");
        fName = sc.nextLine();
        System.out.println("Enter your surname");
        sName = sc.nextLine();
        System.out.println("Enter your address name");
        address = sc.nextInt();
        System.out.println("Enter your address number");
        addressNr = sc.nextLine();
        System.out.println("Enter your phone number");
        phoneNr = sc.nextInt(); 
            
    }
    public void removeMember(int id){
        //TODO remove member
    }
    public boolean checkIn(int scannedCard){
        if scannedCard == 1
            return true; 
        return false;                     
    }
        //TODO check
  
    public void createCourse(){
        
    }
    public void bookCourse(){
        
    }
    public void cancelCourse(){
        
    }
    public void randomMemberID() {
    
    }
}
