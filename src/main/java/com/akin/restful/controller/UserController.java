package com.akin.restful.controller;

import com.akin.restful.utils.ResponseUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "/restful")
public class UserController {

    @RequestMapping("/test")
    public ResponseEntity getTest() {
        String str = "Hello, World! This is the test~~~";
        return ResponseUtil.success(str);
    }
}
