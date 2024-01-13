package com.example.employeebackend.exception;

public class EmployeeNotFoundException extends Exception{
    public EmployeeNotFoundException(String msg) {
        super(msg);
    }
}