package com.foxminded.Java8API;

import com.foxminded.Java8API.domain.Racer;
import com.foxminded.Java8API.formatter.Formatter;
import com.foxminded.Java8API.formatter.TopRacersFormatter;
import com.foxminded.Java8API.parser.Parser;
import com.foxminded.Java8API.parser.RacerParser;
import com.foxminded.Java8API.reader.LogReader;
import com.foxminded.Java8API.reader.Reader;
import java.util.List;

public class Formula1ConsoleApplication {

    public static void main(String[] args) {

        Reader fileReader = new LogReader();
        Parser parser = new RacerParser();
        Formatter<List<Racer>> formatter = new TopRacersFormatter();
        String abbreviations = "src\\main\\resources\\abbreviations.txt";
        String startRace = "src\\main\\resources\\start.log";
        String endRace = "src\\main\\resources\\end.log";

        Formula1Facade formula1Facade =
                new Formula1Facade(fileReader, parser, formatter, abbreviations, startRace, endRace);

        System.out.println(formula1Facade.getTopRacersResult());
    }
}
