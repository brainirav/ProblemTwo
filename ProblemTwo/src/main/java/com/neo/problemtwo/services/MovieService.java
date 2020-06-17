package com.neo.problemtwo.services;

import com.neo.problemtwo.client.Customer;
import com.neo.problemtwo.client.Movie;
import com.neo.problemtwo.exceptions.CustomerAlreadyExistsException;
import com.neo.problemtwo.exceptions.MovieAlreadyExistsException;
import com.neo.problemtwo.services.dao.DataAccessUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {


    public void addMovie(String movieId, String name) throws MovieAlreadyExistsException {

        List<Movie> movies = DataAccessUtil.getAllMovies();
        for(Movie movie : movies){
            if(movie.getId().equals(movieId)){
                throw new MovieAlreadyExistsException();
            }
        }

        String insertQuery = "INSERT INTO T_MOVIE " +
                "VALUES ('" + movieId + "', '" + name + "');";

        DataAccessUtil.executeQuery(insertQuery);

    }

}
