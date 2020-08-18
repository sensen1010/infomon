package com.zhixue.infomon.service;

import com.zhixue.infomon.entity.Users;

public interface UsersService {

    Users login(String no,String pow);

    Users findByNo(String no);
}
