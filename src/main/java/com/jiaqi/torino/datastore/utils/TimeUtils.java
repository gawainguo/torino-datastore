package com.jiaqi.torino.datastore.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

public class TimeUtils {

    public static LocalDateTime parse(String dateTimeStr) {
        return parse(dateTimeStr, DateTimeFormatter.ISO_DATE_TIME);
    }

    public static LocalDateTime parse(String dateTimeStr, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return parse(dateTimeStr, formatter);
    }

    public static LocalDateTime parse(String dateTimeStr, DateTimeFormatter formatter) {
        return utc(ZonedDateTime.parse(dateTimeStr, formatter));
    }

    /**
     * Get current datetime in UTC
     * @return LocalDateTime with current datetime in UTC.
     */
    public static LocalDateTime now() {
        return utc(ZonedDateTime.now());
    }

    /**
     * Get current local datetime in UTC.
     * Null safe.
     * @param dt Datetime with timezone info
     * @return Current LocalDateTime in UTC without timezone info.
     */
    public static LocalDateTime utc(ZonedDateTime dt) {
        return dt == null ? null : dt.withZoneSameInstant(ZoneId.of("UTC")).toLocalDateTime();
    }

    public static void main(String[] args) {
        System.out.println(TimeUtils.now());
    }
}