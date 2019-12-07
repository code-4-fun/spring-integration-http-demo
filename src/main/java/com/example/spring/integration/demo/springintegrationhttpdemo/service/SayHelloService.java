package com.example.spring.integration.demo.springintegrationhttpdemo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SayHelloService {

    private final Logger logger = LoggerFactory.getLogger(SayHelloService.class);

    public String greet(String name) {
        logger.info("received request to process Message - " + name);

        double value = Math.random() * 4;
        if(Math.round(value) % 2 == 0) {
            throw new NullPointerException("Because I can!!!");
        }

        return "Hello " + name + " [ " + value +" ]";
    }

}
