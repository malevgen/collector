package com.malevgen.parser;

import java.util.ArrayList;
import java.util.List;

public class ParserExecutor implements Runnable {
    private List<Parser> parsers = new ArrayList<>(2);

    ParserExecutor() {
        parsers.add(new PetrovichParser());
        parsers.add(new StroyrentParser());
    }

    @Override
    public void run() {
        for (Parser parser : parsers) {
            new Thread(parser).start();
        }
    }
}
