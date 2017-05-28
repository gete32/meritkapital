package main.java.exchanger.utils;

import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * Created by root on 28.05.17.
 */
public class DateUtils {

    private final static Pattern pattern = Pattern.compile("([0-9]{4})-([0-9]{2})-([0-9]{2})");
    private final static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static Date parse(final String source){
        if (StringUtils.isEmpty(source)) {
            return null;
        }
        try {
            return pattern.matcher(source).matches() ? dateFormat.parse(source) : null;
        } catch (ParseException e) {
            //We will check date in a validator
        }
        return null;
    }

    public static String format(final Date date){
        if (date == null) return "";

        return dateFormat.format(date);
    }
}
