package com.example.backendnh.util;

public class StrUtil {
    public StrUtil() {
    }

    public static boolean isEmpty(String s) {
        return s == null || s.trim().length() == 0;
    }

    public static long str2Long(String s) {
        if (isEmpty(s)) {
            return 0L;
        } else {
            try {
                return Long.parseLong(s);
            } catch (NumberFormatException var2) {
                return 0L;
            }
        }
    }

    public static String path2Filename(String s) {
        if (isEmpty(s)) {
            return "";
        } else {
            int len = s.length();
            int start = s.lastIndexOf(System.getProperty("file.separator")) + 1;
            int end = s.lastIndexOf(".");
            return len != 0 && -1 != start && -1 != end && start < end ? s.substring(start, end) : s.trim();
        }
    }
}
