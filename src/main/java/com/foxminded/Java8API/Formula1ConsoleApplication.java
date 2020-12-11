package com.foxminded.Java8API;

import com.foxminded.Java8API.formatter.Formatter;
import com.foxminded.Java8API.formatter.TopRacersFormatter;
import com.foxminded.Java8API.parser.Parser;
import com.foxminded.Java8API.parser.RacerParser;
import com.foxminded.Java8API.reader.LogReader;
import com.foxminded.Java8API.reader.Reader;

public class Formula1ConsoleApplication {

    public static void main(String[] args) {

        Reader fileReader = new LogReader();
        Parser parser = new RacerParser();
        Formatter formatter = new TopRacersFormatter();

        Formula1Facade formula1Facade = new Formula1Facade(fileReader, parser, formatter);
        formula1Facade.domain();
    }
}
