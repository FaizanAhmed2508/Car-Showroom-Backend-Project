package com.carshowroom.utility;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class CarShowroomUtil {

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    public static LocalDateTime changeCurrentTimeToLocalDateTimeFromGmtToIST() {
        LocalDateTime gmtTime = LocalDateTime.now(ZoneOffset.UTC);
        ZoneId istZone = ZoneId.of("Asia/Kolkata");
        LocalDateTime istTime = gmtTime.atZone(ZoneOffset.UTC)
                .withZoneSameInstant(istZone).toLocalDateTime();
        return istTime;
    }

    public static String changeCurrentTimeToLocalDateFromGmtToISTInString() {
        LocalDateTime gmtTime = LocalDateTime.now(ZoneOffset.UTC);
        ZoneId istZone = ZoneId.of("Asia/Kolkata");
        LocalDateTime istTime = gmtTime.atZone(ZoneOffset.UTC)
                .withZoneSameInstant(istZone).toLocalDateTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return istTime.format(formatter);
    }

    public static String nowIST() {
        return LocalDateTime
                .now(ZoneId.of("Asia/Kolkata"))
                .format(FORMATTER);
    }

    public static String plusDaysIST(int days) {
        return LocalDateTime
                .now(ZoneId.of("Asia/Kolkata"))
                .plusDays(days)
                .format(FORMATTER);
    }

    public static long daysRemaining(String endDate) {
        LocalDateTime end = LocalDateTime.parse(endDate, FORMATTER);
        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Kolkata"));
        return ChronoUnit.DAYS.between(now, end);
    }

    // ─── Validation helpers ──────────────────────────────────────────────────

    // Check if a Long ID is valid
    public static boolean isInvalidId(Long id) {
        return id == null || id <= 0;
    }

    // Check if a string is null or blank
    public static boolean isNullOrBlank(String value) {
        return value == null || value.trim().isEmpty();
    }

    // Check if a Double value is valid (not null and not negative)
    public static boolean isInvalidAmount(Double amount) {
        return amount == null || amount < 0;
    }

    // Check if an Integer year is valid
    public static boolean isInvalidYear(Integer year) {
        return year == null || year < 1886 || year > 2100;
    }

    // Check if a price range is valid
    public static boolean isInvalidPriceRange(Double minPrice, Double maxPrice) {
        return minPrice == null || maxPrice == null
                || minPrice < 0 || maxPrice < 0
                || minPrice > maxPrice;
    }

    // Check if a year range is valid
    public static boolean isInvalidYearRange(Integer startYear, Integer endYear) {
        return startYear == null || endYear == null || startYear > endYear;
    }

}
