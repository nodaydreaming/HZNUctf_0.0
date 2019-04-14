package com.ctfplatform.demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/file")
@ResponseBody
public class UploadController {

    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public Map<String, String> uploadaaa(MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws IllegalStateException, IOException{
        //String path = request.getSession().getServletContext().getRealPath("upload");
        Map<String, String> map = new HashMap<>();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");//设置日期格式  HH:mm:ss
        String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳

        String path = request.getSession().getServletContext().getRealPath("/upload/problemResource");
//        System.out.println(path);
        File filepath = new File(path);
        if(!filepath.exists()){
            filepath.mkdirs();
        }
//        UUID uuid=UUID.randomUUID();
        String originalFilename = file.getOriginalFilename();
//        System.out.println(originalFilename);
        // String fileName = uuid.toString() + originalFilename;
//        String extendName = originalFilename.substring(originalFilename.lastIndexOf("."), originalFilename.length());
        String fileName =  getRandomFileName() + originalFilename;
//        System.out.println(fileName);
        File dir = new File(path, fileName);
        try {
            file.transferTo(dir);
            map.put("code", 0+"");
            map.put("src", "/upload/problemResource/" + fileName);
        }catch (IOException e){
            map.put("message", "上传失败！");
        }
//        System.out.println(fileName);
//        System.out.println(map);
        return map;

    }

    @RequestMapping(value = "/uploadImg",method = RequestMethod.POST)
    public Map<String, String> uploadImg(MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws IllegalStateException, IOException{
        //String path = request.getSession().getServletContext().getRealPath("upload");
        Map<String, String> map = new HashMap<>();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");//设置日期格式  HH:mm:ss
        String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳

        String path = request.getSession().getServletContext().getRealPath("/upload/adminPhoto");
        System.out.println("filePath" + path);
        File filepath = new File(path);
        if(!filepath.exists()){
            filepath.mkdirs();
        }
//        UUID uuid=UUID.randomUUID();
        String originalFilename = file.getOriginalFilename();
//        System.out.println(originalFilename);
        // String fileName = uuid.toString() + originalFilename;
//        String extendName = originalFilename.substring(originalFilename.lastIndexOf("."), originalFilename.length());
        String fileName =  getRandomFileName() + originalFilename;
//        System.out.println(fileName);
        File dir = new File(path, fileName);
        try {
            file.transferTo(dir);
            map.put("code", 0+"");
            map.put("src", "/hznuctf/upload/adminPhoto/" + fileName);
        }catch (IOException e){
            map.put("message", "上传失败！");
        }

//        System.out.println(fileName);
//        System.out.println(map);
        return map;

    }
    public static String getRandomFileName(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateStr = sdf.format(new Date());
        Random random = new Random();
        int rannum = (int) (random.nextDouble() * (999999 - 100000 + 1)) + 100000;
        return dateStr + rannum;
    }

}
