package com.pangpi;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 敏感词审核
 *
 * @author pangpi
 */
public class SensitiveWordsCheck {

    private final SensitiveWord sensitiveWord;

    public SensitiveWordsCheck(List<String> sensitiveWords,int wordsMaxLength) {
        this.sensitiveWord = new SensitiveWord(wordsMaxLength);
        this.sensitiveWord.initSensitiveWordsList(sensitiveWords);
    }

    /**
     * 替换敏感词
     *
     * @param content 需要筛选的内容
     * @return 筛选替换后的内容
     */
    @Deprecated
    public String replaceBanWords(String content) {
        return replaceBanWords(content, "***");
    }

    /**
     * 替换敏感词
     *
     * @param content    需要筛选的内容
     * @param replaceStr 替换敏感词的字符
     * @return 筛选替换后的内容
     */
    @Deprecated
    public String replaceBanWords(String content, String replaceStr) {
        List<String> badWords = searchBanWords(content);
        String filterContent = content;
        for (String word : badWords) {
            filterContent = filterContent.replaceAll(word, replaceStr);
        }
        return filterContent;
    }

    /**
     * 替换敏感词
     *
     * @param content 需要筛选的内容
     * @return 筛选替换后的内容
     */
    public String replaceSensitiveWords(String content) {
        return replaceSensitiveWords(content, "***");
    }

    /**
     * 替换敏感词
     *
     * @param content    需要筛选的内容
     * @param replaceStr 替换敏感词的字符
     * @return 筛选替换后的内容
     */
    public String replaceSensitiveWords(String content, String replaceStr) {
        List<String> badWords = searchBanWords(content);
        StringBuilder regexBuilder = new StringBuilder();
        for (String word : badWords) {
            if (regexBuilder.length() > 0) {
                regexBuilder.append("|");
            }
            regexBuilder.append(Pattern.quote(word));
        }
        String regex = regexBuilder.toString();
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(content);
        return matcher.replaceAll(replaceStr);
    }

    /**
     * 检索敏感词
     *
     * @param content 需要筛选的内容
     * @return 敏感词集合
     */
    public List<String> searchBanWords(String content) {
        List<String> result = new ArrayList<>();

        for (int i = 0; i < content.length(); i++) {
            Integer index = sensitiveWord.wordIndex.get(content.substring(i, i + 1));
            int p = 0;

            while ((index != null) && (index > 0)) {
                p++;
                index = index >> 1;
                String sub;
                if ((i + p) < (content.length() - 1)) {
                    sub = content.substring(i, i + p);
                } else {
                    sub = content.substring(i);
                }

                if (((index % 2) == 1) && sensitiveWord.sensitiveWordsList[p].containsKey(sub)) {
                    result.add(sub);
                }
            }
        }
        return result;
    }


    /**
     * 判断当前内容是否有敏感词
     *
     * @param content 当前内容
     * @return 是否是正常内容 true->含有敏感词 false->正常内容
     */
    public boolean hasBanWords(String content) {
        for (int i = 0; i < content.length(); i++) {
            Integer index = sensitiveWord.wordIndex.get(content.substring(i, i + 1));
            int p = 0;

            while ((index != null) && (index > 0)) {
                p++;
                index = index >> 1;
                String sub;

                if ((i + p) < (content.length() - 1)) {
                    sub = content.substring(i, i + p);
                } else {
                    sub = content.substring(i);
                }

                if (((index % 2) == 1) && sensitiveWord.sensitiveWordsList[p].containsKey(sub)) {
                    return true;
                }
            }
        }
        return false;
    }


    public SensitiveWord getSensitiveWord() {
        return sensitiveWord;
    }
}
