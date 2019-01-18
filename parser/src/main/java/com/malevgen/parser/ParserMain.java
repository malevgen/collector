package com.malevgen.parser;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ParserMain {
    public static void main(String[] args) {
        int initialDelay = 0;
        int period = 12;

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        ParserExecutor parser = new ParserExecutor();
        executorService.scheduleAtFixedRate(parser, initialDelay, period, TimeUnit.HOURS);

    }
}
