package com.yevhen_parkhomenko.parsers;

import com.yevhen_parkhomenko.software.Software;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SAX {
    private static List<Software> softwareList = new ArrayList<>();

    public static List<Software> parseSAX(File XMLFile, String keyWord){
        softwareList.clear();

        List<Software> foundSoftwareList = new ArrayList<>();

        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            DefaultHandler handler = new AdvancedXMLHandler();

            saxParser.parse(XMLFile, handler);

            for (int i = 0; i < softwareList.size(); i++) {
                if (softwareList.get(i) != null && containsKeyWord(softwareList.get(i), keyWord)) {
                    foundSoftwareList.add(softwareList.get(i));
                }
            }
        } catch (Exception e) {
            System.out.println("ERROR");
        }
        return foundSoftwareList;
    }
    private static class AdvancedXMLHandler extends DefaultHandler {
        private String title, annotation, type, version, author, termsOfUse, location, lastElementName;

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            lastElementName = qName;
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            String temp = new String(ch, start, length);

            temp = temp.replace("\n", "").trim();

            if (!temp.isEmpty()) {
                if (lastElementName.equals("title"))
                    title = temp;
                if (lastElementName.equals("annotation"))
                    annotation = temp;
                if (lastElementName.equals("type"))
                    type = temp;
                if (lastElementName.equals("version"))
                    version = temp;
                if (lastElementName.equals("author"))
                    author = temp;
                if (lastElementName.equals("termsOfUse"))
                    termsOfUse = temp;
                if (lastElementName.equals("location"))
                    location = temp;
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            if ((title != null && !title.isEmpty()) &&
                    (annotation != null && !annotation.isEmpty()) &&
                    (type != null && !type.isEmpty()) &&
                    (version != null && !version.isEmpty()) &&
                    (author != null && !author.isEmpty()) &&
                    (termsOfUse != null && !termsOfUse.isEmpty()) &&
                    (location != null && !location.isEmpty())) {
                softwareList.add(new Software(title, annotation, type, version, author, termsOfUse, location));
                title = null;
                annotation = null;
                type = null;
                version = null;
                author = null;
                termsOfUse = null;
                location = null;
            }
        }
    }
    private static boolean containsKeyWord(Software software, String keyWord){
        return (software.getTitle().contains(keyWord) || software.getAnnotation().contains(keyWord) ||
                software.getType().contains(keyWord) || software.getVersion().contains(keyWord) ||
                software.getAuthor().contains(keyWord) || software.getTermsOfUse().contains(keyWord) ||
                software.getLocation().contains(keyWord));
    }
}
