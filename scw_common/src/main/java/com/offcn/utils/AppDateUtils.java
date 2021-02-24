package com.offcn.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wzy
 * @version 0.0.3
 * @description AppDateUtils
 * @since 2020/12/3 15:15
 * 日期处理工具类
 */
public class AppDateUtils {

    /**
     * 获取当前日期
     * @return 格式化后的日期字符串
     */
    public static String getFormatTime(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String format = simpleDateFormat.format(new Date());
        return format;
    }

    /**
     *
     * @param pattern 格式化类型
     * @return
     */
    public static String getFormatTime(String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        String string = format.format(new Date());
        return string;
    }

    /**
     *
     * @param pattern 格式化类型
     * @param date 日期
     * @return
     */
    public static String getFormatTime(String pattern, Date date) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        String string = format.format(date);
        return string;
    }


}
