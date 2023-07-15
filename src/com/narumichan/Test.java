package com.narumichan;

import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        StringWhiz x = StringWhiz.of("123xxxxxxxxxxxx123xxxxxxx123xxx123xxxxx123xxx123123");
        
        Arrays.stream(x.slice(5)).forEach(System.out::println);
    }
    
}
