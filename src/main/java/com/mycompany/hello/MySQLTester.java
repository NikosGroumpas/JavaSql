
package com.mycompany.hello;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;


public class MySQLTester {
    
    public static void main(String[] args) {
       
        try {
            
            int bookid;
            int pages;
            String col;
            
            Scanner console = new Scanner (System.in);
            
            /*System.out.println("Enter book id: ");
            bookid = console.nextInt();
            System.out.println("Enter number of pages: ");
            pages = console.nextInt();  */     
            System.out.println("Choose column to add: ");
            col = console.nextLine(); 
             
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/testnew?useUnicode=true&serverTimezone=UTC",
                    "admin",
                    "24895");
            
            Statement stmt = conn.createStatement();
            
            String sql = "ALTER TABLE book add " + col + " varchar(50)";
            stmt.executeUpdate(sql);
            ResultSet rs = stmt.executeQuery("SELECT * FROM book"); /* WHERE id=" + taskId*/
            
         
            //System.out.println("total pages");
            while(rs.next()) {
                System.out.println( rs.getInt("id")  +  " | "  + rs.getString("title")  +  " | " + rs.getDate("year_p") + " | " + rs.getInt("pages") + " | " + rs.getInt("author_id") + " | " + rs.getString(col));
            }
            
            System.out.println("ALL OK");
        }
        catch(ClassNotFoundException cnfe) {
            System.out.println("MySQL Driver not found. Please install");
        }
        catch(SQLException sqle) {
            System.out.println(sqle.getMessage());
        }
        catch(InputMismatchException ime) {
            System.out.println("Input should be an integer");
        } 
        finally {
            System.out.println("THANKS FOR USING OUR SYSTEM");
        }

        
    }
    
}
