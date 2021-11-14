package com.yevhen_parkhomenko.parsers;

import com.yevhen_parkhomenko.software.Software;

import java.io.File;
import java.util.List;

public interface Strategy {
    public List<Software> strategyParse (File xmlFile, String search);
}
