package com.mr.reggie.controller;

import com.mr.reggie.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.UUID;

/**
 * 上传下载
 */
@RestController
@Slf4j
@RequestMapping("/common")
public class CommonController  {

    @PostMapping("/upload")
    public R<String> upload(MultipartFile file){
        log.info("file:{}", file.toString());
        //原始文件名
        String originalFilename = file.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        //使用UUID 防止文件名重复 造成文件覆盖
        String fileName = UUID.randomUUID().toString() + suffix;
        //file是一个临时文件 需要转存
        try {
            file.transferTo(new File("D:\\Work\\Java\\IdeaProjects\\temp\\" + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return R.success(fileName);
    }
    @GetMapping("/download")
    public void download(String name, HttpServletResponse response){

        try {
            //定义输入流
            FileInputStream fileInputStream = new FileInputStream(new File("D:\\Work\\Java\\IdeaProjects\\temp\\" + name));
            //定义输出流
            ServletOutputStream outputStream = response.getOutputStream();
            response.setContentType("image/jpeg");
            byte[] bytes = new byte[1024];
            int len = 0;
            while ((len = fileInputStream.read(bytes)) != -1){
                outputStream.write(bytes,0, len);
                outputStream.flush();
            }
            //关闭资源
            fileInputStream.close();
            outputStream.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
