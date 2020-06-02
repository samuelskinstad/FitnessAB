/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fitnessab;

import static fitnessab.Logic.conn;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.sqlite.SQLiteConfig;

/**
 *
 * @author s_ski
 */

public class DatabaseClass {
    public void addMember(int memberIDRandom, double personNr, String fName,
            String eName, String adress, String adressNr, String mail, int phoneNr,
            String password, Connection conn, SQLiteConfig config) throws SQLException{
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("insert into Member (memberID, personNr, fName, eName, adress, adressNr, mail, phoneNr, password) VALUES ('" + memberIDRandom + "','" + personNr + "','" +  fName + "','" +
                    eName + "','" + adress + "','" + adressNr + "','" + mail + "','" + phoneNr + "', '" + password + "' )");
        } catch (Exception e) {
            System.err.println(e.toString());
            System.exit(0);
        }
    }
    
    public void viewall() throws SQLException{
         try {
            Statement stmt = conn.createStatement();
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
             //ResultSet result = stmt.executeQuery("Select * from Class");
//             while(result.next()){
//                 System.out.println("ID: " + result.getInt("ClassID") + ",");
//                 System.out.println("date1: " + result.getInt("date1") + ",");
//                 System.out.println("startTime: " + result.getInt("startTime") + ",");
//                 System.out.println("stopTime " + result.getInt("stopTime") + ",");
//                 System.out.println("className: " + result.getString("ClassName"));
//             }
       } catch (Exception e) {
           System.out.println( e.toString() );
           System.exit(0);
       }
    }
    
    public void removeMember(int memberID, Connection conn, SQLiteConfig config) throws SQLException{
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("delete from Member where memberID = " + memberID);
       } catch (Exception e) {
           System.out.println( e.toString() );
           System.exit(0);
       }
    }
    
    public void createCourse(int ID, String className, int date, int startTime, int stopTime, int totalLimit, int gymID, Connection conn, SQLiteConfig config) throws SQLException{
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("insert into Class (classID, className, date1, startTime, stopTime) VALUES ('" + ID + "','" +
                    className + "','" + date + "','" +startTime + "','" + stopTime + "')");
            stmt.executeUpdate("insert into ClassLimit (totalLimit, avalible, classID, gymID) VALUES ('" + totalLimit + "','" + totalLimit + "','"
            + ID + "','" + gymID + "')");
       } catch (Exception e) {
           System.out.println( e.toString() );
           System.exit(0);
       }
    }
//    
    public void bookCourse(int bookingID, int memberID, int classID, String fName, int date, int classLimit, Connection conn, SQLiteConfig config) throws SQLException{
        try {
            Statement stmt = conn.createStatement();
            conn.setAutoCommit(false);
            stmt.executeUpdate("insert into Booking (bookingID, memberiD, classID, fName, date1) VALUES ('" + bookingID + "','" + memberID + "','" +
            classID + "','" + fName + "','" + date + "')");
            stmt.executeUpdate("UPDATE ClassLimit SET avalible = " + classLimit + " WHERE classiD = " + classID);
            conn.commit();
       } catch (Exception e) {
           System.out.println( e.toString() );
           System.exit(0);
       }
    }
//    
    public void cancelCourse(int classID, int gymID, Connection conn, SQLiteConfig config) throws SQLException{
        try {
            conn.setAutoCommit(false);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("delete from classLimit where classID = " + classID);
            stmt.executeUpdate("delete from Class where classID = " + classID);
            stmt.executeUpdate("delete from classLimit where gymID = " + gymID);
            conn.commit();
       } catch (Exception e) {
           System.out.println( e.toString() );
           System.exit(0);
       }
    }
//    
    public void cancelBooking(int memberID, int bookingiD, Connection conn, SQLiteConfig config) throws SQLException{
        //TODO add+1 to avilable
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("delete from Booking where bookingID = " + bookingiD);
       } catch (Exception e) {
           System.out.println( e.toString() );
           System.exit(0);
       }
    }
//    
    public void checkLogins(int memberID, String password) throws SQLException {
        if(login(memberID, password)){
            System.out.println("Login sucessfull");
        } else{
            System.out.println("Login error, try again");
        }
    }
//    
    public void checkIn(int gymID, int gymCardID, String date, Connection conn, SQLiteConfig config) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("Select * from Member");
        if(checkCheckIn(gymCardID)){
            System.out.println("Check In Approved");
        } else{
            System.out.println("Check in denied");
        }
     try {
            stmt.executeUpdate("insert into CheckIn (GymID, memberID, date2) VALUES ('" + gymID + "','" + gymCardID + "','" + date + "')");
       } catch (Exception e) {
           System.out.println( e.toString() );
           System.exit(0);
       }
    }
//    
    public void updateMember(String relation, String info, int memberID, Connection conn, SQLiteConfig config) throws SQLException{
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from Member");
            while(rs.next()){
                if(relation.equals("phoneNr")){
                    int phone = Integer.parseInt(info);
                    stmt.executeUpdate("UPDATE Member SET " + relation + " = " + phone + " WHERE memberID = " + memberID);
                } else{
                    stmt.executeUpdate("UPDATE Member SET " + relation + " = '" + info + "' WHERE memberID = " + memberID);
                }
            }
        } catch (Exception e) {
            System.out.println( e.toString() );
            System.exit(0);
        }
    }
    private boolean login(int memberID, String password) throws SQLException{
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select memberID, password from Member");
            while(rs.next()){
                if (rs.getInt("memberID") == memberID && rs.getString("password").equals(password)) {
                    return true;
                }
            }
        } catch (Exception e) {
           System.out.println( e.toString() );
           System.exit(0);
       }
        return false;
    }
    private boolean checkCheckIn(int gymCardID) throws SQLException{
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select memberID from Member");
            while(rs.next()){
                if (rs.getInt("memberID") == gymCardID) {
                    return true;
                }
            }
        } catch (Exception e) {
           System.out.println( e.toString() );
           System.exit(0);
       }
        return false;
    }
}
