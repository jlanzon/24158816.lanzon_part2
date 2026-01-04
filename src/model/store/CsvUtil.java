package model.store;

import java.util.Map;

public final class CsvUtil {
    private CsvUtil() {}

    public static String get(Map<String, String> row, String... possibleHeaders) {
        for (String h : possibleHeaders) {
            if (row.containsKey(h)) return safe(row.get(h));
        }
        return "";
    }

    private static String safe(String s) {
        return s == null ? "" : s.trim();
    }
}
