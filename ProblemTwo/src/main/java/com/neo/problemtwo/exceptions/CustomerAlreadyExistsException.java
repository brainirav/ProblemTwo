package com.neo.problemtwo.exceptions;

public class CustomerAlreadyExistsException extends Exception{
    public String toString(){
        return "Customer with the same id already exists.";
    }
}
