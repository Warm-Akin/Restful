package com.akin.restful.controller;

import com.akin.restful.model.User;
import com.akin.restful.service.HelloService;
import com.akin.restful.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/ahn")
public class HelloController {

    @Autowired
    HelloService helloService;

    @RequestMapping("/hello")
    public ResponseEntity getHello() {
        String str = "Hello, World!";
        return ResponseUtil.success(str);
    }

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getTestData (@RequestBody User user) {
        String userName = user.getUserName();
        String password = user.getPassword();
        System.out.println(userName + "===" + password);
        String str = "success post test";
        return ResponseUtil.success(str);
    }

//    @PostMapping(value = "/test")
    @GetMapping(value = "/test")
    public ResponseEntity getTestMapping () {
        System.out.println("Test~~~~~~");
        int count = helloService.getTestCount();
        List<User> userList = new ArrayList<>();
        User user1 = new User();
        user1.setUserName("Test1");
        user1.setPassword("password1");
        User user2 = new User();
        user2.setUserName("Test2");
        user2.setPassword("password2");
        userList.add(user1);
        userList.add(user2);
        return ResponseUtil.success(userList);
    }
}
