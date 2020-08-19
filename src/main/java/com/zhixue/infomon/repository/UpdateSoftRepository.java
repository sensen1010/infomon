package com.zhixue.infomon.repository;

import com.zhixue.infomon.entity.UpdateSoft;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface UpdateSoftRepository extends JpaRepository<UpdateSoft,Integer> {

    List<UpdateSoft> findAllBySoftMd5(String softMd5);

    @Query(value = "select * from fron_soft where soft_type=?1 order by id DESC limit 1", nativeQuery = true)
    UpdateSoft findLastOne(String softType);

    Page<UpdateSoft> findAllBySoftType(String softType, Pageable pageable);

}
