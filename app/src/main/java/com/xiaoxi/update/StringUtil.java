package com.xiaoxi.update;


public class StringUtil {

    public static boolean isEmpty(String str) {
        return (str == null) || (str.length() == 0) || str.equals("null");
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }


}
