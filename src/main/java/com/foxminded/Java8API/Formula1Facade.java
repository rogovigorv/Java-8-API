package com.foxminded.Java8API;

import com.foxminded.Java8API.calculator.Calculator;
import com.foxminded.Java8API.calculator.TimeCalculator;
import com.foxminded.Java8API.converter.Converter;
import com.foxminded.Java8API.converter.ListToMapConverter;
import com.foxminded.Java8API.formatter.Formatter;
import com.foxminded.Java8API.formatter.Formula1Formatter;
import com.foxminded.Java8API.reader.LogReader;
import com.foxminded.Java8API.reader.Reader;
import java.util.Calendar;
import java.util.Map;

public class Formula1Facade {

    public String domain() {

        Reader startLogReader = new LogReader();
        Converter startConverter = new ListToMapConverter(startLogReader
                .read("src\\main\\resources\\start.log"));
        Map<String, Calendar> startLogMap = startConverter.convertToMap();

        Reader endLogReader = new LogReader();
        Converter endConverter = new ListToMapConverter(endLogReader
                .read("src\\main\\resources\\end.log"));
        Map<String, Calendar> endLogMap = endConverter.convertToMap();

        Calculator calculator = new TimeCalculator(startLogMap, endLogMap);
        Map<String, Calendar> calculatedMap = calculator.calculate();

        Formatter formatter = new Formula1Formatter(calculatedMap);

        return formatter.format();
    }
}
