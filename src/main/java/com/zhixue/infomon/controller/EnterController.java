package com.zhixue.infomon.controller;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/asd")
public class EnterController {



    private Map<String, Object> map;

    @RequestMapping(value = "/asd", method = RequestMethod.POST)
    private Map<String, Object> addEnter(String userName, String pow) {
        map = new HashMap<>();
        System.out.println(userName+pow);
        String enter="100";
        if (enter == null) {
            map.put("code", "1");
            return map;
        }
        if (enter.equals("100")) {
            map.put("code", "2");
            return map;
        }
        if (enter.equals("101")) {
            map.put("code", "3");
            return map;
        }
        map.put("code", "0");
        map.put("data", enter);
        return map;
    }



}
