package com.akin.restful.service;

import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Service;

@Service
public class HelloService {

    public int getTestCount() {
        return 666;
    }

    @Test
    public void testFunction() {

    }
}
