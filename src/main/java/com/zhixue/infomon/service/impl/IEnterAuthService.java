package com.zhixue.infomon.service.impl;

import com.zhixue.infomon.entity.EnterAuth;
import com.zhixue.infomon.entity.Enterprise;
import com.zhixue.infomon.repository.EnterAuthRepository;
import com.zhixue.infomon.repository.EnterpriseRepository;
import com.zhixue.infomon.service.EnterAuthService;
import com.zhixue.infomon.service.EnterpriseService;
import com.zhixue.infomon.util.EnterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class IEnterAuthService implements EnterAuthService {

    @Autowired
    EnterAuthRepository enterAuthRepository;

    @Override
    public String addEnterAuth(String userName,String day,String hostNum) {

        String auth="{no:'"+userName+"',day:'"+day+"',hostNum:'"+hostNum+"'}";

        String enterAuth=EnterUtil.encryption(auth);
        return enterAuth;
    }





}
