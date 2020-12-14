package com.foxminded.Java8API.parser;

import com.foxminded.Java8API.domain.DTO;
import com.foxminded.Java8API.domain.Racer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RacerParser implements Parser {
    private static final SimpleDateFormat FORMAT_TIME =
            new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss.SSS");
    private static final String UNDERSCORE = "_";

    @Override
    public List<Racer> parse(DTO dto) {
        /*List<Racer> result = new LinkedList<>();

        Map<String, Map<String, String>> nameAndCommandMap = nameAndCommandParse(dto.getRacersNames());
        Map<String, Calendar> startTimeMap = timeParse(dto.getStartRace());
        Map<String, Calendar> endTimeMap = timeParse(dto.getEndRace());

        for (Map.Entry<String, Map<String, String>> name : nameAndCommandMap.entrySet()) {
            String nameAbbreviation = name.getKey();

            for (Map.Entry<String, Calendar> start : startTimeMap.entrySet()) {
                String startAbbreviation = start.getKey();

                for (Map.Entry<String, Calendar> end : endTimeMap.entrySet()) {
                    String endAbbreviation = end.getKey();

                    if (nameAbbreviation.equals(startAbbreviation) &&
                            nameAbbreviation.equals(endAbbreviation)) {

                        String racerName = getName(name.getValue());
                        String racerCommand = getCommand(name.getValue());
                        long time = end.getValue().getTimeInMillis() - start.getValue().getTimeInMillis();

                        result.add(new Racer(racerName, racerCommand, getTime(time)));
                    }
                }
            }
        }*/

        List<Racer> result = new LinkedList<>();

        Map<String, Map<String, String>> nameAndCommandMap = nameAndCommandParse(dto.getRacersNames());
        Map<String, Calendar> startTimeMap = timeParse(dto.getStartRace());
        Map<String, Calendar> endTimeMap = timeParse(dto.getEndRace());

        result = nameAndCommandMap.entrySet().stream().flatMap(a -> startTimeMap.entrySet().stream()
                .flatMap(b -> endTimeMap.entrySet().stream().map(c -> {
                    String racerName = null;
                    String racerCommand = null;
                    long time = 0;
                    if (a.getKey().equals(b.getKey()) &&
                            a.getKey().equals(c.getKey())) {

                    racerName = getName(a.getValue());
                    racerCommand = getCommand(a.getValue());
                    time = c.getValue().getTimeInMillis() - b.getValue().getTimeInMillis();

                    }
                    return new Racer(racerName, racerCommand, getTime(time));
                }))).collect(Collectors.toList());

        result.sort(Racer.RacerTimeComparator);

        return result;
    }

    private Map<String, Map<String, String>> nameAndCommandParse(List<String> abbreviations) {
        Map<String, Map<String, String>> result = new HashMap<>();

        abbreviations.forEach(s -> {
            String[] splitLine = s.split(UNDERSCORE);

            Map<String, String> nameAndCommand = new HashMap<>();
            nameAndCommand.put(splitLine[1], splitLine[2]);

            result.put(splitLine[0], nameAndCommand);
        });

        return result;
    }

    private Map<String, Calendar> timeParse(List<String> timeList) {
        Map<String, Calendar> result= new HashMap<>();

        timeList.forEach(s -> result.put(s.substring(0, 3),
                getTime(s.substring(3, 26))));

        return result;

    }

    private Calendar getTime(String time) {
        Calendar calendar = new GregorianCalendar();

        try {
            calendar.setTime(FORMAT_TIME.parse(time));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return calendar;
    }

    private Calendar getTime(Long time) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTimeInMillis(time);

        return calendar;
    }

    private String getName(Map<String, String> innerMap) {
        StringBuilder name = new StringBuilder();

        innerMap.forEach((k, v) -> name.append(k));

        return name.toString();
    }

    private String getCommand(Map<String, String> innerMap) {
        StringBuilder command = new StringBuilder();

        innerMap.forEach((k, v) -> command.append(v));

        return command.toString();
    }
}
