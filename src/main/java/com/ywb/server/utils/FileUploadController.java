package com.ywb.server.utils;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import com.ywb.server.mapper.OrgnizationMapper;
import com.ywb.server.beans.Orgnization;

@Controller
public class FileUploadController {
	
	private final static String UPLOAD_DIR = "/opt/ywbdata/photo";
	private final static String UPLOAD_ORIG_DIR = "/opt/ywbdata/origphoto";
	//private final static String UPLOAD_DIR = "D:\\tmp\\ywbdata\\photo";
	//private final static String UPLOAD_ORIG_DIR = "D:\\tmp\\ywbdata\\origphoto";

	@Autowired
	private OrgnizationMapper orgnizationMapper;

    @RequestMapping(value="/uploadhead", method=RequestMethod.GET)
    public @ResponseBody String provideUploadInfo() {
        return "You can upload a file by posting to this same URL.";
    }

    @RequestMapping(value="/uploadhead", method=RequestMethod.POST)
    public @ResponseBody String handleFileUpload(@RequestParam("id") int id, @RequestParam("file") MultipartFile file){
    	//String name = "photo-" + Integer.toString(id);
    	
        String nowTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());//当前时间  
        Random r = new Random();  
        int x = r.nextInt(999999); //生成一个随机数  
        String name = id +"-"+x+"-"+nowTime+".jpg";//得到文件的新名字  
        
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File(UPLOAD_ORIG_DIR, name )));
                stream.write(bytes);
                stream.close();
                
                uploadHead(new File(UPLOAD_ORIG_DIR, name), UPLOAD_DIR, name);
		Orgnization org = new Orgnization();
		org.setOrgid(id);
		org.setHeadpath(name);
   		orgnizationMapper.insert(org);;
                return "You successfully uploaded " + name + "!";
            } catch (Exception e) {
                return "You failed to upload " + name + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload " + name + " because the file was empty.";
        } 
        
        
    }
    
    private String uploadHead(File file, String path, String name) throws Exception {  
        
//        String nowTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());//当前时间  
//        Random r = new Random();  
//        int x = r.nextInt(999999); //生成一个随机数  
//        String newFileName = requestId+"-"+x+"-"+nowTime+".jpg";//得到文件的新名字  
          
        FileInputStream  is = new FileInputStream(file);  
        BufferedImage sourceImg = javax.imageio.ImageIO.read(is);  
           
        int width = sourceImg.getWidth();                       //原文件宽度  
        int height = sourceImg.getHeight();                     //原文件高度  
           
        if(width>200||height>200){                                //等比缩放为200*200的图片，如果宽和高有一个大于200的就进行截取  
            DecimalFormat df=new DecimalFormat("0.000");          
              
            double bili = 0.000;                                //计算等比  
                  
            if(width > height){                                  //根据像素大的一方进行等比缩放  
                bili = Double.parseDouble(df.format(width / 200.0));  
            }else{  
                bili = Double.parseDouble(df.format(height / 200.0));  
            }  
            width = (int) (width / bili);  
            height = (int) (height / bili);  
         }  
          
        BufferedImage src = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);           //根据计算好的宽高新建画布  
          
        src.getGraphics().drawImage(sourceImg.getScaledInstance(width, height,Image.SCALE_SMOOTH), 0, 0, null);   
  
        ImageIO.write(src, "JPEG", new File(path, name));                                     //输出图像  
        is.close();  
        return name ;  
    }  

	@RequestMapping(value = "/gethead/{id}", method = RequestMethod.GET)
	public void getHead(@PathVariable("id") Integer id, HttpServletResponse response) {
		List<String> names = orgnizationMapper.queryByOrgid(id);
		String name = names.get(names.size()-1);
		System.out.println("##get head name:"+name);
		try {
			InputStream is = new FileInputStream(new File(UPLOAD_DIR,name));
			IOUtils.copy(is, response.getOutputStream());
			response.flushBuffer();
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
	}

}
