/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fitnessab;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import org.sqlite.SQLiteConfig;
import java.sql.*;

/**
 *
 * @author s_ski
 */
public class DatabaseClass {
    static Connection conn = null;
    public static final String DB_URL = "jdbc:mysql:C:/m.sql";
    public static final String DRIVER = "com.mysql.jdbc.Driver"; 
    
    public void addMember(int memberID, String fName, String eName, String adress, String adressNr, String mail, int phoneNr){
        try {
            Class.forName(DRIVER);
            Connection con = DriverManager.getConnection(DB_URL);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("insert into Member (memberID, fName, eName, adress, adressNr, mail, phoneNr) VALUES ('" + memberID + "','" + fName + "','" +
                    eName + "','" + adress + "','" + adressNr + "','" + mail + "','" + phoneNr + "')");
       } catch (Exception e) {
           // Om java-progammet inte lyckas koppla upp sig mot databasen (t ex om fel sÃ¶kvÃ¤g eller om driver inte hittas) sÃ¥ kommer ett felmeddelande skrivas ut
           System.out.println( e.toString() );
           System.exit(0);
       }
    }
    
//    public void removeMember(){
//        try {
//            Class.forName(DRIVER);
//            Connection con = DriverManager.getConnection(DB_URL);
//            Statement stmt = con.createStatement();
//            ResultSet rs = stmt.executeQuery("delete from Member where memberID = " + memberID);
//       } catch (Exception e) {
//           // Om java-progammet inte lyckas koppla upp sig mot databasen (t ex om fel sÃ¶kvÃ¤g eller om driver inte hittas) sÃ¥ kommer ett felmeddelande skrivas ut
//           System.out.println( e.toString() );
//           System.exit(0);
//       }
//    }
    
//    public void createCourse(){
//        try {
//            Class.forName(DRIVER);
//            Connection con = DriverManager.getConnection(DB_URL);
//            Statement stmt = con.createStatement();
//            ResultSet rs = stmt.executeQuery("insert into Class (classID, date, time, name) VALUES ('" + classID + "','" + date + "','" + time + "','" + name + "')");
//       } catch (Exception e) {
//           // Om java-progammet inte lyckas koppla upp sig mot databasen (t ex om fel sÃ¶kvÃ¤g eller om driver inte hittas) sÃ¥ kommer ett felmeddelande skrivas ut
//           System.out.println( e.toString() );
//           System.exit(0);
//       }
//    }
    
//    public void bookCourse(){
//        try {
//            Class.forName(DRIVER);
//            Connection con = DriverManager.getConnection(DB_URL);
//            Statement stmt = con.createStatement();
//            ResultSet rs = stmt.executeQuery("insert into Booking (bookingID, member, name, date, class) VALUES ('" + bookingID + "','" + memberID + "','" + name + "','"
//                    + date + "','" + classID + "')");
//       } catch (Exception e) {
//           // Om java-progammet inte lyckas koppla upp sig mot databasen (t ex om fel sÃ¶kvÃ¤g eller om driver inte hittas) sÃ¥ kommer ett felmeddelande skrivas ut
//           System.out.println( e.toString() );
//           System.exit(0);
//       }
//    }
    
//    public void cancelCourse(){
//        try {
//            Class.forName(DRIVER);
//            Connection con = DriverManager.getConnection(DB_URL);
//            Statement stmt = con.createStatement();
//            ResultSet rs = stmt.executeQuery("Delete from booking where bookingID = " + bookingID);
//       } catch (Exception e) {
//           // Om java-progammet inte lyckas koppla upp sig mot databasen (t ex om fel sÃ¶kvÃ¤g eller om driver inte hittas) sÃ¥ kommer ett felmeddelande skrivas ut
//           System.out.println( e.toString() );
//           System.exit(0);
//       }
//    }
}