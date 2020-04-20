package com.example.demo.controller;


import com.example.demo.util.LockHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/hi")
    public String hi() {
        String uuid = UUID.randomUUID().toString();
        if(LockHelper.lock(uuid)){
            System.out.println("get the lock,success");
        }else {
            System.out.println("get the lock,fail");
        }

        boolean result = LockHelper.unlock(uuid);
        System.out.println("unlock status:" + (result ? "success" : "fail"));
        return "hi";
    }
}
