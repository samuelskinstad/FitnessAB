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
    Scanner sc = new Scanner(System.in);
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
    int randomNr; 
    
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
        //Todo Database connection 
    }
    public void removeMember(int id){
        System.out.println("Which Member would you like to remove?");
        System.out.println("Enter Member ID");
        removeMember = sc.nextInt();
        //TODO Database connection 
        
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
        //TODO Database connection
        
    }
    public void bookCourse(){
        System.out.println("Which class would you like to participate in?");
        className = sc.nextLine();
        System.out.println("Which date?");
        date = sc.nextInt();
        //TODO Database
        System.out.println("Confirmation: Your reservation for class: " + className + " on " + date + " has been successfully registered");
        
        
    }
    public void cancelCourse(){
        System.out.println("Enter Class ID");
        classID = sc.nextInt();
        System.out.println("Which date?");
        date = sc.nextInt(); 
        /TODO Database 

        
        

        
    }
    public void randomMemberID() {
        randomNr = random.nextInt(1000000) + 1000000; //Måste loopa igenom databasen och se så det inte skapas dubletter 
       // for (id : database)
           //boolean result = database.contains(memberID);
           //if (result == true) 
           //  return 
    }
    public void randomBookingNr(){
        randomNr = random.nextInt(2000000) + 2000000; //Måste loopa igenom databasen och se så det inte skapas dubletter
    }
    public void randomClassID(){
        randomNr = random.nextInt(100) + 100; //Samma som ovan
    
    }
}
