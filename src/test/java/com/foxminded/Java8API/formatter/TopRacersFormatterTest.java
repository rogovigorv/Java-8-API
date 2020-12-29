package com.foxminded.Java8API.formatter;

import com.foxminded.Java8API.domain.Racer;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TopRacersFormatterTest {
    private static final Calendar SEBASTIAN_VETTEL_TIME = new GregorianCalendar();
    private static final Calendar DANIEL_RICCIARDO_TIME = new GregorianCalendar();
    private static final Calendar VALTTERI_BOTTAS_TIME = new GregorianCalendar();
    private static final Calendar LEWIS_HAMILTON_TIME = new GregorianCalendar();
    private static final Calendar STOFFEL_VANDOORNE_TIME = new GregorianCalendar();
    private static final Calendar KIMI_RAIKKONEN_TIME = new GregorianCalendar();
    private static final Calendar FERNANDO_ALONSO_TIME = new GregorianCalendar();
    private static final Calendar SERGEY_SIROTKIN_TIME = new GregorianCalendar();
    private static final Calendar CHARLES_LECLERC_TIME = new GregorianCalendar();
    private static final Calendar SERGIO_PEREZ_TIME = new GregorianCalendar();
    private static final Calendar ROMAIN_GROSJEAN_TIME = new GregorianCalendar();
    private static final Calendar PIERRE_GASLY_TIME = new GregorianCalendar();
    private static final Calendar CARLOS_SAINZ_TIME = new GregorianCalendar();
    private static final Calendar ESTEBAN_OCON_TIME = new GregorianCalendar();
    private static final Calendar NICO_HULKENBERG_TIME = new GregorianCalendar();
    private static final Calendar BRENDON_HARTLEY_TIME = new GregorianCalendar();
    private static final String DELIMITER = "";
    private static final String DASH = "-";
    private static final String LINE_BREAK = "\n";
    private static final String LONG_LINE = String.join(DELIMITER, Collections.nCopies(60, DASH));
    private static final List<Racer> RACERS_LIST_IS_NULL = null;
    private static final List<Racer> EMPTY_LIST = new ArrayList<>();

    private final Formatter<List<Racer>> formatter = new TopRacersFormatter();

    @Test
    void makeFormatOfSixteenRacersList() {
        List<Racer> racersTestList = new LinkedList<>();

        setTime();
        addToList(racersTestList);

        final String expected =
                  "Sebastian Vettel   | FERRARI                     | 01:04.415" + LINE_BREAK
                + "Daniel Ricciardo   | RED BULL RACING TAG HEUER   | 01:12.013" + LINE_BREAK
                + "Valtteri Bottas    | MERCEDES                    | 01:12.434" + LINE_BREAK
                + "Lewis Hamilton     | MERCEDES                    | 01:12.460" + LINE_BREAK
                + "Stoffel Vandoorne  | MCLAREN RENAULT             | 01:12.463" + LINE_BREAK
                + "Kimi Raikkonen     | FERRARI                     | 01:12.639" + LINE_BREAK
                + "Fernando Alonso    | MCLAREN RENAULT             | 01:12.657" + LINE_BREAK
                + "Sergey Sirotkin    | WILLIAMS MERCEDES           | 01:12.706" + LINE_BREAK
                + "Charles Leclerc    | SAUBER FERRARI              | 01:12.829" + LINE_BREAK
                + "Sergio Perez       | FORCE INDIA MERCEDES        | 01:12.848" + LINE_BREAK
                + "Romain Grosjean    | HAAS FERRARI                | 01:12.930" + LINE_BREAK
                + "Pierre Gasly       | SCUDERIA TORO ROSSO HONDA   | 01:12.941" + LINE_BREAK
                + "Carlos Sainz       | RENAULT                     | 01:12.950" + LINE_BREAK
                + "Esteban Ocon       | FORCE INDIA MERCEDES        | 01:13.028" + LINE_BREAK
                + "Nico Hulkenberg    | RENAULT                     | 01:13.065" + LINE_BREAK
                + LONG_LINE + LINE_BREAK
                + "Brendon Hartley    | SCUDERIA TORO ROSSO HONDA   | 01:13.179" + LINE_BREAK;

        final String actual = formatter.format(racersTestList);

        assertEquals(expected, actual);
    }

    @Test
    void makeTopRacersFormatterShouldThrowIllegalArgumentExceptionWhenNullPassed() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                formatter.format(RACERS_LIST_IS_NULL));
        assertThat("Racers list is null", equalTo(exception.getMessage()));
    }

    @Test
    void makeTopRacersFormatterShouldThrowIllegalArgumentExceptionWhenListIsEmpty() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                formatter.format(EMPTY_LIST));
        assertThat("Racers list is empty", equalTo(exception.getMessage()));
    }

    private void addToList(List<Racer> racersTestList) {
        racersTestList.add(new Racer("Sebastian Vettel",
                "FERRARI", SEBASTIAN_VETTEL_TIME));
        racersTestList.add(new Racer("Daniel Ricciardo",
                "RED BULL RACING TAG HEUER", DANIEL_RICCIARDO_TIME));
        racersTestList.add(new Racer("Valtteri Bottas",
                "MERCEDES", VALTTERI_BOTTAS_TIME));
        racersTestList.add(new Racer("Lewis Hamilton",
                "MERCEDES", LEWIS_HAMILTON_TIME));
        racersTestList.add(new Racer("Stoffel Vandoorne",
                "MCLAREN RENAULT", STOFFEL_VANDOORNE_TIME));
        racersTestList.add(new Racer("Kimi Raikkonen",
                "FERRARI", KIMI_RAIKKONEN_TIME));
        racersTestList.add(new Racer("Fernando Alonso",
                "MCLAREN RENAULT", FERNANDO_ALONSO_TIME));
        racersTestList.add(new Racer("Sergey Sirotkin",
                "WILLIAMS MERCEDES", SERGEY_SIROTKIN_TIME));
        racersTestList.add(new Racer("Charles Leclerc",
                "SAUBER FERRARI", CHARLES_LECLERC_TIME));
        racersTestList.add(new Racer("Sergio Perez",
                "FORCE INDIA MERCEDES", SERGIO_PEREZ_TIME));
        racersTestList.add(new Racer("Romain Grosjean",
                "HAAS FERRARI", ROMAIN_GROSJEAN_TIME));
        racersTestList.add(new Racer("Pierre Gasly",
                "SCUDERIA TORO ROSSO HONDA", PIERRE_GASLY_TIME));
        racersTestList.add(new Racer("Carlos Sainz",
                "RENAULT", CARLOS_SAINZ_TIME));
        racersTestList.add(new Racer("Esteban Ocon",
                "FORCE INDIA MERCEDES", ESTEBAN_OCON_TIME));
        racersTestList.add(new Racer("Nico Hulkenberg",
                "RENAULT", NICO_HULKENBERG_TIME));
        racersTestList.add(new Racer("Brendon Hartley",
                "SCUDERIA TORO ROSSO HONDA", BRENDON_HARTLEY_TIME));
    }

    private void setTime() {
        SEBASTIAN_VETTEL_TIME.setTimeInMillis(64415);
        DANIEL_RICCIARDO_TIME.setTimeInMillis(72013);
        VALTTERI_BOTTAS_TIME.setTimeInMillis(72434);
        LEWIS_HAMILTON_TIME.setTimeInMillis(72460);
        STOFFEL_VANDOORNE_TIME.setTimeInMillis(72463);
        KIMI_RAIKKONEN_TIME.setTimeInMillis(72639);
        FERNANDO_ALONSO_TIME.setTimeInMillis(72657);
        SERGEY_SIROTKIN_TIME.setTimeInMillis(72706);
        CHARLES_LECLERC_TIME.setTimeInMillis(72829);
        SERGIO_PEREZ_TIME.setTimeInMillis(72848);
        ROMAIN_GROSJEAN_TIME.setTimeInMillis(72930);
        PIERRE_GASLY_TIME.setTimeInMillis(72941);
        CARLOS_SAINZ_TIME.setTimeInMillis(72950);
        ESTEBAN_OCON_TIME.setTimeInMillis(73028);
        NICO_HULKENBERG_TIME.setTimeInMillis(73065);
        BRENDON_HARTLEY_TIME.setTimeInMillis(73179);
    }
}
