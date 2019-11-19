package com.woniu.fitness.service;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 功能描述:<br>
 * 〈〉
 *
 * @author wugd
 * @create 2019/11/12
 * @since 1.0.0
 */
@Service
public class UploadService {

    @Autowired
    FastFileStorageClient storageClient;
    //设置允许上传的图片的类型
    //private static final List<String> suffixes = Arrays.asList("image/png", "image/jpeg","image/gif");

    public String upload(MultipartFile file) {
        //1.验证文件
        //验证文件的类型
       /* String contentType = file.getContentType();
        if (!suffixes.contains(contentType)) {
            return null;
        }*/
        //验证文件的内容
        try {
            /*BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
            if (bufferedImage == null) {
                return null;
            }*/

            String fileName = file.getOriginalFilename();
            // 2、将图片上传到FastDFS
            // 2.1、获取文件后缀名
            String extension =  fileName.substring(fileName.lastIndexOf(".")+1);
            // 2.2、上传
            StorePath storePath = this.storageClient.uploadFile(
                    file.getInputStream(), file.getSize(), extension, null);
            // 2.3、返回完整路径
            String path = "http://172.81.253.212/" + storePath.getFullPath();
            System.out.println(path +"============================================================");
            return path;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


}