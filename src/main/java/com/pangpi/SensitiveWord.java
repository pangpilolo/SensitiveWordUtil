package com.pangpi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 敏感词存储
 * @author pangpi
 */
public class SensitiveWord {

    /**
     * 敏感词最长长度
     */
    private final int wordsMaxLength;

    /**
     * 敏感词列表
     */
    public Map<String, String>[] sensitiveWordsList = null;

    /**
     * 敏感词索引
     */
    public Map<String, Integer> wordIndex = new HashMap<>();

    public Lock clearLock = new ReentrantLock(true);

    public SensitiveWord(int wordsMaxLength) {
        this.wordsMaxLength = wordsMaxLength;
    }


    public void initSensitiveWordsList(List<String> words) {
        if (sensitiveWordsList == null) {
            sensitiveWordsList = new Map[wordsMaxLength];
            for (int i = 0; i < sensitiveWordsList.length; i++) {
                sensitiveWordsList[i] = new HashMap<>();
            }
            addNewWord(words);
        }
    }

    /**
     * 添加一个单词进入敏感词trie
     * @param sensitiveWord 敏感词
     */
    public void addNewWord(String sensitiveWord) {
        if (SensitiveWordUtils.isNotBlank(sensitiveWord) && sensitiveWord.length() <= wordsMaxLength) {
            sensitiveWordsList[sensitiveWord.length()].put(sensitiveWord.toLowerCase(), "");
            Integer index = wordIndex.get(sensitiveWord.substring(0, 1));
            if (index == null) {
                index = 0;
            }
            int x = (int) Math.pow(2, sensitiveWord.length());
            index = (index | x);
            wordIndex.put(sensitiveWord.substring(0, 1), index);
        } else {
            throw new RuntimeException("敏感词长度过长或为空");
        }
    }

    public void addNewWord(List<String> badWords) {
        for (String word : badWords) {
            addNewWord(word);
        }
    }

}
