package com.neo.problemtwo.services.dao;

import com.neo.problemtwo.client.Customer;
import com.neo.problemtwo.client.Movie;
import com.neo.problemtwo.client.Rating;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DataAccessUtil {

    public static int executeQuery(String query){
        Connection con = null;
        Statement stmt = null;
        int result = 0;

        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
            con = DriverManager.getConnection( "jdbc:hsqldb:hsql://localhost/testdb", "SA", "");
            stmt = con.createStatement();
            result = stmt.executeUpdate(query);
            con.commit();
        }  catch (Exception e) {
            e.printStackTrace(System.out);
        }

        System.out.println("Query Executed successfully");
        return result;
    }

    public static List<Rating> getAllRatings(){
        List<Rating> ratings = new ArrayList<>();

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

                ratings.add(new Rating(
                        result.getString("movieid"),
                        result.getString("customerId"),
                        result.getString("rating")
                ));

            }

        }  catch (Exception e) {
            e.printStackTrace(System.out);
        }

        return ratings;
    }

    public static List<Movie> getAllMovies(){
        List<Movie> movies = new ArrayList<>();

        Connection con = null;
        Statement stmt = null;
        ResultSet result = null ;

        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
            con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/testdb", "SA", "");
            stmt = con.createStatement();

            result = stmt.executeQuery(
                    "SELECT * FROM t_movie");

            while(result.next()){
                movies.add(new Movie(
                        result.getString("id"),
                        result.getString("name")
                ));

            }

        }  catch (Exception e) {
            e.printStackTrace(System.out);
        }

        return movies;
    }


    public static List<Customer> getAllCustomers(){
        List<Customer> customers = new ArrayList<>();

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
                customers.add(new Customer(
                        result.getString("id"),
                        result.getString("firstname"),
                        result.getString("lastname")
                ));

            }

        }  catch (Exception e) {
            e.printStackTrace(System.out);
        }

        return customers;
    }



}
