package com.yevhen_parkhomenko.app;

import com.yevhen_parkhomenko.parsers.Context;
import com.yevhen_parkhomenko.parsers.DOMStrategy;
import com.yevhen_parkhomenko.parsers.SAXStrategy;
import com.yevhen_parkhomenko.software.Software;
import com.yevhen_parkhomenko.xmlcreator.XMLCreator;
import com.yevhen_parkhomenko.xmltohtml.XML2HTML;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

public class CodingCrimeScene extends JFrame {
    private JButton miracleButton;
    private JLabel filePathLabel;
    private JLabel keyWordLabel;
    private JTextField filePath;
    private JTextField keyWord;
    private JRadioButton DOMButton;
    private JRadioButton SAXButton;

    public CodingCrimeScene(){
        DOMButton = new JRadioButton("DOM", false);
        SAXButton = new JRadioButton("SAX", true);
        ButtonGroup group = new ButtonGroup();
        group.add(DOMButton);
        group.add(SAXButton);
        filePathLabel = new JLabel("Enter XML file path");
        filePath = new JTextField("");
        keyWordLabel = new JLabel("Enter keyword");
        keyWord = new JTextField("");
        miracleButton = new JButton("Let the miracle happen");
        miracleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Context context;
                if (DOMButton.isSelected()){
                    context = new Context(new DOMStrategy());
                }
                else{
                    context = new Context(new SAXStrategy());
                }
                List<Software> softwareList = context.executeStrategy(new File(filePath.getText()), keyWord.getText());
                for (Software software:softwareList)
                    System.out.println(software.getTitle());
                XMLCreator xmlCreator = new XMLCreator();
                xmlCreator.createXML(softwareList);
                XML2HTML toHTML = new XML2HTML();
                toHTML.convert();
                softwareList.clear();
                JOptionPane.showMessageDialog(null, "The Miracle has happened (probably).");
            }
        });

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Box contents = new Box(BoxLayout.Y_AXIS);
        contents.add(filePathLabel);
        contents.add(filePath);
        contents.add(keyWordLabel);
        contents.add(keyWord);
        contents.add(DOMButton);
        contents.add(SAXButton);
        contents.add(miracleButton);
        setContentPane(contents);
        setSize(500, 200);
        setVisible(true);
    }

    public static void main(String[] args) {
        new CodingCrimeScene();
    }
}