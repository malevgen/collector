package com.malevgen.parser;

import java.util.Date;

public class PetrovichParser implements Parser {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + ", PetrovichParser:  run to " + new Date());
    }
}
