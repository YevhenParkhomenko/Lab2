package com.yevhen_parkhomenko.parsers;

import com.yevhen_parkhomenko.software.Software;

import java.io.File;
import java.util.List;

public class DOMStrategy implements Strategy{
    @Override
    public List<Software> strategyParse(File xmlFile, String search) {
        System.out.println("DOM");
        DOM dom = new DOM();
        return dom.parseDOM(xmlFile,search);
    }
}
