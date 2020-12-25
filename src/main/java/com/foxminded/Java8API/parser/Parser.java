package com.foxminded.Java8API.parser;

import com.foxminded.Java8API.domain.RadeDataDto;
import com.foxminded.Java8API.domain.Racer;
import java.util.List;

public interface Parser {
    List<Racer> parse(RadeDataDto racers);
}
