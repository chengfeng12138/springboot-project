package com.chengfeng.study.myspringbootproject.controller.picture;

import cn.hutool.core.io.file.FileReader;
import com.chengfeng.study.myspringbootproject.common.ResponseResult;
import com.chengfeng.study.myspringbootproject.utils.ResultUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * PictureController class
 *
 * @author chengfeng
 * @date 2021/9/4 /0004 0:36
 */
@RestController
public class PictureController {

    private static final Logger log = Logger.getLogger(PictureController.class.getName());

    /**
     * @description 文件图片上传
     * @author chengfeng
     * @date 2021/7/18 /0018 16:31
     **/
    @RequestMapping("/upload")
    @ResponseBody
    public ResponseResult upload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResultUtil.error(500, "上传失败,请选择文件!");
        }
        String filename = file.getOriginalFilename();
        String filePath = "C:\\Users\\15623\\Desktop\\uploadFile\\";
        File dest = new File(filePath + filename);
        try {
            boolean newFile = dest.createNewFile();
            file.transferTo(dest);
            log.log(Level.INFO, "文件上传成功!");
            return ResultUtil.success();
        } catch (IOException e) {
            e.printStackTrace();
            log.log(Level.WARNING, e.toString(), e);
            return ResultUtil.error(501, "上传失败!");
        }
    }

    @RequestMapping(value = "/readPicture/{image_name}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> readPicture(@PathVariable("image_name") String image_name) {


        FileReader fileReader = new FileReader("test.properties");
        byte[] imageContent = fileReader.readBytes();

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<>(imageContent, headers, HttpStatus.OK);
    }

}
