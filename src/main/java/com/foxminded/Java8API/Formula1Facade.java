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
    private final Formatter formatter;

    public Formula1Facade(Reader fileReader, Parser parser, Formatter formatter) {
        this.fileReader = fileReader;
        this.parser = parser;
        this.formatter = formatter;
    }

    public void domain() {

        DTO racers = new DTO(fileReader.read("src\\main\\resources\\start.log"),
                fileReader.read("src\\main\\resources\\end.log"),
                fileReader.read("src\\main\\resources\\abbreviations.txt"));

        List<Racer> parseRacers = parser.parse(racers);
        System.out.println(formatter.format(parseRacers));
    }
}
