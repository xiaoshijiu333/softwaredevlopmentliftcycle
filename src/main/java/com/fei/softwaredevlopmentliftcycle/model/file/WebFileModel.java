package com.fei.softwaredevlopmentliftcycle.model.file;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author: xiaoshijiu
 * @Date: 2020/4/22
 * @Description: 返回前端文件模型
 */
@Setter
@Getter
public class WebFileModel {
    private String url;
    private String name;

    public WebFileModel(String fileUrl, String fileName) {
        this.url = fileUrl;
        this.name = fileName;
    }

    public WebFileModel() {
    }
}
