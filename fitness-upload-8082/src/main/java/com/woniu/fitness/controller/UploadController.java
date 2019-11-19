package com.woniu.fitness.controller;

import com.woniu.fitness.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 功能描述:<br>
 * 〈〉
 *
 * @author wugd
 * @create 2019/11/12
 * @since 1.0.0
 */
@RestController
@RequestMapping("upfile")
public class UploadController {

    @Autowired
    UploadService uploadService ;

    @PostMapping("/uploadImg")
    public String upload(@RequestParam("file") MultipartFile file) {
        System.out.println(file);
        System.out.println("==================================================================");
        return uploadService.upload(file);
    }
    @RequestMapping("/text")
    public String test(){
        System.out.println("===============");
        return "success";
    }
}