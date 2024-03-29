package com.pangpi;

/**
 * 敏感词检测中需要用的的统一的工具方法
 *
 * @author pangpi
 */
public class SensitiveWordUtils {


    public static boolean isBlank(String str) {
        if (str == null) {
            return true;
        } else {
            return str.trim().length() == 0;
        }
    }

    public static boolean isNotBlank(String str) {
        return isBlank(str);
    }

}
