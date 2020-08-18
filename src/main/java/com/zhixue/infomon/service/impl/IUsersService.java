package com.zhixue.infomon.service.impl;

import com.zhixue.infomon.entity.Users;
import com.zhixue.infomon.repository.UsersRepository;
import com.zhixue.infomon.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

@Transactional
@Service
public class IUsersService implements UsersService {

    @Autowired
    UsersRepository usersRepository;

    @Override
    public Users login(String no, String pow) {

        String newPow=DigestUtils.md5DigestAsHex(pow.getBytes());

        Users users=usersRepository.findAllByNoAndPow(no, newPow);
        if (users==null){
            return null;
        }else {
            return users;
        }
    }

    @Override
    public Users findByNo(String no) {
        return usersRepository.findAllByNo(no);
    }
}
