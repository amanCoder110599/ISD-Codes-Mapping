package com.example.demo;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class No_such_resource_found_Exception extends RuntimeException {
    public No_such_resource_found_Exception(String msg) {
        super(msg);
    }
}
