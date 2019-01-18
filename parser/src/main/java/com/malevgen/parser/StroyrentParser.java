package com.malevgen.parser;

import java.util.Date;

public class StroyrentParser implements Parser {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + ", StroyrentParser:  run to " + new Date());
    }
}
