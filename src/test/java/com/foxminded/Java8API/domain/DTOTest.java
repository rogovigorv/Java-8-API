package com.foxminded.Java8API.domain;

import org.junit.jupiter.api.Test;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DTOTest {
    private static final List<String> FROM_ABBREVIATION_TXT_FILE =
            Collections.singletonList("SVF_Sebastian Vettel_FERRARI");
    private static final List<String> FROM_START_LOG_FILE =
            Collections.singletonList("SVF2018-05-24_12:02:58.917");
    private static final List<String> FROM_END_LOG_FILE =
            Collections.singletonList("SVF2018-05-24_12:04:03.332");

    private final DTO dto = new DTO(FROM_START_LOG_FILE, FROM_END_LOG_FILE, FROM_ABBREVIATION_TXT_FILE);

    @Test
    void shouldGetRacersNames() {
        final List<String> expected = Collections.singletonList("SVF_Sebastian Vettel_FERRARI");
        final List<String> actual = dto.getRacersNames();

        assertEquals(expected, actual);
    }

    @Test
    void shouldGetStartRace() {
        final List<String> expected = Collections.singletonList("SVF2018-05-24_12:02:58.917");
        final List<String> actual = dto.getStartRace();

        assertEquals(expected, actual);
    }

    @Test
    void shouldGetEndRace() {
        final List<String> expected = Collections.singletonList("SVF2018-05-24_12:04:03.332");
        final List<String> actual = dto.getEndRace();

        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnDtoToString() {
        final String expected = "Start race time: SVF2018-05-24_12:02:58.917"
                + "End race time: SVF2018-05-24_12:04:03.332"
                + "Names and commands of racers: SVF_Sebastian Vettel_FERRARI";
        final String actual = dto.toString();

        assertEquals(expected, actual);
    }
}
