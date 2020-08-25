package utils;

public class StringUtils {

    // 判断字符串为空， 为空返回true
    public static Boolean isEmpty(String value) {
        if (value == null || "".equals(value) || value.length() <= 0) {
            return true;
        } else {
            return false;
        }
    }

    // 判断字符串不为空，不为空返回true
    public static Boolean isNotEmpty(String value) {
        if (!isEmpty(value)) {
            return true;
        } else {
            return false;
        }
    }

}
