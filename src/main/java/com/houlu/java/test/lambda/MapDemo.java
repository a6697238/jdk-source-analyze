package com.houlu.java.test.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Lu Hou
 * @date 2017/10/22
 * @time 下午11:08
 */
public class MapDemo {

    public static void main(String[] args) {

        List<String> wordList = Arrays.asList("Aaa","BSD","cSA");

        List<String> output = wordList.stream().
                map(str->str.toUpperCase()).
                collect(Collectors.toList());
        output.stream().forEach(str ->{System.out.println(str);});
    }
}
