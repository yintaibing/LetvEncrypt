package com.ytb.letvencrypt.util;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class Utils {
    public static int string2Int(String s) {
        if (isEmpty(s)) {
            return 0;
        }

        try {
            return Integer.parseInt(s);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static long string2Long(String s) {
        if (isEmpty(s)) {
            return 0L;
        }

        try {
            return Long.parseLong(s);
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        }
    }

    public static boolean string2Boolean(String s) {
        if (isEmpty(s)) {
            return false;
        }

        try {
            return Boolean.parseBoolean(s);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isEmpty(String s) {
        return s == null || "".equals(s.trim());
    }

    public static Map<String, Object> parseQueryParameters(URI uri) {
        Map<String, Object> map = new HashMap<>();

        String query = uri.getQuery();
        if (isEmpty(query)) {
            return map;
        }

        String[] pairs = query.split("&");
        for (String p : pairs) {
            int eq = p.indexOf("=");
            if (eq < 0) {
                map.put(p, null);
            } else if (eq == 0) {
                map.put("", p);
            } else {
                map.put(p.substring(0, eq), p.substring(eq + 1));
            }
        }
        return map;
    }
}
