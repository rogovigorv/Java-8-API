package com.foxminded.Java8API.calculator;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class TimeCalculator implements Calculator {
    private final Map<String, Calendar> startLogMap;
    private final Map<String, Calendar> endLogMap;

    public TimeCalculator(Map<String, Calendar> startLogMap, Map<String, Calendar> endLogMap) {
        this.startLogMap = startLogMap;
        this.endLogMap = endLogMap;
    }

    @Override
    public Map<String, Calendar> calculate() {
        Map<String, Calendar> result = new LinkedHashMap<>();
        Map<String, Long> millis = new HashMap<>();

        for (Map.Entry<String, Calendar> startMap : startLogMap.entrySet()) {
            String startName = startMap.getKey();
            Calendar startTime = startMap.getValue();
            for (Map.Entry<String, Calendar> endMap : endLogMap.entrySet()) {
                String endName = endMap.getKey();
                Calendar endTime = endMap.getValue();
                if (startName.equals(endName)) {
                    long time = endTime.getTimeInMillis() - startTime.getTimeInMillis();
                    millis.put(startName, time);
                }
            }
        }

        millis.entrySet().stream().sorted(Map.Entry.comparingByValue())
                .forEach(e -> result.put(e.getKey(), getTime(e.getValue())));

        return result;
    }

    private Calendar getTime(Long timeInMillis) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTimeInMillis(timeInMillis);

        return calendar;
    }
}
