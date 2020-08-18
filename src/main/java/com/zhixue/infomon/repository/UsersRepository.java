package com.zhixue.infomon.repository;

import com.zhixue.infomon.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users,Integer> {

    Users findAllByNoAndPow(String no,String pow);

    Users findAllByNo(String no);



}
