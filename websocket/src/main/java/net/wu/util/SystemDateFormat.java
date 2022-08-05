package net.wu.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author Wu Zihan
 * @Date 2022-08-04 18:04
 */
public class SystemDateFormat {
    public static String SdfForTimeString(Date date){
        return format(date);
    }

    public static String format(Date date){
        if (date != null){
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
            return simpleDateFormat.format(date);
        }
        return "";
    }
}
