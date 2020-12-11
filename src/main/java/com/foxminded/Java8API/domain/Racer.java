package com.foxminded.Java8API.domain;

import java.util.Calendar;
import java.util.Comparator;

public class Racer implements Comparable<Racer> {

    private final String name;
    private final String command;
    private final Calendar bestLapTime;

    public Racer(String name, String command, Calendar bestLapTime) {
        this.name = name;
        this.command = command;
        this.bestLapTime = bestLapTime;
    }

    public String getName() {
        return name;
    }

    public String getCommand() {
        return command;
    }

    public Calendar getBestLapTime() {
        return bestLapTime;
    }

    @Override
    public int compareTo(Racer racer) {
        long compareTime = racer.getBestLapTime().getTimeInMillis();
        return (int) (this.bestLapTime.getTimeInMillis() - compareTime);
    }

    public static Comparator<Racer> RacerTimeComparator = (racer1, racer2) -> {

        Calendar racer1Time = racer1.getBestLapTime();
        Calendar racer2Time = racer2.getBestLapTime();

        return racer1Time.compareTo(racer2Time);
    };
}
