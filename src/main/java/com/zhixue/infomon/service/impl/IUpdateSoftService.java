package com.zhixue.infomon.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.zhixue.infomon.entity.UpdateSoft;
import com.zhixue.infomon.repository.UpdateSoftRepository;
import com.zhixue.infomon.service.UpdateSoftService;
import com.zhixue.infomon.util.DateUtil;
import com.zhixue.infomon.util.FileUtil;
import com.zhixue.infomon.util.SoftType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@Service
public class IUpdateSoftService implements UpdateSoftService {

    @Autowired
    UpdateSoftRepository updateSoftRepository;

    @Override
    public UpdateSoft add(UpdateSoft updateSoft) {

        return updateSoftRepository.save(updateSoft);
    }

    @Override
    public String findALL(String page, String size, SoftType softType) {
        //如果为null默认为0
        Integer rpage = page == null || page.equals("") ? 0 : Integer.parseInt(page);
        //如果为null默认为10
        Integer rsize = size == null || size.equals("") ? 10 : Integer.parseInt(size);
        Pageable pageable = PageRequest.of(rpage, rsize, Sort.Direction.DESC, "id");

        Page<UpdateSoft> updateSofts = updateSoftRepository.findAllBySoftType(softType.toString(),pageable);
        List<Map<String, Object>> lists = new ArrayList<>();
        List<Map<String, String>> list = new ArrayList<>();
        Map<String, Object> maps = new HashMap<>();
        for (UpdateSoft upd : updateSofts) {
            Map<String, String> m = new HashMap<>();
            m.put("softId", upd.getSoftId());
            m.put("softName", upd.getSoftName());
            m.put("softSize", FileUtil.getSize(Integer.parseInt(upd.getSoftSize())));
            m.put("softMd5", upd.getSoftMd5());
            m.put("updateTime", DateUtil.date2TimeStamp(upd.getUpdateTime(), null));
            list.add(m);
        }
        maps.put("size", updateSofts.getTotalElements());
        maps.put("data", JSONObject.toJSONString(list));
        lists.add(maps);
        return JSONObject.toJSONString(lists);
    }

    @Override
    public String findLastOne(SoftType softType) {

        UpdateSoft upd = updateSoftRepository.findLastOne(softType.toString());
        if (upd == null) {
            return null;
        }
        Map<String, String> m = new HashMap<>();
        m.put("softId", upd.getSoftId());
        m.put("softName", upd.getSoftName());
        m.put("softSize", FileUtil.getSize(Integer.parseInt(upd.getSoftSize())));
        m.put("softMd5", upd.getSoftMd5());
        m.put("downloadUrl",upd.getDownloadUrl());
        m.put("updateTime", DateUtil.date2TimeStamp(upd.getUpdateTime(), null));
        return JSONObject.toJSONString(m);
    }
}
