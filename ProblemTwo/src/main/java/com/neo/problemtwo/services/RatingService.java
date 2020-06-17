package com.neo.problemtwo.services;

import com.neo.problemtwo.client.Movie;
import com.neo.problemtwo.client.Rating;
import com.neo.problemtwo.exceptions.InvalidCustomerException;
import com.neo.problemtwo.exceptions.InvalidMovieException;
import com.neo.problemtwo.exceptions.InvalidRatingException;
import com.neo.problemtwo.services.dao.DataAccessUtil;
import com.neo.problemtwo.utils.CommonUtils;
import com.neo.problemtwo.utils.Validations;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RatingService {

    public void addRating(String customerId, String movieId, String rating) throws
            Exception{

        if(!Validations.validateCustomer(customerId)){
            throw new InvalidCustomerException();
        }else if(!Validations.validateMovie(movieId)){
            throw new InvalidMovieException();
        }else if(!Validations.validateRating(Integer.parseInt(rating))){
            throw new InvalidRatingException();
        }

        List<Rating> ratings = DataAccessUtil.getAllRatings();
        for(Rating r : ratings){
            if(r.getCustomerId().equals(customerId)){
                if(r.getMovieId().equals(movieId)){
                    String updateQuery = "UPDATE T_RATING " +
                            "SET rating = '" + rating + "' " +
                            "WHERE customerid = '" + customerId + "' and movieid = '" + movieId + "' ;";
                    DataAccessUtil.executeQuery(updateQuery);
                    return;
                }
            }
        }

        String insertQuery = "INSERT INTO T_RATING " +
        "VALUES ('" + rating + "', '" + movieId + "', '" + customerId + "');";

        DataAccessUtil.executeQuery(insertQuery);

    }

    public Movie getHighestRatedMovie(){
        List<Rating> ratings = DataAccessUtil.getAllRatings();

        HashMap<String, Float> movieAverageRating = new HashMap<>();

        for(Rating rating : ratings){
            movieAverageRating.put(rating.getMovieId(), 0.0f);
        }

        for(String movieId : movieAverageRating.keySet()){
            List<Integer> ratingsForMovie = new ArrayList<>();
            for(Rating rating : ratings){
                if(rating.getMovieId().equals(movieId)){
                    ratingsForMovie.add(Integer.parseInt(rating.getRating()));
                }
            }
            movieAverageRating.put(movieId, CommonUtils.findAverage(ratingsForMovie));
        }

        Map.Entry<String, Float> maxRatedMovie = null;
        for(Map.Entry<String, Float> entry : movieAverageRating.entrySet()){
            if(maxRatedMovie == null || entry.getValue().compareTo(maxRatedMovie.getValue()) > 0){
                maxRatedMovie = entry;
            }
        }

        List<Movie> allMovies = DataAccessUtil.getAllMovies();
        for(Movie movie : allMovies){
            if(movie.getId().equals(maxRatedMovie.getKey())){
                return movie;
            }
        }

        return null;
    }


}
