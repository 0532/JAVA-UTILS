package com.haier.neusoft.o2o.common.util;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by will on 2015/5/11.
 */
public class StringUtil {
    public static boolean equalsAny(String source,String... targets){
        for(String target : targets){
            if(StringUtils.equals(source,target)){
                return true;
            }
        }
        return false;
    }

    /***
     * 把a,b,c字符串转换成'a','b','c'形式
     * @param str
     * @return
     */
    public static String parseSqlString(String str){
        String[] arr=str.split(",");
        StringBuffer result=new StringBuffer();
        if(arr!=null){
            for(String s:arr){
                result.append("'").append(s).append("',");
            }
            return result.substring(0,result.length()-1);
        }
        return result.toString();
    }
    /**
     * 产生随机字符串
     * */
    private static Random randGen = null;
    private static char[] numbersAndLetters = null;
    public static final String randomString(int length) {
        if (length < 1) {
            return null;
        }
        if (randGen == null) {
            randGen = new Random();
            numbersAndLetters = ("0123456789abcdefghijklmnopqrstuvwxyz" +
                    "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();
        }
        char [] randBuffer = new char[length];
        for (int i=0; i<randBuffer.length; i++) {
            randBuffer[i] = numbersAndLetters[randGen.nextInt(71)];
        }
        return new String(randBuffer);
    }
    public static final String randomMailString() {
        Random randGen = new Random();
        char [] num=("0123456789").toCharArray();
        char [] lowercase=("abcdefghijklmnopqrstuvwxyz").toCharArray();
        char [] uppercase=("ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();
        char [] specialcase=(",.-_*&^%$#@!+'").toCharArray();
        char [] randBuffer = new char[8];
        randBuffer[0] = uppercase[randGen.nextInt(25)];
        randBuffer[1] = lowercase[randGen.nextInt(25)];
        randBuffer[2] = specialcase[randGen.nextInt(14)];
        randBuffer[3] = num[randGen.nextInt(9)];
        randBuffer[4] = specialcase[randGen.nextInt(14)];
        randBuffer[5] = num[randGen.nextInt(9)];
        randBuffer[6] = uppercase[randGen.nextInt(25)];
        randBuffer[7] = lowercase[randGen.nextInt(25)];
        return new String(randBuffer);
    }

    /***
     * 校验是否邮箱地址
     * @param email
     * @return
     */
    public static boolean isEmail(String email){
        String str="^([a-zA-Z0-9]*[-_.]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    /***
     * 数组去重
     * @param a
     * @return
     */
    public static String[] arrayUnique(String[] a) {
        Set<String> set = new HashSet<String>();
        set.addAll(Arrays.asList(a));
        return set.toArray(new String[0]);
    }
}
