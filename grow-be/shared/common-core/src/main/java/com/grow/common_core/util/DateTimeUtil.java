package com.grow.common_core.util;

import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DateTimeUtil {

    private static final String UTC_TIMEZONE = "UTC";
    private static final Map<String, String> DATE_FORMAT_REGEXPS = new HashMap<String, String>() {{
        put("^\\d{8}$", "yyyyMMdd");
        put("^\\d{1,2}-\\d{1,2}-\\d{4}$", "dd-MM-yyyy");
        put("^\\d{4}-\\d{1,2}-\\d{1,2}$", "yyyy-MM-dd");
        put("^\\d{1,2}/\\d{1,2}/\\d{4}$", "MM/dd/yyyy");
        put("^\\d{1}/\\d{1,2}/\\d{4}$", "M/dd/yyyy");
        put("^\\d{1,2}/\\d{1,2}/\\d{2}$", "M/d/yy");
        put("^\\d{4}/\\d{1,2}/\\d{1,2}$", "yyyy/MM/dd");
        put("^\\d{1,2}\\s[a-z]{3}\\s\\d{4}$", "dd MMM yyyy");
        put("^\\d{1,2}\\s[a-z]{4,}\\s\\d{4}$", "dd MMMM yyyy");
        put("^\\d{12}$", "yyyyMMddHHmm");
        put("^\\d{8}\\s\\d{4}$", "yyyyMMdd HHmm");
        put("^\\d{1,2}-\\d{1,2}-\\d{4}\\s\\d{1,2}:\\d{2}$", "dd-MM-yyyy HH:mm");
        put("^\\d{4}-\\d{1,2}-\\d{1,2}\\s\\d{1,2}:\\d{2}$", "yyyy-MM-dd HH:mm");
        put("^\\d{1,2}/\\d{1,2}/\\d{4}\\s\\d{1,2}:\\d{2}$", "MM/dd/yyyy HH:mm");
        put("^\\d{4}/\\d{1,2}/\\d{1,2}\\s\\d{1,2}:\\d{2}$", "yyyy/MM/dd HH:mm");
        put("^\\d{1,2}\\s[a-z]{3}\\s\\d{4}\\s\\d{1,2}:\\d{2}$", "dd MMM yyyy HH:mm");
        put("^\\d{1,2}\\s[a-z]{4,}\\s\\d{4}\\s\\d{1,2}:\\d{2}$", "dd MMMM yyyy HH:mm");
        put("^\\d{14}$", "yyyyMMddHHmmss");
        put("^\\d{8}\\s\\d{6}$", "yyyyMMdd HHmmss");
        put("^\\d{1,2}-\\d{1,2}-\\d{4}\\s\\d{1,2}:\\d{2}:\\d{2}$", "dd-MM-yyyy HH:mm:ss");
        put("^\\d{4}-\\d{1,2}-\\d{1,2}\\s\\d{1,2}:\\d{2}:\\d{2}$", "yyyy-MM-dd HH:mm:ss");
        put("^\\d{1,2}/\\d{1,2}/\\d{4}\\s\\d{1,2}:\\d{2}:\\d{2}$", "MM/dd/yyyy HH:mm:ss");
        put("^\\d{4}/\\d{1,2}/\\d{1,2}\\s\\d{1,2}:\\d{2}:\\d{2}$", "yyyy/MM/dd HH:mm:ss");
        put("^\\d{1,2}\\s[a-z]{3}\\s\\d{4}\\s\\d{1,2}:\\d{2}:\\d{2}$", "dd MMM yyyy HH:mm:ss");
        put("^\\d{1,2}\\s[a-z]{4,}\\s\\d{4}\\s\\d{1,2}:\\d{2}:\\d{2}$", "dd MMMM yyyy HH:mm:ss");
    }};

    /**
     * Determine SimpleDateFormat pattern matching with the given date string. Returns null if
     * format is unknown. You can simply extend DateUtil with more formats if needed.
     *
     * @param dateString The date string to determine the SimpleDateFormat pattern for.
     * @return The matching SimpleDateFormat pattern, or null if format is unknown.
     * @see SimpleDateFormat
     */
    public static String determineDateFormat(String dateString) {
        if (dateString == null) return null;
        for (String regexp : DATE_FORMAT_REGEXPS.keySet()) {
            if (dateString.toLowerCase().matches(regexp)) {
                return DATE_FORMAT_REGEXPS.get(regexp);
            }
        }
        return null;
    }

    public static LocalDateTime currentUtcTime() {
        return LocalDateTime.now(ZoneOffset.UTC);
    }

    public static ZonedDateTime toZonedDateTime(LocalDateTime localDateTime) {
        if (localDateTime == null) return null;
        return localDateTime.atZone(ZoneOffset.UTC);
    }

    public static ZonedDateTime currentZonedUtcTime() {
        return ZonedDateTime.now(ZoneOffset.UTC);
    }

    public static long currentTimeMillis() {
        LocalDateTime currentDateTime = currentUtcTime();
        return millisAtUtc(currentDateTime);
    }

    public static long millisAtUtc(@NonNull LocalDateTime date) {
        return date.atZone(ZoneId.of(UTC_TIMEZONE))
                .toInstant().toEpochMilli();
    }

    public static LocalDateTime toLocalDateTime(long millisAtUtc) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(millisAtUtc), ZoneId.of(UTC_TIMEZONE));
    }


    public static Date toDate(@NonNull LocalDateTime value) {
        return new Date(millisAtUtc(value));
    }

    public static Date toDate(@NonNull ZonedDateTime value) {
        return Date.from(value.toInstant());
    }

    public static LocalDateTime toLocalDateTime(Instant instant) {
        if (instant == null) throw new IllegalArgumentException("Instant must not be null");
        return LocalDateTime.ofInstant(instant, ZoneId.of(UTC_TIMEZONE));
    }

    @AllArgsConstructor
    public enum TemporalUnit {
        MINUTE("minute", ChronoUnit.MINUTES),
        HOUR("hour", ChronoUnit.HOURS),
        DAY("day", ChronoUnit.DAYS),
        WEEK("week", ChronoUnit.WEEKS),
        MONTH("month", ChronoUnit.MONTHS),
        YEAR("year", ChronoUnit.YEARS);

        private final String value;
        private final ChronoUnit unit;

        public Long getSeconds() {
            return unit.getDuration().getSeconds();
        }

        public static TemporalUnit fromString(String value) {
            return Arrays.stream(values()).filter(v -> v.value.equals(value))
                    .findAny()
                    .orElseThrow(() -> new RuntimeException("Unknown temporal unit type"));
        }
    }
}
