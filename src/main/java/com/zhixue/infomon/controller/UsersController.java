package com.zhixue.infomon.controller;

import com.zhixue.infomon.entity.Enterprise;
import com.zhixue.infomon.entity.Users;
import com.zhixue.infomon.service.UsersService;
import com.zhixue.infomon.token.annotation.PassToken;
import com.zhixue.infomon.token.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/user")
public class UsersController {

    private Map<String, Object> map;

    @Autowired
    UsersService usersService;

    @Autowired
    TokenService tokenService;

    @PassToken
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    private Map<String, Object> login(String no,String pow) {
        map = new HashMap<>();
        Users re=usersService.login(no, pow);
        if (re==null){
            map.put("code","1");
            return map;
        }else {
            map.put("code","0");
            map.put("token",tokenService.getToken(re));
            return map;
        }
    }

}
