package com.haier.neusoft.o2o.common.util;

/**
 * 自定义进制转换.
 *
 * @author will Tong
 */
public class CustomNumericUtil {
    public static final char[] DIGITS_36 = {
            '0' , '1' , '2' , '3' , '4' , '5' ,
            '6' , '7' , '8' , '9' , 'A' , 'B' ,
            'C' , 'D' , 'E' , 'F' , 'G' , 'H' ,
            'I' , 'J' , 'K' , 'L' , 'M' , 'N' ,
            'O' , 'P' , 'Q' , 'R' , 'S' , 'T' ,
            'U' , 'V' , 'W' , 'X' , 'Y' , 'Z'
    };

    /**
     * 将十进制的数字转换为指定进制的字符串。
     * @param i 十进制的数字。
     * @return 转换后的字符串。
     */
    public static String toCustomNumericString(int i,char[] digits) {
        int system=digits.length;
        long num = 0;
        if (i < 0) {
            num = ((long)2 * 0x7fffffff) + i + 2;
        } else {
            num = i;
        }
        char[] buf = new char[32];
        int charPos = 32;
        while ((num / system) > 0) {
            buf[--charPos] = digits[(int)(num % system)];
            num /= system;
        }
        buf[--charPos] = digits[(int)(num % system)];
        return new String(buf, charPos, (32 - charPos));
    }

    /**
     * 将其它进制的数字（字符串形式）转换为十进制的数字。
     * @param s 其它进制的数字（字符串形式）
     * @return 转换后的数字。
     */
    public static int toCustomNumeric(String s,char[] digits) {
        int system=digits.length;
        char[] buf = new char[s.length()];
        s.getChars(0, s.length(), buf, 0);
        long num = 0;
        for (int i=0;i<buf.length;i++) {
            for (int j=0;j<digits.length;j++) {
                if (digits[j] == buf[i]) {
                    num += j * Math.pow(system, buf.length - i -1);
                    break;
                }
            }
        }
        return (int)num;
    }
}
