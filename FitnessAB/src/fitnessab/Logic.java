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
import java.text.SimpleDateFormat;
import java.util.Date;
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
    private String address; 
    private String addressNr;
    private String mail;
    private int phoneNr; 
    private String className;
    private String stringDate;
    private int date;
    private int startTime;
    private int stopTime;
    private int memberIDRandom;
    private int bookingID;
    private int classID;
    private int removeMember;
    double personNr;
    String password; 
    int memberID; 
    private int gymID;
    private int gymCardID;
    DatabaseClass db = new DatabaseClass();
    
    public Logic() {}    
    /**
     * 
     */
    public void addMember(){
        memberIDRandom =  randomMemberID();
        System.out.println(memberIDRandom);
        System.out.println("Enter Social Security Number - YYYYMMDDXXXX");
        personNr = sc.nextDouble();
        sc.nextLine();
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
        sc.nextLine();
        System.out.println("Enter your password");
        password = sc.nextLine();
        db.addMember(memberIDRandom, personNr,  fName, sName, address, addressNr, mail, phoneNr, password);
    }
    
    public void removeMember(){
        System.out.println("Which Member would you like to remove?");
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(DB_URL);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select memberID, fName from Member");
            while(rs.next()){
                System.out.println("Name: " + rs.getString("fName"));
                System.out.println("ID: " + rs.getInt("memberID"));
            }
       } catch (Exception e) {
           System.out.println( e.toString() );
           System.exit(0);
       }
        System.out.println("Enter Member ID");
        removeMember = sc.nextInt();
        db.removeMember(removeMember);
    }
    
    public void checkIn(){
        gymID = 1; 
        System.out.println("Please scan your gym card");
        gymCardID = sc.nextInt();
        String pattern = "yyyyMMdd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());
        db.checkIn(gymID, gymCardID, date);        
    }

    public void createCourse(){
        boolean format = false;
        randomClassID();
        System.out.println("Name of class?");
        className = sc.nextLine();
        System.out.println("Enter which date the class will be held (YYYYMMDD)");
        stringDate = sc.nextLine();
        while(!format){
            if(stringDate.length() != 8){
                System.out.println("Wrong format, try again (YYYYMMDD)");
                stringDate = sc.nextLine();
            } else{
                date = Integer.parseInt(stringDate);
                format = true;
            }
        }
        System.out.println("Enter start time of class (for example: 20 for 20:00)");
        startTime = sc.nextInt();
        System.out.println("Enter stop time of class (for example: 21 for 21:00)");
        stopTime = sc.nextInt();
        db.createCourse(classID, className, date, startTime, stopTime);
    }
    
    public void bookCourse(){
        getCourses();
        System.out.println("MemberID: ");
        int input = sc.nextInt();
        randomBookingNr();
        sc.nextLine();
        System.out.println("Which class would you like to participate in? (classID)");
        classID = sc.nextInt();
        try {
            Class.forName(DRIVER);
            SQLiteConfig config = new SQLiteConfig();
            config.enforceForeignKeys(true);
            conn = DriverManager.getConnection(DB_URL,config.toProperties());
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select memberID, fName from Member where memberID = " + input);
            while(rs.next()){
                memberID = rs.getInt("memberID");
                fName = rs.getString("fName");
            }
            ResultSet kurs = stmt.executeQuery("Select classID, date1, className from Class where classID = " + classID);
            while(kurs.next()){
                date = kurs.getInt("date1");
                classID = kurs.getInt("classID");
                className = kurs.getString("className");
            }
            db.bookCourse(bookingID, memberID, classID, fName, date);
       } catch (Exception e) {
           System.out.println( e.toString() );
           System.exit(0);
       }
        System.out.println("Confirmation: Your reservation for class: " + className + " on " + date + " has been successfully registered");
    }
    
    public void cancelCourse(){
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(DB_URL);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select classID, date1, ClassName from Class");
            while(rs.next()){
                System.out.println("Class: " + rs.getString("ClassName"));
                System.out.println("Date: " + rs.getInt("date1"));
                System.out.println("ID: " + rs.getInt("classID"));
            }
       } catch (Exception e) {
           System.out.println( e.toString() );
           System.exit(0);
       }
        System.out.println("Enter Class ID");
        classID = sc.nextInt();
        db.cancelCourse(classID);
    }
    
    public void cancelBooking(){
        System.out.println("Please enter memberID: ");
        memberID = sc.nextInt();
        viewBookings(memberID);
        System.out.println("Select booking to cancel via bookingID: ");
        bookingID = sc.nextInt();
        db.cancelBooking(memberID, bookingID);
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
           System.out.println( e.toString() );
           System.exit(0);
       }
        return classID;
    }
    
    public void viewdata(){
        db.viewall();
    }
    
    public void getCourses(){
        String pattern = "yyyyMMdd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());
        try {
            Class.forName(DRIVER);
            SQLiteConfig config = new SQLiteConfig();
            config.enforceForeignKeys(true);
            conn = DriverManager.getConnection(DB_URL,config.toProperties());
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from Class where date1 >= " + date);
            while(rs.next()){
                System.out.println("ClassID: " + rs.getInt("classID"));
                System.out.println("ClassName: " + rs.getString("ClassName"));
                System.out.println("ClassDate: " + rs.getInt("date1"));
            }
       } catch (Exception e) {
           System.out.println( e.toString() );
           System.exit(0);
       }
    }
    
    public void viewBookings(int ID){
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(DB_URL);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select memberID, fName, bookingID, date1 from Booking");
            while(rs.next()){
                if(rs.getInt("memberID") == ID){
                    System.out.println("Name: " + rs.getString("fName"));
                    System.out.println("BookingID: " + rs.getInt("bookingID"));
                    System.out.println("Date: " + rs.getInt("date1"));
                }
            }
        } catch (Exception e) {
            System.out.println( e.toString() );
            System.exit(0);
        }
    }
    
    public void checkLogin(){
        System.out.println("User ID: ");
        memberID = sc.nextInt();
        sc.nextLine();
        System.out.println("Password: ");
        password = sc.nextLine(); 
        db.checkLogins(memberID, password);
    }
    
    public void updateMember(){
        System.out.println("MemberID: ");
        memberID = sc.nextInt();
        sc.nextLine();
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(DB_URL);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from Member");
            while(rs.next()){
                if(rs.getInt("memberID") == memberID){
                    System.out.println("Forename: " + rs.getString("fName"));
                    System.out.println("Lastname: " + rs.getString("eName"));
                    System.out.println("Adress: " + rs.getString("adress"));
                    System.out.println("Adress number: " + rs.getString("adressNr"));
                    System.out.println("Mail: " + rs.getString("mail"));
                    System.out.println("Phone number: " + rs.getInt("phoneNr"));
                    System.out.println("Password: " + rs.getString("password"));
                }
            }
       } catch (Exception e) {
           System.out.println( e.toString() );
           System.exit(0);
       }
        System.out.println("Select information to update (eg: fName\n"
                    + "1: fName: \n" 
                    + "2: eName: \n"
                    + "3: adress: \n"
                    + "4: adressNr: \n"
                    + "5: mail: \n"
                    + "6: phoneNr: \n"
                    + "7: password\n");
        String relation = sc.nextLine();
        if(relation.equals("fName") || relation.equals("eName")
                || relation.equals("adress") || relation.equals("adressNr")
                || relation.equals("mail") || relation.equals("password")){
            System.out.println("New information: ");
            String info = sc.nextLine();
            db.updateMember(relation, info, memberID);
        } else if(relation.equals("phoneNr")){
            System.out.println("New phone number:");
            phoneNr = sc.nextInt();
            String phone = Integer.toString(phoneNr);
            db.updateMember(relation, phone, memberID);
        }
        
    }
}
