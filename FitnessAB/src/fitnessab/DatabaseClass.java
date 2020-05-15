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

/**
 *
 * @author s_ski
 */
public class DatabaseClass {
    static Connection conn = null;
    public static final String DB_URL = "jdbc:sqlite:C:/test111.db";
    public static final String DRIVER = "org.sqlite.JDBC"; 
    
    public void addMember(int memberID, String fName, String eName, String adress, String adressNr, String mail, int phoneNr){
        try {
            Class.forName(DRIVER);
            SQLiteConfig config = new SQLiteConfig();
            config.enforceForeignKeys(true);
            conn = DriverManager.getConnection(DB_URL,config.toProperties());
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("insert into Member (memberID, fName, eName, adress, adressNr, mail, phoneNr) VALUES ('" + memberID + "','" + fName + "','" +
                    eName + "','" + adress + "','" + adressNr + "','" + mail + "','" + phoneNr + "')");
       } catch (Exception e) {
           // Om java-progammet inte lyckas koppla upp sig mot databasen (t ex om fel sÃ¶kvÃ¤g eller om driver inte hittas) sÃ¥ kommer ett felmeddelande skrivas ut
           System.out.println( e.toString() );
           System.exit(0);
       }
    }
    public void viewall(){
         try {
            Class.forName(DRIVER);
            Connection con = DriverManager.getConnection(DB_URL);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from Member");
             while(rs.next()){
                 System.out.println("ID: " + rs.getInt("memberID") + ",");
                 System.out.println("Name: " + rs.getString("fName") + ",");
                 System.out.println("LastName: " + rs.getString("eName") + ",");
                 System.out.println("Address: " + rs.getString("adress") + ",");
                 System.out.println("AddressNr: " + rs.getString("adressNr") + ",");
                 System.out.println("Mail: " + rs.getString("mail") + ",");
                 System.out.println("phoneNr: " + rs.getInt("phoneNr"));
             }
       } catch (Exception e) {
           // Om java-progammet inte lyckas koppla upp sig mot databasen (t ex om fel sÃ¶kvÃ¤g eller om driver inte hittas) sÃ¥ kommer ett felmeddelande skrivas ut
           System.out.println( e.toString() );
           System.exit(0);
       }
    }
    
    public void removeMember(int memberID){
        try {
            Class.forName(DRIVER);
            SQLiteConfig config = new SQLiteConfig();
            config.enforceForeignKeys(true);
            conn = DriverManager.getConnection(DB_URL,config.toProperties());
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("delete from Member where memberID = " + memberID);
       } catch (Exception e) {
           // Om java-progammet inte lyckas koppla upp sig mot databasen (t ex om fel sÃ¶kvÃ¤g eller om driver inte hittas) sÃ¥ kommer ett felmeddelande skrivas ut
           System.out.println( e.toString() );
           System.exit(0);
       }
    }
    
    public void createCourse(int ID, String className, int date, int startTime, int stopTime){
        try {
            Class.forName(DRIVER);
            SQLiteConfig config = new SQLiteConfig();
            config.enforceForeignKeys(true);
            conn = DriverManager.getConnection(DB_URL,config.toProperties());
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("insert into Class (classID, className, date, time, name) VALUES ('" + ID + "','" +
                    className + "','" + date + "','" +startTime + "','" + stopTime + "')");
       } catch (Exception e) {
           // Om java-progammet inte lyckas koppla upp sig mot databasen (t ex om fel sÃ¶kvÃ¤g eller om driver inte hittas) sÃ¥ kommer ett felmeddelande skrivas ut
           System.out.println( e.toString() );
           System.exit(0);
       }
    }
    
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