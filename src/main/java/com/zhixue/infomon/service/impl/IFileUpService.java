package com.zhixue.infomon.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.zhixue.infomon.entity.UpdateSoft;
import com.zhixue.infomon.repository.UpdateSoftRepository;
import com.zhixue.infomon.service.UpdateSoftService;
import com.zhixue.infomon.service.FileUpService;
import com.zhixue.infomon.util.FileUtil;
import com.zhixue.infomon.util.SoftType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
@Transactional
public class IFileUpService implements FileUpService {


//    private String UPLOAD_FOLDER = "E:\\imgfile\\file";
//    private String BACKPATH = "\\back\\";
//    private String FRONTPATH = "\\front\\";
//    private String APKPATH = "\\apk\\";

    private String UPLOAD_FOLDER = System.getProperty("catalina.home");
    private String BACKPATH = "/webapps/ile/back/";
    private String FRONTPATH = "/webapps/file/front/";
    private String APKPATH = "/webapps/file/apk/";


    @Autowired
    UpdateSoftRepository updateSoftRepository;

    @Autowired
    UpdateSoftService backSoftService;

    Map<String, Object> myMap;

    @Override
    public String softAdd(MultipartFile reportFile,SoftType softType) {

        //上传文件的名称
        String upApkName = reportFile.getOriginalFilename();
        //截取末尾类型
        int lastFile = upApkName.lastIndexOf(".");
        String upApkType = upApkName.substring(lastFile + 1, upApkName.length()).toLowerCase();
        List<Map<String, String>> list = new ArrayList<>();
        Map<String, String> m = new HashMap<>();
        String fileMd5 = null;
        try {
            fileMd5 = FileUtil.fileToBetyArray(reportFile.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            //  System.out.println(apkMd5);
            System.out.println(softType.name());
            //查询该apk是否存在，若存在，则添加记录、不上传文件
            List<UpdateSoft> updateSofts = updateSoftRepository.findAllBySoftMd5(fileMd5);
                if (updateSofts.size() < 1) {
                    //文件名
                    String fileName = fileMd5 + "." + upApkType;
                    //设置文件路径，
                    System.out.println("linux系统获取到的路径"+UPLOAD_FOLDER);
                   // int lastURL = UPLOAD_FOLDER.lastIndexOf("\\");
                    String upFileUrl="";
                    System.out.println("上传文件");
                    //判断上传文件类型
                    if (softType.equals(SoftType.BACK)){
                        upFileUrl= UPLOAD_FOLDER + BACKPATH;
                    }else if (softType.equals(SoftType.FRONT)){
                        upFileUrl= UPLOAD_FOLDER + FRONTPATH;
                    }else if (softType.equals(SoftType.APK)){
                        upFileUrl= UPLOAD_FOLDER + APKPATH;
                    }else {
                        return null;
                    }
                    String filePath = upFileUrl;
                    File targetFile = new File(filePath);
                    targetFile.setWritable(true, false);
                    if (!targetFile.exists()) {

                        targetFile.mkdirs();
                    }
                    File oldFile = new File(filePath + fileName);
                    reportFile.transferTo(oldFile);
                    //写入信息
                    UpdateSoft backSoft = new UpdateSoft.Builder(softType.toString())
                            .softMd5(fileMd5)
                            .softName(upApkName)
                            .softSize(reportFile.getSize() + "")
                            .downloadUrl(fileName).build();
                    backSoftService.add(backSoft);
                } else {
                    //写入信息
                    UpdateSoft backSoft = new UpdateSoft.Builder(softType.toString())
                            .softMd5(updateSofts.get(0).getSoftMd5())
                            .softName(updateSofts.get(0).getSoftName())
                            .softSize(updateSofts.get(0).getSoftSize())
                            .downloadUrl(updateSofts.get(0).getDownloadUrl()).build();
                    backSoftService.add(backSoft);
                }
            m.put("name", reportFile.getOriginalFilename());
            m.put("md5", fileMd5);
            list.add(m);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        return JSONObject.toJSONString(list);
    }

}
