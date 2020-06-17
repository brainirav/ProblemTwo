package com.neo.problemtwo.utils;

import com.neo.problemtwo.client.Customer;
import com.neo.problemtwo.client.Movie;
import com.neo.problemtwo.services.dao.DataAccessUtil;

import java.util.List;

public class Validations {

    public static boolean validateRating(int rating){
        return rating >=1 && rating <=5 ;
    }

    public static boolean validateCustomer(String customerId){
        List<Customer> allCustomers = DataAccessUtil.getAllCustomers();
        for(Customer customer : allCustomers){
            if(customer.getId().equals(customerId)){
                return true;
            }
        }
        return false;
    }

    public static boolean validateMovie(String movieId){
        List<Movie> allMovies = DataAccessUtil.getAllMovies();
        for(Movie movie : allMovies){
            if(movie.getId().equals(movieId)){
                return true;
            }
        }
        return false;
    }


}
