package com.yevhen_parkhomenko.parsers;

import com.yevhen_parkhomenko.software.Software;

import java.io.File;
import java.util.List;

public class Context {
    private Strategy strategy;
    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    public List<Software> executeStrategy(File xmlFile, String search)
    {
        return strategy.strategyParse(xmlFile,search);
    }
}
