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
import static fitnessab.DatabaseClass.conn;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
import java.util.Random;
import org.sqlite.SQLiteConfig;

        
public class Logic {
    static Connection conn = null;
    public static final String DB_URL = "jdbc:sqlite:C:/test111.db";
    public static final String DRIVER = "org.sqlite.JDBC";
    private Scanner sc = new Scanner(System.in);
    private Random random = new Random();
    private String fName;
    private String sName;
    private int peronNr;
    private String address; 
    private String addressNr;
    private String mail;
    private int phoneNr; 
    private String className;
    private int date;
    private int startTime;
    private int stopTime;
    private int memberIDRandom;
    private int bookingID;
    private int classID;
    private int removeMember;
    private String password; 
    private int memberID; 
    DatabaseClass db = new DatabaseClass();
    public Logic() {}
            
    public void addMember(){
        randomMemberID();
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
        db.addMember(memberIDRandom, fName, sName, address, addressNr, mail, phoneNr);
    }
    
    public void removeMember(){
        System.out.println("Which Member would you like to remove?");
        System.out.println("Enter Member ID");
        removeMember = sc.nextInt();
        db.removeMember(removeMember);
        
    }
    
    public boolean checkIn(int scannedCard){
        if (scannedCard == 1)
            return true; 
        return false;                     
    }
        
  
    public void createCourse(){
        randomClassID();
        System.out.println("Name of class?");
        className = sc.nextLine();
        System.out.println("Enter which date the class will be held");
        date = sc.nextInt();
        System.out.println("Enter start time (Example 20 for 20.00");
        startTime = sc.nextInt();
        System.out.println("Enter stop time (Example 21 for 21.00)");
        stopTime = sc.nextInt();
        db.createCourse(classID, className, date, startTime, stopTime);
        
    }
    public void bookCourse(){
        System.out.println("MemberID: ");
        int input = sc.nextInt();
        int memberID = 0;
        String name = "";
        int date = 0;
        randomBookingNr();
        System.out.println("Which class would you like to participate in?");
        className = sc.nextLine();
        System.out.println("Which date?");
        date = sc.nextInt();
        try {
            Class.forName(DRIVER);
            SQLiteConfig config = new SQLiteConfig();
            config.enforceForeignKeys(true);
            conn = DriverManager.getConnection(DB_URL,config.toProperties());
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select memberID, classID, fName, date1 from Booking where memberID = " + input);
            while(rs.next()){
                memberID = rs.getInt("memberID");
                classID = rs.getInt("classiD");
                name = rs.getString("fName");
                date = rs.getInt("date1");
            }
            db.bookCourse(bookingID, memberID, classID, name, date);
       } catch (Exception e) {
           System.out.println( e.toString() );
           System.exit(0);
       }
        System.out.println("Confirmation: Your reservation for class: " + className + " on " + date + " has been successfully registered");
    }
    
    public void cancelCourse(){
        System.out.println("Enter Class ID");
        classID = sc.nextInt();
        System.out.println("Which date?");
        date = sc.nextInt();
        System.out.println("hehehehe");
        
    }
    
    public int randomMemberID() {
        memberIDRandom = random.nextInt(1000000) + 1000000;
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(DB_URL);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select memberID from Member");
            while(rs.next()){
                if(rs.getInt("memberID") == memberIDRandom){
                    randomMemberID();
                }
            }
       } catch (Exception e) {
           // Om java-progammet inte lyckas koppla upp sig mot databasen (t ex om fel sÃ¶kvÃ¤g eller om driver inte hittas) sÃ¥ kommer ett felmeddelande skrivas ut
           System.out.println( e.toString() );
           System.exit(0);
       }
        return memberIDRandom;
    }
    
    public int randomBookingNr(){
        bookingID = random.nextInt(2000000) + 2000000;
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(DB_URL);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select bookingID from Booking");
            while(rs.next()){
                if(rs.getInt("bookingID") == bookingID){
                    randomBookingNr();
                }
            }
       } catch (Exception e) {
           // Om java-progammet inte lyckas koppla upp sig mot databasen (t ex om fel sÃ¶kvÃ¤g eller om driver inte hittas) sÃ¥ kommer ett felmeddelande skrivas ut
           System.out.println( e.toString() );
           System.exit(0);
       }
        return bookingID;
    }
    
    public int randomClassID(){
        classID = random.nextInt(3000000) + 3000000;
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(DB_URL);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select classID from Class");
            while(rs.next()){
                if(rs.getInt("classID") == classID){
                    randomClassID();
                }
            }
       } catch (Exception e) {
           // Om java-progammet inte lyckas koppla upp sig mot databasen (t ex om fel sÃ¶kvÃ¤g eller om driver inte hittas) sÃ¥ kommer ett felmeddelande skrivas ut
           System.out.println( e.toString() );
           System.exit(0);
       }
        return classID;
    }
    public void viewdata(){
        db.viewall();
    }
    
    public void checkLogin(){
        System.out.println("User ID: ");
        memberID = sc.nextInt();
        System.out.println("Password: ");
        password = sc.nextLine();
        //TODO database 
        if (member == memberID && password == passwordID) {
            System.out.println("Login Successful");
        }else{
            System.out.println("Access Denied");
        }
        
        
        
    }
}
