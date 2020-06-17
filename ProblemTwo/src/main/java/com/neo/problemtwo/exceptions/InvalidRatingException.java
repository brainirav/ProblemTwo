package com.neo.problemtwo.exceptions;

public class InvalidRatingException extends Exception{
    public String toString(){
        return "Invalid Rating, Rating must be between 1 and 5.";
    }
}
