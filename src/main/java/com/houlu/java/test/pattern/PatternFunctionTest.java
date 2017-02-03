package com.houlu.java.test.pattern;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 类名称: PatternFunctionTest <br>
 * 类描述: <br>
 *
 * @author lu.hou
 * @version 1.0.0
 * @since 16/11/2 下午12:23
 */
public class PatternFunctionTest {

//    "^172.*": 开头一定要有"wangsheng"的字符串才能被匹配;
//     .*3g$: 结尾一定要由"isJoshWang"的字符串来结尾;
//    "^abc$": 就是要求以abc开头和以abc结尾的字符串，实际上是只有abc匹配。
//     "notice": 匹配包含notice的字符串。

    public static void main(String[] args) {
        String reg1 = "^172.*";
        String reg2 = ".*3g$";

        Pattern pattern1 = Pattern.compile(reg1);
        Matcher matcher1 = pattern1.matcher("10.172.10");
        System.out.println(matcher1.matches());

        Pattern pattern2 = Pattern.compile(reg2);
        Matcher matcher2 = pattern2.matcher("bass.3g");
        System.out.println(matcher2.matches());
    }
}
