package com.foxminded.Java8API;

import com.foxminded.Java8API.domain.DTO;
import com.foxminded.Java8API.domain.Racer;
import com.foxminded.Java8API.formatter.Formatter;
import com.foxminded.Java8API.parser.Parser;
import com.foxminded.Java8API.reader.Reader;
import java.util.List;

public class Formula1Facade {
    private final Reader fileReader;
    private final Parser parser;
    private final Formatter<List<Racer>> formatter;
    private final String abbreviations;
    private final String startRace;
    private final String endRace;

    public Formula1Facade(Reader fileReader, Parser parser, Formatter<List<Racer>> formatter,
                          String abbreviations, String startRace, String endRace) {
        this.fileReader = fileReader;
        this.parser = parser;
        this.formatter = formatter;
        this.abbreviations = abbreviations;
        this.startRace = startRace;
        this.endRace = endRace;
    }

    public void getTopRacersResult() {

        DTO racers = new DTO(fileReader.readTopRacers(startRace),
                fileReader.readTopRacers(endRace),
                fileReader.readTopRacers(abbreviations));

        List<Racer> parseRacers = parser.parse(racers);
        System.out.println(formatter.format(parseRacers));
    }
}
