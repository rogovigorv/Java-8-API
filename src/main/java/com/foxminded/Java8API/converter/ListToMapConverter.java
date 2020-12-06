package com.foxminded.Java8API.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListToMapConverter implements Converter {
    private static final SimpleDateFormat FORMAT_TIME =
            new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss.SSS");

    private final List<String> readFromFile;

    public ListToMapConverter(List<String> readFromFile) {
        this.readFromFile = readFromFile;
    }

    @Override
    public Map<String, Calendar> convertToMap() {

        Map<String, Calendar> result = new HashMap<>();

        for (Map.Entry<String, String> pair : split(readFromFile).entrySet()) {
            String name = pair.getKey();
            String time = pair.getValue();
            Calendar calendar = new GregorianCalendar();

            try {
                calendar.setTime(FORMAT_TIME.parse(time));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            result.put(name, calendar);

        }

        return result;
    }

    private Map<String, String> split(List<String> readFromFile) {
        Map<String, String> splitLine= new HashMap<>();

        readFromFile.forEach(s -> splitLine.put(s.substring(0, 3), s.substring(3, 26)));

        return splitLine;
    }
}
