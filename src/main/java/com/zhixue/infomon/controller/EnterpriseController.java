package com.zhixue.infomon.controller;

import com.zhixue.infomon.entity.Enterprise;
import com.zhixue.infomon.service.EnterAuthService;
import com.zhixue.infomon.service.EnterpriseService;
import com.zhixue.infomon.token.annotation.PassToken;
import com.zhixue.infomon.token.annotation.UserLoginToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/enter")
public class EnterpriseController {

    private Map<String, Object> map;

    @Autowired
    EnterpriseService enterpriseService;

    @Autowired
    EnterAuthService enterAuthService;

    @PassToken
    @RequestMapping(value = "/enter", method = RequestMethod.POST)
    private Map<String, Object> addEnter(Enterprise enter) {
        map = new HashMap<>();
        String re=enterpriseService.addEnter(enter);
        map.put("code",re);
        return map;
    }

    @UserLoginToken
    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    private Map<String, Object> authEnter(String enterUser,String day,String hostNum) {
        map = new HashMap<>();
        String re=enterAuthService.addEnterAuth(enterUser,day,hostNum);
        map.put("code","0");
        map.put("data",re);
        return map;
    }
    @UserLoginToken
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    private Map<String, Object> selectEnter(String enterName,String enterState,String page,String size) {
        map = new HashMap<>();
        String re=enterpriseService.findByEnter(enterName, enterState, page, size);
        map.put("code","0");
        map.put("data",re);
        return map;
    }

}
