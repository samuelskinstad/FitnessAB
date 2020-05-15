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
import static fitnessab.DatabaseClass.DB_URL;
import static fitnessab.DatabaseClass.DRIVER;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
import java.util.Random;
import org.sqlite.SQLiteConfig;

        
public class Logic {
    static Connection conn = null;
    public static final String DB_URL = "jdbc:sqlite:C:/m.db";
    public static final String DRIVER = "org.sqlite.JDBC";
    Scanner sc = new Scanner(System.in);
    Random random = new Random();
    String fName;
    String sName;
    int peronNr;
    String address; 
    String addressNr;
    String mail;
    int phoneNr; 
    String className;
    int date;
    int startTime;
    int stopTime;
    int memberIDRandom;
    int bookingID;
    int classID;
    int removeMember;
    DatabaseClass db = new DatabaseClass();
    public Logic() {}
            
    public void addMember(){
        int random = randomMemberID();
        System.out.println("Enter your first name");
        fName = sc.nextLine();
        System.out.println("Enter your surname");
        sName = sc.nextLine();
        System.out.println("Enter your address name");
        address = sc.nextLine();
        System.out.println("Enter your address number");
        addressNr = sc.nextLine();
        System.out.println("Enter your mail");
        mail = sc.nextLine();
        System.out.println("Enter your phone number");
        phoneNr = sc.nextInt();    
        db.addMember(random, fName, sName, address, addressNr, mail, phoneNr);
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
        
    }
    public void cancelCourse(){
        
    }
    public int randomMemberID() {
        memberIDRandom = random.nextInt(1000000) + 1000000; //Måste loopa igenom databasen och se så det inte skapas dubletter 
        try {
            Class.forName(DRIVER);
            Connection con = DriverManager.getConnection(DB_URL);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("Select memberID from Member");
            while(rs.next()){
                if(rs.getInt("memberID") == memberIDRandom){
                    randomMemberID();
                }
            }
       } catch (Exception e) {
           // Om java-progammet inte lyckas koppla upp sig mot databasen (t ex om fel sÃ¶kvÃ¤g eller om driver inte hittas) sÃ¥ kommer ett felmeddelande skrivas ut
           System.out.println( e.toString() );
           System.out.println("error");
           System.exit(0);
       }
        return memberIDRandom;
    }
    public void randomBookingNr(){
        bookingID = random.nextInt(2000000) + 2000000; //Måste loopa igenom databasen och se så det inte skapas dubletter
    }
    public void randomClassID(){
        classID = random.nextInt(100) + 100; //Samma som ovan
    
    }
}
