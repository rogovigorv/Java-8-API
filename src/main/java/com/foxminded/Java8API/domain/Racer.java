package com.foxminded.Java8API.domain;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Objects;

public class Racer implements Comparable<Racer> {
    private static final SimpleDateFormat FORMAT_TIME = new SimpleDateFormat("mm:ss.SSS");

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

    @Override
    public boolean equals(Object otherRacer) {
        if (this == otherRacer) {
            return true;
        }
        if (otherRacer == null || getClass() != otherRacer.getClass()) {
            return false;
        }

        Racer racer = (Racer) otherRacer;

        return name.equals(racer.name) &&
                command.equals(racer.command) &&
                bestLapTime == racer.bestLapTime;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, command, bestLapTime);
    }

    @Override
    public String toString() {

        return "Name " + name + "Command " + command + "Best lap time " + FORMAT_TIME.format(bestLapTime.getTime());
    }
}
