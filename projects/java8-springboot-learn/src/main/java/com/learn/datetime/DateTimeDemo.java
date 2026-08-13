package com.learn.datetime;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Java 8 Date/Time API (java.time).
 */
@Service
public class DateTimeDemo {

    public Object demo() {
        LocalDate today = LocalDate.now();
        LocalDate joining = LocalDate.of(2011, 8, 1);
        Period experience = Period.between(joining, today);

        LocalDateTime now = LocalDateTime.now();
        ZonedDateTime ist = ZonedDateTime.now(ZoneId.of("Asia/Kolkata"));
        String formatted = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        Map<String, Object> result = new LinkedHashMap<String, Object>();
        result.put("today", today.toString());
        result.put("experienceYears", experience.getYears());
        result.put("experienceMonths", experience.getMonths());
        result.put("formattedNow", formatted);
        result.put("ist", ist.toString());
        return result;
    }
}
