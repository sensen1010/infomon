package com.zhixue.infomon.util;


import org.springframework.util.DigestUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.Arrays;

public class FileUtil {
    public static String fileToBetyArray(InputStream fis) {
        MessageDigest md = null;
        //FileInputStream fis = null;
        byte[] buffer = null;
        try {
            md = MessageDigest.getInstance("MD5");
            // fis = new FileInputStream(file);
            buffer = new byte[1024];
            int length = -1;
            while ((length = fis.read(buffer, 0, 1024)) != -1) {
                md.update(buffer, 0, length);
            }
            BigInteger bigInt = new BigInteger(1, md.digest());
            return bigInt.toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
                buffer.clone();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static String fileMd5(InputStream inputStream) {
        try {
            return String.valueOf(DigestUtils.md5Digest(inputStream));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * @desc: 判断返回文件类型
     * @author: sen
     * @date: 2020/7/22 0022 10:24
     **/

    public static String fileType(String type) {
        String[] imgName = {"jpg", "png", "jpeg"};
        String[] wordName = {"xls", "xlsx", "doc", "docx", "ppt", "pptx"};
        String[] videoName = {"mp4", "flv", "avi"};
        String[] musicName = {"mp3"};
        type = type.toLowerCase();
        if (Arrays.asList(imgName).contains(type)) return "1";
        else if (Arrays.asList(wordName).contains(type)) return "2";
        else if (Arrays.asList(videoName).contains(type)) return "3";
        else if (Arrays.asList(musicName).contains(type)) return "4";
        return null;
    }

    public static String fileTypePath(String type) {
        switch (type) {
            case "1":
                return "img";
            case "2":
                return "word";
            case "3":
                return "video";
            case "4":
                return "music";
            default:
                return null;
        }
    }

    /**
     * @desc: 返回文件大小
     * @author: sen
     * @date: 2020/8/3 0003 13:38
     **/
    public static String  getSize(int size) {
        //获取到的size为：1705230
        int GB = 1024 * 1024 * 1024;//定义GB的计算常量
        int MB = 1024 * 1024;//定义MB的计算常量
        int KB = 1024;//定义KB的计算常量
        DecimalFormat df = new DecimalFormat("0.00");//格式化小数
        String resultSize = "";
        if (size / GB >= 1) {
            //如果当前Byte的值大于等于1GB
            resultSize = df.format(size / (float) GB) + "GB   ";
        } else if (size / MB >= 1) {
            //如果当前Byte的值大于等于1MB
            resultSize = df.format(size / (float) MB) + "MB   ";
        } else if (size / KB >= 1) {
            //如果当前Byte的值大于等于1KB
            resultSize = df.format(size / (float) KB) + "KB   ";
        } else {
            resultSize = size + "B   ";
        }
        return  resultSize;
    }


}
