package com.pangpi;

/**
 * 敏感词检测中需要用的的统一的工具方法
 *
 * @author pangpi
 */
public class SensitiveWordUtils {


    public static boolean isBlank(String str) {
        return str == null || str.trim().isEmpty();
    }

    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

}
