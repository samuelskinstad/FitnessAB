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
    Scanner sc = new Scanner();
    Random random = new Random();
    String fName;
    String sName;
    int peronNr;
    String address; 
    String addressNr;
    int phoneNr; 
    String className;
    int date;
    int startTime;
    int stopTime;
    int memberID;
    int bookingID;
    int classID;
    int removeMember;
    
    public Logic() {}
            
    public void addMember(){
        //Create Random number 
        System.out.println("Enter your first name");
        fName = sc.nextLine();
        System.out.println("Enter your surname");
        sName = sc.nextLine();
        System.out.println("Enter your address name");
        address = sc.nextLine();
        System.out.println("Enter your address number");
        addressNr = sc.nextLine();
        System.out.println("Enter your phone number");
        phoneNr = sc.nextInt();    
    }
    public void removeMember(int id){
        System.out.println("Which Member would you like to remove?");
        System.out.println("Enter Member ID");
        removeMember = sc.nextInt();
        
    }
    public boolean checkIn(int scannedCard){
        if (scannedCard == 1)
            return true; 
        return false;                     
    }
        
  
    public void createCourse(){
        //create random number
        System.out.println("Name of class?");
        className = sc.nextLine();
        System.out.println("Enter which date the class will be held");
        date = sc.nextInt();
        System.out.println("Enter start time (Example 20 for 20.00");
        startTime = sc.nextInt();
        System.out.println("Enter stop time (Example 21 for 21.00)");
        stopTime = sc.nextInt();
        
    }
    public void bookCourse(){
        
    }
    public void cancelCourse(){
        
    }
    public void randomMemberID() {
        memberID = random.nextInt(1000000) + 1000000; //Måste loopa igenom databasen och se så det inte skapas dubletter 
    }
    public void randomBookingNr(){
        bookingID = random.nextInt(2000000) + 2000000; //Måste loopa igenom databasen och se så det inte skapas dubletter
    }
    public void randomClassID(){
        classID = random.nextInt(100) + 100; //Samma som ovan
    
    }
}
