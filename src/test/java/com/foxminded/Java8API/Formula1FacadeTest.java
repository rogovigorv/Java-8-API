package com.foxminded.Java8API;

import com.foxminded.Java8API.domain.Racer;
import com.foxminded.Java8API.domain.RadeDataDto;
import com.foxminded.Java8API.formatter.Formatter;
import com.foxminded.Java8API.parser.Parser;
import com.foxminded.Java8API.reader.Reader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class Formula1FacadeTest {
    private static final String ABBREVIATIONS_TXT_FILE_ADDRESS = "src\\main\\resources\\abbreviations.txt";
    private static final String START_RACE_LOG_FILE_ADDRESS = "src\\main\\resources\\start.log";
    private static final String END_RACE_LOG_FILE_ADDRESS = "src\\main\\resources\\end.log";
    private static final List<String> FROM_ABBREVIATION_TXT_FILE = new ArrayList<>();
    private static final List<String> FROM_START_LOG_FILE = new ArrayList<>();
    private static final List<String> FROM_END_LOG_FILE = new ArrayList<>();
    private static final String RESULT =
            "Sebastian Vettel   | FERRARI                     | 01:04.415";

    @Mock
    Reader fileReader;

    @Mock
    Parser parser;

    @Mock
    Formatter<List<Racer>> formatter;

    @Mock
    RadeDataDto radeDataDto;

    private Formula1Facade formula1Facade;

    @BeforeEach
    private void setup() {

        formula1Facade = new Formula1Facade(fileReader, parser, formatter,
                ABBREVIATIONS_TXT_FILE_ADDRESS,
                START_RACE_LOG_FILE_ADDRESS,
                END_RACE_LOG_FILE_ADDRESS
        );

        FROM_ABBREVIATION_TXT_FILE.add("SVF_Sebastian Vettel_FERRARI");
        FROM_START_LOG_FILE.add("SVF2018-05-24_12:02:58.917");
        FROM_END_LOG_FILE.add("SVF2018-05-24_12:04:03.332");

    }

    @Test
    void makeFormula1FacadeShouldCheckOrderAndMethodCalls() {
        final String expected =
                "Sebastian Vettel   | FERRARI                     | 01:04.415";

        when(fileReader.readTopRacers(START_RACE_LOG_FILE_ADDRESS))
                .thenReturn(FROM_START_LOG_FILE);
        when(fileReader.readTopRacers(END_RACE_LOG_FILE_ADDRESS))
                .thenReturn(FROM_END_LOG_FILE);
        when(fileReader.readTopRacers(ABBREVIATIONS_TXT_FILE_ADDRESS))
                .thenReturn(FROM_ABBREVIATION_TXT_FILE);

        radeDataDto = new RadeDataDto(
                fileReader.readTopRacers(START_RACE_LOG_FILE_ADDRESS),
                fileReader.readTopRacers(END_RACE_LOG_FILE_ADDRESS),
                fileReader.readTopRacers(ABBREVIATIONS_TXT_FILE_ADDRESS)
        );

        when(formatter.format(parser.parse(radeDataDto))).thenReturn(RESULT);

        final String actual = formula1Facade.getTopRacersResult();

        assertEquals(expected, actual);

        InOrder order = Mockito.inOrder(fileReader, formatter);

        order.verify(fileReader).readTopRacers(START_RACE_LOG_FILE_ADDRESS);
        order.verify(fileReader).readTopRacers(END_RACE_LOG_FILE_ADDRESS);
        order.verify(fileReader).readTopRacers(ABBREVIATIONS_TXT_FILE_ADDRESS);
        order.verify(formatter).format(parser.parse(radeDataDto));
    }
}
