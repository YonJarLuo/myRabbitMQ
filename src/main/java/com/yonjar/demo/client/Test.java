package com.yonjar.demo.client;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {

    public static void main(String[] args) {
        String test = "hsfgdfge45geedr--ggerkdlfjkjl";
        String test2 = "hsfgdf你geedr--ggerkdl#$%fjkjl";

        String rexg = "^[a-z-\\d]{1,30}$"; //数字英文a-z，支持中划线"-"
        Pattern compile = Pattern.compile(rexg);
        Matcher matcher = compile.matcher(test);
        boolean b = matcher.find();

        String rexg2 = "^[A-Za-z-\\u4E00-\\u9FA5]{1,30}$"; //英文大小写，中文，-
        Pattern compile2 = Pattern.compile(rexg2);
        Matcher matcher2 = compile2.matcher(test2);
        boolean b2 = matcher2.find();


        System.out.println(test.length());
        System.out.println(b);
        System.out.println(test2.length());
        System.out.println(b2);
    }
}
