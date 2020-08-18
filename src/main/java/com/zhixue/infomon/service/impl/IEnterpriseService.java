package com.zhixue.infomon.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.zhixue.infomon.entity.Enterprise;
import com.zhixue.infomon.repository.EnterpriseRepository;
import com.zhixue.infomon.service.EnterpriseService;
import com.zhixue.infomon.util.DateUtil;
import com.zhixue.infomon.util.EnterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.unit.DataUnit;

import java.util.*;

@Transactional
@Service
public class IEnterpriseService implements EnterpriseService {

    @Autowired
    EnterpriseRepository enterpriseRepository;

    @Override
    public String addEnter(Enterprise enterprise) {
        String enterId=enterprise.getEnterId();
        enterprise.setLoginTime(new Date());
        //根据企业id查询
        Enterprise reEnter=enterpriseRepository.findAllByEnterId(enterId);
        //为空
        if (reEnter==null){
            enterprise.setInfoId(UUID.randomUUID().toString().replace("-",""));
            enterprise.setState("0");
            enterpriseRepository.save(enterprise);
            return "ADD";
        }
        else {
        //不为空
            //更新信息
            enterprise.setId(reEnter.getId());
            enterpriseRepository.saveAndFlush(enterprise);
        }
        return "Ok";
    }

    @Override
    public String findByEnter(String enterName,String enterState ,String page, String size) {
        enterName = enterName == null || enterName.equals("") ? "" : enterName;
        enterState = enterState == null || enterState.equals("") ? "" : enterState;
        //如果为null默认为0
        Integer rpage = page == null || page.equals("") ? 0 : Integer.parseInt(page);
        //如果为null默认为10
        Integer rsize = size == null || size.equals("") ? 10 : Integer.parseInt(size);
        Pageable pageable = PageRequest.of(rpage, rsize, Sort.Direction.DESC, "id");

        Page<Enterprise> all=enterpriseRepository.findAllByEnterNameContainingAndEnterStateContaining(enterName,enterState,pageable);

        List<Map<String, Object>> lists = new ArrayList<>();
        List<Map<String, String>> list = new ArrayList<>();
        Map<String, Object> maps = new HashMap<>();
        for (Enterprise enterprise : all) {
            Map<String, String> map = new HashMap<>();
            map.put("infoId",enterprise.getInfoId());
            map.put("enterId",enterprise.getEnterId());
            map.put("enterName",enterprise.getEnterName());
            map.put("enterState",enterprise.getEnterState());
            map.put("hostNum",EnterUtil.decrypt(enterprise.getHostNum()));
            map.put("defaultUser",enterprise.getDefaultUser());
            map.put("linkNum",enterprise.getLinkNum());
            map.put("state",enterprise.getState());
            map.put("enterAuth",enterprise.getEnterAuth());
            map.put("innetIp",enterprise.getInnetIp());
            map.put("ipv4",enterprise.getIpv4());
            String signTime=EnterUtil.decrypt(enterprise.getSignTime());
            String day=EnterUtil.decrypt(enterprise.getEnterDay());
            if (signTime != null && day != null) {
                String endTime = DateUtil.addOneday(signTime, Integer.parseInt(day));
                map.put("endTime", endTime);
            }
            map.put("enterDay",day);
            map.put("signTime",DateUtil.timeStamp2Date(signTime,""));
            map.put("loginTime",DateUtil.date2TimeStamp(enterprise.getLoginTime(),""));
            list.add(map);
        }
        maps.put("size", all.getTotalElements());
        maps.put("data", JSONObject.toJSONString(list));
        lists.add(maps);
        return JSONObject.toJSONString(lists);

    }
}
