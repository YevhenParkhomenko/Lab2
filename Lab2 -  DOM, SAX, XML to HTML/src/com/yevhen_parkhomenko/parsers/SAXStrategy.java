package com.yevhen_parkhomenko.parsers;

import com.yevhen_parkhomenko.software.Software;

import java.io.File;
import java.util.List;

public class SAXStrategy implements Strategy{
    @Override
    public List<Software> strategyParse(File xmlFile, String search) {
        System.out.println("SAX");
        SAX sax = new SAX();
        return sax.parseSAX(xmlFile,search);
    }
}