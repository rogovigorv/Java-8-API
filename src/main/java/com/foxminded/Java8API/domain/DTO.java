package com.foxminded.Java8API.domain;

import java.util.List;

public class DTO {
    private final List<String> startRace;
    private final List<String> endRace;
    private final List<String> racersNames;

    public DTO(List<String> startRace, List<String> endRace, List<String> racersNames) {
        this.startRace = startRace;
        this.endRace = endRace;
        this.racersNames = racersNames;
    }

    public List<String> getRacersNames() {
        return racersNames;
    }

    public List<String> getStartRace() {
        return startRace;
    }

    public List<String> getEndRace() {
        return endRace;
    }
}
