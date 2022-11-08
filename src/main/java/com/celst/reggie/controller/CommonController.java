package com.celst.reggie.controller;

import com.celst.reggie.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/common")
public class CommonController {//文件上传下载

    @Value("${reggie.path}")
    private String basePath;

    @PostMapping("/upload")
    public R<String> upload(MultipartFile file){
        log.info(file.toString());
        //获取原来的文件名（可能重名）
        String originalFilename = file.getOriginalFilename();

        //截取格式名(.jpg)
        String substring = originalFilename.substring(originalFilename.lastIndexOf("."));

        //使用UUID生成文件名
        String fileName= UUID.randomUUID().toString()+substring;

        //创建目录对象
        File dir = new File(basePath);

        //判断basePath是否存在
        if(!dir.exists()){
            //创建目录
            dir.mkdirs();
        }
        try{
            file.transferTo(new File(basePath+fileName));
        }catch (IOException e){
            e.printStackTrace();
        }

        return R.success(fileName);

    }

    @GetMapping("/download")
    public void download(String name, HttpServletResponse response){

        try {
            //输入流读取文件内容
            FileInputStream fileInputStream = new FileInputStream(new File(basePath + name));

            //输出流写回浏览器
            ServletOutputStream outputStream = response.getOutputStream();

            response.setContentType("image/jpeg");
            int len = 0;
            byte[] bytes= new byte[1024];
            while ((len = fileInputStream.read(bytes)) != -1){
                outputStream.write(bytes,0,len);
                outputStream.flush();
            }
            outputStream.close();
            fileInputStream.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}
