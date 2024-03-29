package com.pangpi;

import java.util.List;

/**
 * 敏感词检测对象工厂
 * @author pangpi
 */
public class SensitiveWordFactory {


    /**
     * 获取默认配置的敏感词检测对象
     * @param sensitiveWords 敏感词列表
     * @return 敏感词检测对象
     */
    public static SensitiveWordsCheck getDefaultSensitiveWordsCheck(List<String> sensitiveWords) {
        return new SensitiveWordsCheck(sensitiveWords, 20);
    }

    /**
     * 获取自定义配置的敏感词检测对象
     * @param sensitiveWords 敏感词列表
     * @param wordsMaxLength 敏感词字符串最长长度
     * @return 敏感词检测对象
     */
    public static SensitiveWordsCheck getSensitiveWordsCheck(List<String> sensitiveWords,int wordsMaxLength) {
        return new SensitiveWordsCheck(sensitiveWords, wordsMaxLength);
    }

}
