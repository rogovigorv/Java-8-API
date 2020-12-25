package com.foxminded.Java8API.parser;

import com.foxminded.Java8API.domain.RadeDataDto;
import com.foxminded.Java8API.domain.Racer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static java.util.stream.Collectors.toList;

public class RacerParser implements Parser {
    private static final SimpleDateFormat FORMAT_TIME =
            new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss.SSS");
    private static final String UNDERSCORE = "_";

    @Override
    public List<Racer> parse(RadeDataDto radeDataDto) {

        Map<String, Map<String, String>> nameAndCommandMap = nameAndCommandParse(radeDataDto.getRacersNames());
        Map<String, Calendar> startTimeMap = timeParse(radeDataDto.getStartRace());
        Map<String, Calendar> endTimeMap = timeParse(radeDataDto.getEndRace());

        return nameAndCommandMap.entrySet().stream().map(e -> {

            String racerName = getName(e.getValue());
            String racerCommand = getCommand(e.getValue());
            long time = endTimeMap.get(e.getKey()).getTimeInMillis()
                    - startTimeMap.get(e.getKey()).getTimeInMillis();

            return new Racer(racerName, racerCommand, getTime(time));

        }).sorted(RacerTimeComparator).collect(toList());
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

    private final Comparator<Racer> RacerTimeComparator = (racer1, racer2) -> {

        Calendar racer1Time = racer1.getBestLapTime();
        Calendar racer2Time = racer2.getBestLapTime();

        return racer1Time.compareTo(racer2Time);
    };
}
