package com.zhixue.infomon.service;

import com.zhixue.infomon.util.SoftType;
import org.springframework.web.multipart.MultipartFile;

public interface FileUpService {

    String softAdd(MultipartFile reportFile, SoftType softType);

}
