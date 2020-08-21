package com.zhixue.infomon.controller;


import com.zhixue.infomon.service.FileUpService;
import com.zhixue.infomon.service.UpdateSoftService;
import com.zhixue.infomon.util.SoftType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/clientUpdate")
public class ClientUpdateController {

    private Map<String, Object> map;

    @Autowired
    FileUpService fileUpService;

    @Autowired
    UpdateSoftService updateSoftService;

    /**
     * @desc: 客户端更新
     * @author: sen
     * @date: 2020/8/13 0013 11:01
     **/
    @RequestMapping(value = "/file", method = RequestMethod.POST)
    public synchronized Map<String, Object> singleFileUpload(@RequestParam("file") MultipartFile file,String type) {
        map = new HashMap<>();
        String name="";
        String fileName=file.getOriginalFilename();
        int lastFile = fileName.lastIndexOf(".");
        String upApkType = fileName.substring(lastFile + 1, fileName.length()).toLowerCase();
        System.out.println(upApkType);
        if (type.equals("BACK")){
            if (!upApkType.toLowerCase().equals("war")){
                map.put("code", "1");
                return map;
            }
            name = fileUpService.softAdd(file, SoftType.BACK);
        }else if (type.equals("FRONT")){
            if (!upApkType.toLowerCase().equals("zip")){
                map.put("code", "1");
                return map;
            }
            name = fileUpService.softAdd(file, SoftType.FRONT);
        }else {
            if (!upApkType.toLowerCase().equals("apk")){
                map.put("code", "1");
                return map;
            }
            name = fileUpService.softAdd(file, SoftType.APK);
        }
        if (name == null) {
            map.put("code", "1");
            return map;
        }
        map.put("code", "0");
        return map;
    }

    /**
     * @desc: 查询更新记录
     * @author: sen
     * @date: 2020/8/13 0013 14:26
     **/
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public synchronized Map<String, Object> select(String page, String size,String type) {
        map = new HashMap<>();
        String data;
        if (type.equals("BACK")){
            data = updateSoftService.findALL(page, size,SoftType.BACK);
        }else if (type.equals("FRONT")){
            data = updateSoftService.findALL(page, size,SoftType.FRONT);
        }else {
            data = updateSoftService.findALL(page, size,SoftType.APK);
        }
        if (data == null) {
            map.put("code", "1");
            return map;
        }
        map.put("code", "0");
        map.put("data", data);
        return map;
    }

    /**
     * @desc: 查询最新一条
     * @author: sen
     * @date: 2020/8/13 0013 14:26
     **/
    @RequestMapping(value = "/newUpdate", method = RequestMethod.POST)
    public synchronized Map<String, Object> selectNew(String type) {
        map = new HashMap<>();
        String data;
        if (type.equals("BACK")){
            data = updateSoftService.findLastOne(SoftType.BACK);
        }else if (type.equals("FRONT")){
            data = updateSoftService.findLastOne(SoftType.FRONT);
        }else {
            data = updateSoftService.findLastOne(SoftType.APK);
        }
        if (data == null) {
            map.put("code", "1");
            return map;
        }
        map.put("code", "0");
        map.put("data", data);
        return map;
    }

}
