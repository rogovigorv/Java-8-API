package com.foxminded.Java8API.reader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LogReader implements Reader{

    public List<String> readTopRacers(String fileAddress) {
        List<String> readFromFile = new ArrayList<>();

        try {
            readFromFile = Files.lines(Paths.get(fileAddress))
                    .collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return readFromFile;
    }
}
