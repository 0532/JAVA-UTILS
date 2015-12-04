package com.haier.neusoft.o2o.common.util;

/**
 * Created with IntelliJ IDEA.
 * User: xzl
 * Date: 13-6-17
 * Time: 下午3:06
 * To change this template use File | Settings | File Templates.
 */
public class OnlyNumUtil {
    private static final long ONE_STEP = 100;
    private static long lastTime = System.currentTimeMillis();
    private static short count = 0;

    /**
     * 时间+计数器的主键
     *
     * @return
     */
    public static synchronized String getPk() {
        try{
            if (count == ONE_STEP) {
                boolean done = false;
                while (!done) {
                    long now = System.currentTimeMillis();
                    if (now == lastTime) {
                        continue;
                    } else {
                        lastTime = now;
                        count = 0;
                        done = true;
                    }
                }
            }
        }catch(Exception e){
            return null;
        }
        String formatCount=String.format("%02d",count++);
        String result = lastTime + "" + (formatCount);
        return result;
    }

}
