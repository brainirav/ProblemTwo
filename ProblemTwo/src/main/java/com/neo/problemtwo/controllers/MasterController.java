
package com.neo.problemtwo.controllers;

import com.neo.problemtwo.client.Rating;
import com.neo.problemtwo.exceptions.CustomerAlreadyExistsException;
import com.neo.problemtwo.exceptions.MovieAlreadyExistsException;
import com.neo.problemtwo.services.dao.CreateTables;
import com.neo.problemtwo.client.Customer;
import com.neo.problemtwo.client.Movie;
import com.neo.problemtwo.exceptions.InvalidCustomerException;
import com.neo.problemtwo.services.CustomerService;
import com.neo.problemtwo.services.MovieService;
import com.neo.problemtwo.services.RatingService;
import com.neo.problemtwo.services.dao.DataAccessUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rest")
public class MasterController {

    @Autowired
    private RatingService ratingService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private MovieService movieService;


    @RequestMapping("/setup/tables")
    public void setupTables() throws Exception {
        CreateTables.setupTables();
    }

    @RequestMapping("/customer/add/{customerId}/firstname/{firstName}/lastname/{lastName}")
    public void addCustomer(@PathVariable String customerId,
                            @PathVariable String firstName,
                            @PathVariable String lastName) throws CustomerAlreadyExistsException {
        customerService.addCustomer(customerId, firstName, lastName);
    }

    @RequestMapping("/movie/add/{movieId}/moviename/{movieName}")
    public void addMovie(@PathVariable String movieId,
                            @PathVariable String movieName) throws MovieAlreadyExistsException {
        movieService.addMovie(movieId, movieName);
    }


    @RequestMapping("/customer/{customerId}/rate/{rating}/{movieId}")
    public void rateMovie(@PathVariable String customerId,
                          @PathVariable String rating,
                          @PathVariable String movieId) throws Exception {
        ratingService.addRating(customerId, movieId, rating);
    }

    @RequestMapping("/movie/highestrated")
    public Movie getHighestRatedMovie() {
        return ratingService.getHighestRatedMovie();
    }

    @RequestMapping("/customer/highestratings")
    public Customer getCustomerWithHighestRatings() throws InvalidCustomerException {
        return customerService.getCustomerWithHighestRatings();
    }

    @RequestMapping("/movies")
    public List<Movie> getMovies() {
        return DataAccessUtil.getAllMovies();
    }

    @RequestMapping("/customers")
    public List<Customer> getCustomers() {
        return DataAccessUtil.getAllCustomers();
    }

    @RequestMapping("/ratings")
    public List<Rating> getRatings() {
        return DataAccessUtil.getAllRatings();
    }


}
