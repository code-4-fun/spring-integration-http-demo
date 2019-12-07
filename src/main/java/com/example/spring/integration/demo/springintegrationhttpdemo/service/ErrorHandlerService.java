package com.example.spring.integration.demo.springintegrationhttpdemo.service;

import org.springframework.stereotype.Service;

@Service
public class ErrorHandlerService {

    public String handleErrors(Throwable exception) {
        return "Exception ocurred during Processing " + exception.getLocalizedMessage();
    }

}
