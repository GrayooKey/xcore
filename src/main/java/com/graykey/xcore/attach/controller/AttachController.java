package com.graykey.xcore.attach.controller;

import com.alibaba.fastjson.JSONObject;
import com.graykey.xcore.attach.module.Attach;
import com.graykey.xcore.attach.service.IAttachService;
import com.graykey.xcore.common.base.controller.BaseController;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * 附件信息	Controller层
 *
 * @author xuezb
 * @date 2019-12-27
 */
@Controller
@RequestMapping("/xcore/attach")
public class AttachController extends BaseController {

    @Autowired
    private IAttachService attachServiceImpl;


    /**
     * 文件上传
     *
     * @param response
     * @param file 文件
     * @param formId    实体表单id
     * @param source    数据来源
     */
    @RequestMapping(value="/attach_upload",method = RequestMethod.POST)
    public void upload(HttpServletResponse response, MultipartFile file, String formId, String source){
        JSONObject json = new JSONObject();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String timePath = sdf.format(new Date());//时间目录
            String fileUploadName = System.currentTimeMillis()+"";//保存的名称
            String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1);// 取文件格式后缀名
            String path = "/upload";    //Cache.getSystemConfig.getUploadPath();
            path = path + File.separator + timePath;
            File filePath =new File(path);
            if(!filePath.exists()&&!filePath.isDirectory())//如果文件夹不存在则创建
                filePath.mkdir();
            String fullPath = path + File.separator + fileUploadName + "."  +suffix;//全路径
            String pathUpload = "D://upload";    //Cache.getSystemConfig.getVirtualPath();
            pathUpload += "/"+ timePath +"/"+ fileUploadName + "." + suffix;
            File f =new File(fullPath);
            FileUtils.copyInputStreamToFile(file.getInputStream(), f);// 复制临时文件到指定目录下
            Attach attach = new Attach();
            attach.setFormId(formId);
            attach.setAttachName(file.getOriginalFilename());//文件名称
            attach.setAttachUploadName(fileUploadName);
            attach.setLocalPath(fullPath);
            attach.setUploadPath(pathUpload);//虚拟路径
            attach.setAttachSuffix(suffix);//后缀名
            if(!StringUtils.isEmpty(source)){
                attach.setDataSource(Integer.parseInt(source)); //来源 APP 或者 微信
            }else{
                attach.setDataSource(1); //来源
            }
            attach.setAttachSize(file.getSize());
            this.attachServiceImpl.saveOrUpdate(attach);//保存

            json.put("result", true);
            json.put("originalName", file.getOriginalFilename());//初始名
            json.put("name", fileUploadName+"."+suffix);//保持到服务器名称
            json.put("url", pathUpload);//访问的url
            json.put("size", file.getSize());//大小
            json.put("type", "."+suffix);//后缀类型 如 .jpg
            json.put("state", "SUCCESS");//
            json.put("filePath", fullPath);//返回附件文件全路径
            json.put("attachId", attach.getId());//返回附件ID

            logger.info("上传文件原名："+file.getOriginalFilename()+";服务器名称:"+ fileUploadName +"."+suffix+";时间："+new Date());
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            json.put("result", false);
        }
        this.print(json.toString());
    }

    /**
     * 文件上传 带水印
     *
     * @param response
     * @param file 文件
     */
    @RequestMapping(value="/attach_uploadWaterMark",method = RequestMethod.POST)
    public void uploadWaterMark(HttpServletResponse response, MultipartFile file){
        JSONObject json = new JSONObject();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String timePath = sdf.format(new Date());//时间目录
            String fileUploadName = System.currentTimeMillis()+"";//保存的名称
            String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1);// 取文件格式后缀名
            String path = "/upload";    //Cache.getSystemConfig.getUploadPath();
            path = path + File.separator + timePath;
            File filePath =new File(path);
            if(!filePath.exists()&&!filePath.isDirectory())//如果文件夹不存在则创建
                filePath.mkdir();
            String fullPath = path + File.separator + fileUploadName + "."  +suffix;//全路径
            String pathUpload = "D://upload";    //Cache.getSystemConfig.getVirtualPath();
            pathUpload += "/"+ timePath +"/"+ fileUploadName + "." + suffix;
            File f =new File(fullPath);
            FileUtils.copyInputStreamToFile(file.getInputStream(), f);// 复制临时文件到指定目录下
            //添加水印
            AttachController.waterMark("Gray_Key",fullPath,fullPath,"幼圆",Font.BOLD,"#FFFFFF",28,125, 60, 0.4f);
            Attach attach = new Attach();
            attach.setAttachName(file.getOriginalFilename());//文件名称
            attach.setAttachUploadName(fileUploadName);
            attach.setLocalPath(fullPath);
            attach.setUploadPath(pathUpload);//虚拟路径
            attach.setAttachSuffix(suffix);//后缀名
            attach.setDataSource(1); //来源
            attach.setAttachSize(file.getSize());
            this.attachServiceImpl.saveOrUpdate(attach);//保存
            json.put("result", true);
            logger.info("上传文件："+fileUploadName+";时间："+new Date());
        } catch (IOException e) {
            json.put("result", false);
            logger.error(e.getMessage(), e);
        }
        this.print(json.toString());
    }

    /**
     * 通过附件id获得附件对象
     *
     * @param response
     * @param attachId 附件id
     */
    @RequestMapping(value="/attach_getAttachById",method = RequestMethod.POST)
    public void getAttachById(HttpServletResponse response, String attachId){
        JSONObject json = new JSONObject();
        Attach attach = this.attachServiceImpl.getEntityById(attachId);
        if (attach != null) {
            json.put("result", true);
            json.put("attach", attach);
        } else {
            json.put("result", false);
        }
        this.print(json.toString());
    }

    /**
     * 通过附件ids获得附件集合
     *
     * @param response
     * @param ids 	多个附件id以","作为分隔符连接起来的String类型变量
     */
    @RequestMapping(value="/attach_getAttachListByIds",method = RequestMethod.POST)
    public void getAttachListByIds(HttpServletResponse response, String ids){
        JSONObject json = new JSONObject();
        List<Attach> attachList = this.attachServiceImpl.queryAttchListByIds(ids);
        if (!attachList.isEmpty()) {
            json.put("result", true);
            json.put("attachList", attachList);
        } else {
            json.put("result", false);
        }
        this.print(json.toString());
    }

    /**
     * 通过实体表单id获得附件集合
     *
     * @param response
     * @param formId 实体表单id
     */
    @RequestMapping(value="/attach_getAttachListByFormId",method = RequestMethod.POST)
    public void getAttachListByFormId(HttpServletResponse response, String formId){
        JSONObject json = new JSONObject();
        List<Attach> attachList = this.attachServiceImpl.queryAttchListByFormId(formId);
        if (!attachList.isEmpty()) {
            json.put("result", true);
            json.put("attachList", attachList);
        } else {
            json.put("result", false);
        }
        this.print(json.toString());
    }

    /**
     * 通过附件ids删除附件
     *
     * @param response
     * @param ids   多个附件id以","作为分隔符连接起来的String类型变量
     */
    @RequestMapping(value="/attach_deleteAttachByIds",method = RequestMethod.POST)
    public void deleteAttachByIds(HttpServletResponse response, String ids){
        JSONObject json = new JSONObject();
        try {
            this.attachServiceImpl.deleteAttachByIds(ids);
            json.put("result", true);
        } catch (Exception e) {
            json.put("result", false);
        }
        this.print(json.toString());
    }

    /**
     * 文件下载
     *
     * @param request
     * @param response
     * @param attachId  附件id
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("/attach_downLoad")
    public String downLoad(HttpServletRequest request,HttpServletResponse response, String attachId) throws UnsupportedEncodingException {
        Attach attach = this.attachServiceImpl.getEntityById(attachId);
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        response.addHeader("Content-Disposition", "attachment;filename=" + new String(attach.getAttachName().getBytes("utf-8"),"iso-8859-1"));
        try {
            InputStream inputStream = new FileInputStream(new File(attach.getLocalPath()));
            OutputStream os = response.getOutputStream();
            byte[] b = new byte[2048];
            int length;
            while ((length = inputStream.read(b)) > 0) {
                os.write(b, 0, length);
            }
            os.close(); // 这里主要关闭。
            inputStream.close();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 给图片添加文字水印
     *
     * @param pressText 水印文字
     * @param srcImageFile 源图像地址
     * @param destImageFile 目标图像地址
     * @param fontName 水印的字体名称
     * @param fontStyle 水印的字体样式
     * @param color 水印的字体颜色  如：#FFFFFF 白色
     * @param fontSize 水印的字体大小
     * @param x 修正值
     * @param y 修正值
     * @param alpha 透明度：alpha 必须是范围 [0.0, 1.0] 之内（包含边界值）的一个浮点数字
     */
    public final static void waterMark(String pressText,String srcImageFile, String destImageFile,String fontName,int fontStyle,String color,int fontSize,int x,int y, float alpha) {
        try {
            File img = new File(srcImageFile);
            Image src = ImageIO.read(img);
            int width = src.getWidth(null);
            int height = src.getHeight(null);
            BufferedImage image = new BufferedImage(width, height,
                    BufferedImage.TYPE_INT_RGB);
            Graphics2D g = image.createGraphics();
            // 开文字抗锯齿 去文字毛刺
            g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g.drawImage(src, 0, 0, width, height, null);
            // 设置颜色
            g.setColor(Color.decode(color));
            // 设置 Font
            g.setFont(new Font(fontName,fontStyle,fontSize));
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,alpha));
            // 第一参数->设置的内容，后面两个参数->文字在图片上的坐标位置(x,y) .
            g.drawString(pressText, x, y);
            g.dispose();
            ImageIO.write((BufferedImage) image, "JPEG", new File(destImageFile));// 输出到文件流
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

}