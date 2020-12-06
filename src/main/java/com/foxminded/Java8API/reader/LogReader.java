package com.foxminded.Java8API.reader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LogReader implements Reader{

    @Override
    public List<String> read(String fileAddress) {
        List<String> readFromFile = new ArrayList<>();

        try {
            Stream<String> streamRacerName = Files.lines(Paths.get(fileAddress));
            readFromFile = streamRacerName.collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return readFromFile;
    }
}
