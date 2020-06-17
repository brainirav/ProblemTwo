package com.neo.problemtwo.services;

import com.neo.problemtwo.client.Customer;
import com.neo.problemtwo.client.Movie;
import com.neo.problemtwo.client.Rating;
import com.neo.problemtwo.exceptions.CustomerAlreadyExistsException;
import com.neo.problemtwo.exceptions.InvalidCustomerException;
import com.neo.problemtwo.services.dao.DataAccessUtil;
import com.neo.problemtwo.utils.CommonUtils;
import com.neo.problemtwo.utils.Validations;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomerService {

    public void addCustomer(String customerId, String firstName, String lastName) throws CustomerAlreadyExistsException {

        List<Customer> customers = DataAccessUtil.getAllCustomers();
        for(Customer customer : customers){
            if(customer.getId().equals(customerId)){
                throw new CustomerAlreadyExistsException();
            }
        }

        String insertQuery = "INSERT INTO T_CUSTOMER " +
        "VALUES ('" + customerId + "', '" + firstName + "', '" + lastName + "');";

        DataAccessUtil.executeQuery(insertQuery);

    }


    /*
    * This method will return the customer who rated highest number of movies,
    * his average rating for all movies
    * and also overall average rating of all customers.
    */
    public Customer getCustomerWithHighestRatings() throws InvalidCustomerException {
        Customer customer = null;
        List<Rating> ratings = DataAccessUtil.getAllRatings();
        Map<String, Integer> customerRatingCountMap = new HashMap<>();
        for(Rating rating : ratings){
            if(customerRatingCountMap.keySet().contains(rating.getCustomerId())){
                customerRatingCountMap.put(rating.getCustomerId(),
                        customerRatingCountMap.get(rating.getCustomerId()) + 1);
            }else{
                customerRatingCountMap.put(rating.getCustomerId(), 0);
            }
        }

        Map.Entry<String, Integer> maxEntry = null;
        for(Map.Entry<String, Integer> entry : customerRatingCountMap.entrySet()){
            if(maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0){
                maxEntry = entry;
            }
        }

        customer = getCustomer(maxEntry.getKey());

        List<Integer> numbersForCustomerAverageRating = new ArrayList<>();
        List<Integer> numbersForAverageRating = new ArrayList<>();
        for(Rating rating : ratings){
            if(rating.getCustomerId().equals(customer.getId())){
                numbersForCustomerAverageRating.add(Integer.parseInt(rating.getRating()));
            }
            numbersForAverageRating.add(Integer.parseInt(rating.getRating()));
        }

        customer.setCustomerAverageRating(CommonUtils.findAverage(numbersForCustomerAverageRating));
        customer.setAverageRating(CommonUtils.findAverage(numbersForAverageRating));

        return customer;
    }




    public Customer getCustomer(String customerId) throws InvalidCustomerException{

        if(!Validations.validateCustomer(customerId)){
            throw new InvalidCustomerException();
        }

        List<Customer> customers = DataAccessUtil.getAllCustomers();

        for(Customer customer : customers){
            if(customer.getId().equals(customerId)){
                return customer;
            }
        }

        return null;
    }


}
