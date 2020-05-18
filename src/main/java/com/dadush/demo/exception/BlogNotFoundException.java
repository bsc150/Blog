package com.dadush.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class BlogNotFoundException extends RuntimeException {
    public BlogNotFoundException(String msg) {
        super(msg);
        System.out.println("exception called");
    }
}