package com.foxminded.Java8API.formatter;

import com.foxminded.Java8API.domain.Racer;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;

public class TopRacersFormatter implements Formatter {
    private static final SimpleDateFormat FORMAT_TIME = new SimpleDateFormat("mm:ss.SSS");
    private static final String LINE_BREAK = "\n";
    private static final String TAB = " ";
    private static final String DASH = "-";
    private static final String VERTICAL_LINE = "|";
    private static final String DELIMITER = "";

    @Override
    public String format(List<Racer> racers) {
        StringBuilder result = new StringBuilder();

        racers.stream().limit(15).forEach(r -> result.append(r.getName())
                .append(String.format("%1$" + (20 - r.getName().length()) + "s", VERTICAL_LINE))
                .append(TAB)
                .append(r.getCommand())
                .append(String.format("%1$" + (29 - r.getCommand().length()) + "s", VERTICAL_LINE))
                .append(TAB)
                .append(FORMAT_TIME.format(r.getBestLapTime().getTime()))
                .append(LINE_BREAK));

        String longLine = String.join(DELIMITER, Collections.nCopies(60, DASH));
        result.append(longLine)
                .append(LINE_BREAK);

        racers.stream().skip(15).forEach(r -> result.append(r.getName())
                .append(String.format("%1$" + (20 - r.getName().length()) + "s", VERTICAL_LINE))
                .append(TAB)
                .append(r.getCommand())
                .append(String.format("%1$" + (29 - r.getCommand().length()) + "s", VERTICAL_LINE))
                .append(TAB)
                .append(FORMAT_TIME.format(r.getBestLapTime().getTime()))
                .append(LINE_BREAK));

        return result.toString();
    }
}
