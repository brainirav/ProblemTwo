package com.neo.problemtwo.client;

public class Rating {
    private String movieId;
    private String rating;
    private String customerId;

    public Rating() {}
    public Rating(String movieId, String customerId, String rating){
        this.movieId = movieId;
        this.customerId = customerId;
        this.rating = rating;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}
