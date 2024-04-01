package com.pangpi;

import java.util.ArrayList;
import java.util.List;

public class TestCode {

    public static void main(String[] args) {
        List<String> sensitiveWords = new ArrayList<>();
        sensitiveWords.add("我草");
        sensitiveWords.add("我草你马");
        sensitiveWords.add("吊毛");
        sensitiveWords.add("某某党");
        SensitiveWordsCheck sensitiveWordsCheck = SensitiveWordFactory.getDefaultSensitiveWordsCheck(sensitiveWords);
        String text = "我是一个好人，我草，你是不是人啊？你是某某党的？";
        if (sensitiveWordsCheck.hasBanWords(text)) {
            System.out.println("当前文本涉及敏感词");
        }

        List<String> words = sensitiveWordsCheck.searchBanWords(text);
        words.forEach(item -> System.out.println("涉及的敏感词:" + item));


        SensitiveWord sensitiveWord = sensitiveWordsCheck.getSensitiveWord();
        sensitiveWord.addNewWord("我操");
        List<String> newSensitiveWords = new ArrayList<>();
        newSensitiveWords.add("你妹的");
        newSensitiveWords.add("你他妈");
        sensitiveWord.addNewWord(newSensitiveWords);


        String replaceText = sensitiveWordsCheck.replaceBanWords(text, "[违规词]");
        System.out.println("经过替换后的文本" + replaceText);
    }

}
