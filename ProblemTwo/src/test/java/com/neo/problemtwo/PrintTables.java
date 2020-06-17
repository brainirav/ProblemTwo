package com.neo.problemtwo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class PrintTables {

    public static void main(String[] args) {
        System.out.println("Customers\n");
        printCustomers();
        System.out.println("\n_______________________________\nMovies\n");
        printMovies();
        System.out.println("\n_______________________________\nRatings\n");
        printRatings();
    }

    public static void printCustomers(){
        Connection con = null;
        Statement stmt = null;
        ResultSet result = null ;

        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
            con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/testdb", "SA", "");
            stmt = con.createStatement();

            result = stmt.executeQuery(
                    "SELECT * FROM T_CUSTOMER");

            while(result.next()){
                System.out.println(result.getInt("id")+" | "+
                        result.getString("firstname")+" | "+
                        result.getString("lastname"));
            }

        }  catch (Exception e) {
            e.printStackTrace(System.out);
        }
        System.out.println("Query Executed");
    }

    public static void printMovies(){
        Connection con = null;
        Statement stmt = null;
        ResultSet result = null ;

        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
            con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/testdb", "SA", "");
            stmt = con.createStatement();

            result = stmt.executeQuery(
                    "SELECT * FROM T_MOVIE");

            while(result.next()){
                System.out.println(result.getInt("id")+" | "+
                        result.getString("name"));
            }

        }  catch (Exception e) {
            e.printStackTrace(System.out);
        }
        System.out.println("Query Executed");
    }

    public static void printRatings(){
        Connection con = null;
        Statement stmt = null;
        ResultSet result = null ;

        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
            con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/testdb", "SA", "");
            stmt = con.createStatement();

            result = stmt.executeQuery(
                    "SELECT * FROM t_rating");

            while(result.next()){
                System.out.println(result.getInt("rating")+" | "+
                        result.getString("movieid")+" | "+
                        result.getString("customerid"));
            }

        }  catch (Exception e) {
            e.printStackTrace(System.out);
        }
        System.out.println("Query Executed");
    }

}