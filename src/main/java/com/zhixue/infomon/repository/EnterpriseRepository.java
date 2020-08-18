package com.zhixue.infomon.repository;

import com.zhixue.infomon.entity.Enterprise;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface EnterpriseRepository extends JpaRepository<Enterprise,Integer> {

    Enterprise findAllByEnterIdAndEnterNameAndIpv4(String enterId,String enterName,String ipv4);

    Enterprise findAllByEnterId(String enterId);

    Page<Enterprise> findAllByEnterNameContainingAndEnterStateContaining(String enterName, String enterState, Pageable pageable);


}
