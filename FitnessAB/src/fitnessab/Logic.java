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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.Random;
import org.sqlite.SQLiteConfig;

public class Logic {
    static Connection conn = null;
    SQLiteConfig config;
    Statement stmt;
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
    private int classLimit;
    private int currentLimit;
    DatabaseClass db = new DatabaseClass();
    
    public Logic() {
    try{
            Class.forName(DRIVER);
            config = new SQLiteConfig();
            config.enforceForeignKeys(true);
            conn = DriverManager.getConnection(DB_URL,config.toProperties());
            stmt = conn.createStatement();
        } catch(Exception e){
            System.out.println( e.toString() );
            System.exit(0);
        }
    }
    /**
     * 
     */
    public void addMember() throws SQLException{
        memberIDRandom = randomMemberID(conn, config);
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
        db.addMember(memberIDRandom, personNr,  fName, sName, address, addressNr, mail, phoneNr, password, conn, config);
        System.out.println("Member " + fName + " Created! Welcome to Fitness AB");
    }
    
    public void removeMember() throws SQLException{
        System.out.println("Which Member would you like to remove?");
        try {
            stmt = conn.createStatement();
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
        db.removeMember(removeMember, conn, config);
    }
//    
    public void checkIn() throws SQLException{
        gymID = 1; 
        System.out.println("Please scan your gym card");
        gymCardID = sc.nextInt();
        String pattern = "yyyyMMdd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());
        db.checkIn(gymID, gymCardID, date, conn, config);
    }
//
    public void createCourse() throws SQLException{
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
        System.out.println("Select number of participants: ");
        classLimit = sc.nextInt();
        System.out.println("Please enter gymID: ");
        gymID = sc.nextInt();
        db.createCourse(classID, className, date, startTime, stopTime, classLimit, gymID, conn, config);
        System.out.println("Course: " + className + " created!");
    }
//    
    public void bookCourse() throws SQLException{
        getCourses();
        System.out.println("MemberID: ");
        memberID = sc.nextInt();
        randomBookingNr();
        System.out.println("Which class would you like to participate in? (classID)");
        classID = sc.nextInt();
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select memberID, fName from Member where memberID = " + memberID);
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
            ResultSet limit = stmt.executeQuery("Select avalible from classLimit where classID = " + classID);
            classLimit = limit.getInt("avalible");
            classLimit -= 1;
            if(classLimit <= 0){
                System.out.println("Course Full");
            } else{
                db.bookCourse(bookingID, memberID, classID, fName, date, classLimit, conn, config);
            }
        } catch (Exception e) {
            System.out.println( e.toString() );
            System.exit(0);
        }
        System.out.println("Confirmation: Your reservation for class: " + className + " on " + date + " has been successfully registered");
    }
//    
    public void cancelCourse() throws SQLException{
        try {
            stmt = conn.createStatement();
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
        System.out.println("Enter gymID");
        gymID = sc.nextInt();
        db.cancelCourse(classID, gymID, conn, config);
        System.out.println("Course canceled");
    }
//    
    public void cancelBooking() throws SQLException{
        System.out.println("Please enter memberID: ");
        memberID = sc.nextInt();
        viewBookings(memberID);
        System.out.println("Select booking to cancel via bookingID: ");
        bookingID = sc.nextInt();
        db.cancelBooking(memberID, bookingID, conn, config);
        System.out.println("Booking canceled");
    }
//    
    public int randomMemberID(Connection conn, SQLiteConfig config) throws SQLException {
        memberIDRandom = random.nextInt(1000000) + 1000000;
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select memberID from Member");
            while(rs.next()){
                if(rs.getInt("memberID") == memberIDRandom){
                    randomMemberID(conn, config);
                }
            }
        } catch (Exception e) {
            System.out.println( e.toString() );
            System.exit(0);
        }
        return memberIDRandom;
    }
//    
    public int randomBookingNr() throws SQLException{
        bookingID = random.nextInt(2000000) + 2000000;
        try {
            stmt = conn.createStatement();
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
//    
    public int randomClassID() throws SQLException{
        classID = random.nextInt(3000000) + 3000000;
        try {
            stmt = conn.createStatement();
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
//    
    public void viewdata() throws SQLException{
        db.viewall();
    }
//    
    public void getCourses() throws SQLException{
        String pattern = "yyyyMMdd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from Class where date1 >= " + date);
            while(rs.next()){
                System.out.println("ClassID: " + rs.getInt("classID"));
                System.out.println("ClassName: " + rs.getString("ClassName"));
                System.out.println("ClassDate: " + rs.getInt("date1"));
            }
            ResultSet limit = stmt.executeQuery("Select * from ClassLimit");
            while(limit.next()){
                System.out.println("Available: " + limit.getInt("avalible"));
                classLimit = limit.getInt("avalible");
            }
       } catch (Exception e) {
           System.out.println( e.toString() );
           System.exit(0);
       }
    }
//    
    public void viewBookings(int ID) throws SQLException{
        try {
            stmt = conn.createStatement();
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
//    
    public void checkLogin() throws SQLException{
        System.out.println("User ID: ");
        memberID = sc.nextInt();
        sc.nextLine();
        System.out.println("Password: ");
        password = sc.nextLine(); 
        db.checkLogins(memberID, password);
    }
//    
    public void updateMember() throws SQLException{
        System.out.println("MemberID: ");
        memberID = sc.nextInt();
        sc.nextLine();
        try {
            stmt = conn.createStatement();
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
            db.updateMember(relation, info, memberID, conn, config);
        } else if(relation.equals("phoneNr")){
            System.out.println("New phone number:");
            phoneNr = sc.nextInt();
            String phone = Integer.toString(phoneNr);
            db.updateMember(relation, phone, memberID, conn, config);
        }
        System.out.println("Member information updated");
    }
}