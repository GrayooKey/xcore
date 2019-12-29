package com.graykey.xcore.common.utils.helper;

import java.security.SecureRandom;

/**
 * 随机字符串生成类
 *
 * @author xuezb
 * @date 2019-12-26
 */
public class SecretKeyUtil {

  /**
   * 产生密钥信息 采用安全的生成随机数方法（SecureRandom）包含数字和小写字母
   *
   * @param length 随机数长度
   * @return java.lang.String
   */
    public static String createSecretKey(int length) {
        final String base = "qwertyuiopasdfghjklzxcvbnm0123456789";
        SecureRandom secureRandom = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int number = secureRandom.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 生成指定长度的随机数 全数字
     *
     * @param length 长度
     * @return java.lang.String
     */
    public static String createRandow(int length) {
        final String num = "0123456789";
        SecureRandom secureRandom = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int number = secureRandom.nextInt(num.length());
            sb.append(num.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 将字符串进行部分加密(隐藏)  适用于身份证号码加密等
     *
     * @param code 要隐藏显示的字符串
     * @param head 前面保留的位数
     * @param tail 后面保留的位数
     * @return 处理后的字符串
     */
    public static String encryptCode(String code,int head,int tail){
        // 中间要隐藏的位数
        int body = code.length() - head - tail;
        // 将字符串拆成三部分，并指定每部分长度的正则表达式
        String regexVar = "(\\w{%d})(\\w{%d})(\\w{%d})";
        String regex = String.format(regexVar, head, body, tail);
        // 获取字符串中间要隐藏的部分，并替换成对应长度的*
        String bodyPart = code.replaceAll(regex, "$2");
        String bodyEncrypt = bodyPart.replaceAll("\\w", "*");
        // 处理生成字符串replacement = "$1*****$3" 中间是对应长度的*号
        String replacement = String.format("$1%s$3", bodyEncrypt);
        return code.replaceAll(regex, replacement);
    }

}
