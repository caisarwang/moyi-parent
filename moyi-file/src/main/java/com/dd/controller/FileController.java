package com.dd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("file")
public class FileController {

    private List<String> suffixList = Arrays.asList("image/png", "image/jpeg");

    @RequestMapping("upload")
    public String uploadFile(@RequestParam("file")MultipartFile file) throws IOException {
        BufferedImage image = ImageIO.read(file.getInputStream());
        if(image == null) {
            throw new RuntimeException("图片不能为空");
        }
        String contentType = file.getContentType();
        if(!suffixList.contains(contentType)) {
            throw new RuntimeException("请上传指定类型图片");
        }
        File newFile = new File("E:\\file\\image" + "\\" + System.currentTimeMillis() +
                file.getOriginalFilename());
        file.transferTo(newFile);
        return "上传成功";
    }
}
