package com.neo.problemtwo.services.dao;

import com.neo.problemtwo.services.CustomerService;
import com.neo.problemtwo.services.MovieService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class CreateTables{

    public static void setupTables() throws Exception {

        Connection con = null;
        Statement stmt = null;
        int result = 0;

        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
            con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/testdb", "SA", "");
            stmt = con.createStatement();

            stmt.executeUpdate("CREATE TABLE T_RATING ( " +
                    "rating INT NOT NULL, " +
                    "movieId VARCHAR(20) NOT NULL," +
                    "customerId VARCHAR(20) NOT NULL " +
                    "); ");

            stmt.executeUpdate("CREATE TABLE T_MOVIE ( " +
                    "id INT NOT NULL, " +
                    "name VARCHAR(50) NOT NULL," +
                    "); ");

            stmt.executeUpdate("CREATE TABLE T_CUSTOMER ( " +
                    "id INT NOT NULL, " +
                    "firstName VARCHAR(20) NOT NULL," +
                    "lastName VARCHAR(20) NOT NULL " +
                    "); ");

        }  catch (Exception e) {
            e.printStackTrace(System.out);
        }

        System.out.println("Table created successfully");

        new CustomerService().addCustomer("21", "Jack", "Sparrow");
        new CustomerService().addCustomer("22", "Tony", "Stark");
        new CustomerService().addCustomer("23", "Steve", "Rogers");
        new CustomerService().addCustomer("24", "Peter", "Parker");

        new MovieService().addMovie("123", "Ironman");
        new MovieService().addMovie("124", "Thor");
        new MovieService().addMovie("125", "Spiderman");
        new MovieService().addMovie("126", "Hulk");
        new MovieService().addMovie("127", "Avengers");
        new MovieService().addMovie("128", "Batman");

        System.out.println("Dummy Data added successfully.");
    }
}