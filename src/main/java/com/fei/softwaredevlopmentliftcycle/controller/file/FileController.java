package com.fei.softwaredevlopmentliftcycle.controller.file;

import com.fei.common.data.ApiResult;
import com.fei.softwaredevlopmentliftcycle.model.file.WebFileModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

/**
 * @Author: xiaoshijiu
 * @Date: 2020/4/22
 * @Description: 文件控制器
 */
@RequestMapping("/file")
@RestController
public class FileController {

    @PostMapping("/upload")
    public ApiResult<WebFileModel> upload(@RequestParam("file") MultipartFile file)
            throws IOException {
        // 判定的是size==0（文件内容为空，空word！！）
        if (file.isEmpty()) {
            return ApiResult.fail("失败，文件不存在！！");
        }
        String fileName = file.getOriginalFilename();
        String endFilePath = "K:/softwarefiles/" + fileName;
        file.transferTo(new File(endFilePath));
        WebFileModel webFileModel = new WebFileModel(endFilePath, fileName);
        return ApiResult.ok(webFileModel);
    }

    /**
     * 下载资源
     * @param filename 文件名
     * @param path url
     * @return ResponseEntity
     */
    @GetMapping("/download")
    public ResponseEntity download(String filename, String path) throws Exception {

        // 把文件读写到程序当中
        InputStream io = new FileInputStream(path);
        byte[] aByte = new byte[(io.available())];
        io.read(aByte);

        // 创建响应头
        HttpHeaders httpHeaders = new HttpHeaders();
        filename = URLEncoder.encode(filename, "UTF-8");
        httpHeaders.add("Content-Disposition", "attachment;filename=" + filename);

        // 将byte数组、HttpHeader和HttpStatus传入ResponseEntity
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(aByte, httpHeaders,
                HttpStatus.OK);
        return responseEntity;
    }
}
