package com.yevhen_parkhomenko.xmlcreator;

import com.yevhen_parkhomenko.software.Software;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

public class XMLCreator {
    private FileOutputStream fout = null;

    public void createXML(List<Software> softwareList){
        try {
            fout = new FileOutputStream("temp.xml");
            BufferedWriter bo = new BufferedWriter(new OutputStreamWriter(fout));
            bo.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>");
            bo.newLine();
            bo.write("<?xml-stylesheet type=\"\" ?>");
            bo.newLine();
            bo.write("<softwareList type=\"root\">");
            bo.newLine();
            for (int i = 0; i < softwareList.size(); i++) {
                bo.write("<software id=\"" + i + "\">");
                bo.newLine();
                bo.write("<title>" + softwareList.get(i).getTitle() + "</title>");
                bo.newLine();
                bo.write("<annotation>" + softwareList.get(i).getAnnotation() + "</annotation>");
                bo.newLine();
                bo.write("<type>" + softwareList.get(i).getType() + "</type>");
                bo.newLine();
                bo.write("<version>" + softwareList.get(i).getVersion() + "</version>");
                bo.newLine();
                bo.write("<author>" + softwareList.get(i).getAuthor() + "</author>");
                bo.newLine();
                bo.write("<termsOfUse>" + softwareList.get(i).getTitle() + "</termsOfUse>");
                bo.newLine();
                bo.write("<location>" + softwareList.get(i).getLocation() + "</location>");
                bo.newLine();
                bo.write("</software>");
                bo.newLine();
            }
            bo.write("</softwareList>");
            bo.close();
        } catch (Exception ex) {
            System.out.println("ERROR");
        }
    }
}
