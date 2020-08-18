package com.zhixue.infomon.service;

import com.zhixue.infomon.entity.Enterprise;

public interface EnterpriseService {

    String addEnter(Enterprise enterprise);

    String findByEnter(String enterName,String enterState ,String page,String size);

}
