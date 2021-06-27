package com.itheima.controller;


import com.itheima.domain.StudentInfoDO;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@RestController
public class QuickController {

    @RequestMapping("/hello")
    public ModelAndView quick(){
        return new ModelAndView  ("index");
    }
    public final static String IMG_PATH_PREFIX = "static/assets/images";

    public static File getImgDirFile(String imgPath){
        // 构建上传文件的存放 "文件夹" 路径s
        String fileDirPath = new String("src/main/resources/" + imgPath);
        File fileDir = new File(fileDirPath);
        if(!fileDir.exists()){
            // 递归生成文件夹
            fileDir.mkdirs();
        }
        return fileDir;
    }

    //@RequestMapping("/hello2")
    @RequestMapping(value = "/hello2",method = RequestMethod.POST)
    @ResponseBody
    public String quick2(MultipartFile file,StudentInfoDO user) throws IOException {
        System.err.println("访问了11" + user);
        String filePath = "D://0331//";
        Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HHmmssSSS");
        String dateString=sdf.format(date);
        if (file!=null) {
            String contentType = file.getContentType();
            //获取文件名
            String filename = file.getOriginalFilename();
            String prefix = filename.substring(filename.lastIndexOf("."));//文件后缀
            // 存放上传图片的文件夹
            File fileDir = getImgDirFile(IMG_PATH_PREFIX);
            // 输出文件夹绝对路径  -- 这里的绝对路径是相当于当前项目的路径而不是“容器”路径
            String absolutePath = fileDir.getAbsolutePath();
            String seq=File.separator;
            //新文件名
            String s = dateString;
            String filenas = s + prefix;
            //上传到项目内 static/assets/images
            File dest = new File(absolutePath +seq+filenas);
            file.transferTo(dest);
            System.out.println("文件上传成功!");
        }
//        if (!file.isEmpty()) {
//            FileOutputStream out = null;
//            try {
//                File targetFile = new File(filePath);
//                String filename = file.getOriginalFilename();
//                if (!targetFile.exists()) {
//                    targetFile.mkdirs();
//                }
//                out = new FileOutputStream(filePath + filename);
//                out.write(file.getBytes());
//                out.flush();
//            } catch (Exception e) {
//                e.printStackTrace();
//                System.out.println("文件上传失败!");
//                return "文件上传失败!";
//            } finally {
//                out.close();
//            }
//
//        }


        return "手持用户";
    }

}
