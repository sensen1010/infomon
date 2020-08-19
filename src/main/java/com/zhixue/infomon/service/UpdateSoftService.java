package com.zhixue.infomon.service;


import com.zhixue.infomon.entity.UpdateSoft;
import com.zhixue.infomon.util.SoftType;


public interface UpdateSoftService {

    //添加版本
    UpdateSoft add(UpdateSoft updateSoft);

    //查询更新列表
    String findALL(String page, String size,SoftType softType);

    //查询最后一个
    String findLastOne(SoftType softType);

}
