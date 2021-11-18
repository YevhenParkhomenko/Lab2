package com.yevhen_parkhomenko.xmltohtml;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class XML2HTML {
    public void convert(){
        try {
            TransformerFactory tFactory=TransformerFactory.newInstance();

            Source xslDoc = new StreamSource("rules.xsl");
            Source xmlDoc = new StreamSource("temp.xml");

            String outputFileName = "search.html";

            OutputStream htmlFile = new FileOutputStream(outputFileName);
            Transformer transform = tFactory.newTransformer(xslDoc);
            transform.transform(xmlDoc, new StreamResult(htmlFile));
        }
        catch(TransformerException | FileNotFoundException e){
            e.printStackTrace();
        }
    }
}