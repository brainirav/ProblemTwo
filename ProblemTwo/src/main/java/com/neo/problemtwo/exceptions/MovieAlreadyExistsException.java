package com.neo.problemtwo.exceptions;

public class MovieAlreadyExistsException extends Exception{
    public String toString(){
        return "Movie with the same id already exists.";
    }
}
