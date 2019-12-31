package com.graykey.xcore.common.utils;

import com.graykey.xcore.common.base.controller.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ResourceBundle;

/**

/**
 * 读写工具类
 * @author zc
 * @date 2019/7/23 9:16
 */
public class LiteracyUtil {

    private static final Logger logger = LoggerFactory.getLogger(BaseController.class);


    //读取配置文件
    public static String getProperty(String key) {
        String str = "";
        try {
            str = new String(ResourceBundle.getBundle("config").getString(key).getBytes("ISO-8859-1"),"utf-8");
        } catch (UnsupportedEncodingException e) {
            logger.error("读取配置信息", e);
        }
        return str;
    }

    /**
     * 清空文件内容
     * @param fileName
     * @return
     * @author xuezb
     * @Date 2018年8月6日
     */
    public static void clearInfoForFile(String fileName) {
        File file =new File(fileName);
        try {
            if(!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter =new FileWriter(file);
            fileWriter.write("");
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 追加文件内容
     * @param file
     * @param conent
     * @return
     * @author xuezb
     * @Date 2018年8月6日
     */
    public static void fileAdditionalContent(String file, String conent) {
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(file, true),"UTF-8"));
            out.write(conent);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(out != null){
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
