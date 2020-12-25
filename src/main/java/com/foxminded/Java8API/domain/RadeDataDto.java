package com.foxminded.Java8API.domain;

import java.util.List;
import java.util.Objects;

public class RadeDataDto {
    private final List<String> startRace;
    private final List<String> endRace;
    private final List<String> racersNames;

    public RadeDataDto(List<String> startRace, List<String> endRace, List<String> racersNames) {
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

    @Override
    public boolean equals(Object otherDto) {
        if (this == otherDto) {
            return true;
        }
        if (otherDto == null || getClass() != otherDto.getClass()) {
            return false;
        }

        RadeDataDto radeDataDto = (RadeDataDto) otherDto;

        return startRace == radeDataDto.startRace &&
                endRace == radeDataDto.endRace &&
                racersNames == radeDataDto.racersNames;
    }

    @Override
    public int hashCode() {
        return Objects.hash(startRace, endRace, racersNames);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        result.append("Start race time: ");
        startRace.forEach(result::append);

        result.append("End race time: ");
        endRace.forEach(result::append);

        result.append("Names and commands of racers: ");
        racersNames.forEach(result::append);

        return result.toString();
    }
}
