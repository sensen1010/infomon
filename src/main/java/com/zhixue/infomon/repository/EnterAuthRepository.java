package com.zhixue.infomon.repository;

import com.zhixue.infomon.entity.EnterAuth;
import com.zhixue.infomon.entity.Enterprise;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface EnterAuthRepository extends JpaRepository<EnterAuth,Integer> {


    //根据企业id、企业表id查询
    EnterAuth findAllByEnterIdAndInfoId(String enterId,String infoId);


}
