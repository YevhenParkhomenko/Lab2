package com.yevhen_parkhomenko.parsers;

import com.yevhen_parkhomenko.software.Software;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DOM {
    private static List<Software> softwareList = new ArrayList<>();

    public List<Software> parseDOM(File xmlFile, String keyWord) {
        try{
            softwareList.clear();
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document document = builder.parse(xmlFile);

            NodeList softwareInfoComponents = document.getDocumentElement().getElementsByTagName("software");
            for (int i = 0; i < softwareInfoComponents.getLength(); i++) {
                Node software = softwareInfoComponents.item(i);
                if (software.getNodeType() == Node.ELEMENT_NODE){
                    Element element = (Element) software;
                    if (softwareInfoContains(element, keyWord)) {
                        softwareList.add(new Software(element.getElementsByTagName("title").item(0).getTextContent(),
                                element.getElementsByTagName("annotation").item(0).getTextContent(),
                                element.getElementsByTagName("type").item(0).getTextContent(),
                                element.getElementsByTagName("version").item(0).getTextContent(),
                                element.getElementsByTagName("author").item(0).getTextContent(),
                                element.getElementsByTagName("termsOfUse").item(0).getTextContent(),
                                element.getElementsByTagName("location").item(0).getTextContent()));
                    }
                }
            }

        }
        catch (Exception ex){
            System.out.println("ERROR");
        }
        return softwareList;
    }

    private boolean softwareInfoContains(Element element, String keyWord) {
        return ((element.getElementsByTagName("title").item(0).getTextContent().contains(keyWord)) ||
                (element.getElementsByTagName("annotation").item(0).getTextContent().contains(keyWord)) ||
                (element.getElementsByTagName("type").item(0).getTextContent().contains(keyWord)) ||
                (element.getElementsByTagName("version").item(0).getTextContent().contains(keyWord)) ||
                (element.getElementsByTagName("author").item(0).getTextContent().contains(keyWord)) ||
                (element.getElementsByTagName("termsOfUse").item(0).getTextContent().contains(keyWord)) ||
                (element.getElementsByTagName("location").item(0).getTextContent().contains(keyWord)));
    }
}