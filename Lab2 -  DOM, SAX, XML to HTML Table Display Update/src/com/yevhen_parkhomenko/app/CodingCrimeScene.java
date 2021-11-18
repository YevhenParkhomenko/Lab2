package com.yevhen_parkhomenko.app;

import com.yevhen_parkhomenko.parsers.Context;
import com.yevhen_parkhomenko.parsers.DOMStrategy;
import com.yevhen_parkhomenko.parsers.SAXStrategy;
import com.yevhen_parkhomenko.software.Software;
import com.yevhen_parkhomenko.xmlcreator.XMLCreator;
import com.yevhen_parkhomenko.xmltohtml.XML2HTML;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import java.util.Vector;

public class CodingCrimeScene extends JFrame {
    private JButton miracleButton;
    private JScrollPane scrollPane;
    private JLabel filePathLabel;
    private JLabel keyWordLabel;
    private JTextField filePath;
    private JTextField keyWord;
    private List<Software> softwareList;
    private JRadioButton DOMButton;
    private JRadioButton SAXButton;
    private String[] columns = new String[]{};
    private Object[][] information = new Object[][]{{}};
    private String[] columnNames = new String[]{"Title", "Annotation",
            "Version", "Type", "Terms of Use", "Author", "File Path"};
    private DefaultTableModel tableModel;
    private JTable table;

    public CodingCrimeScene() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        initTable();
        DOMButton = new JRadioButton("DOM", false);
        SAXButton = new JRadioButton("SAX", true);
        ButtonGroup group = new ButtonGroup();
        group.add(DOMButton);
        group.add(SAXButton);
        filePathLabel = new JLabel("Enter the XML file path");
        filePath = new JTextField("");
        keyWordLabel = new JLabel("Enter the keyword");
        keyWord = new JTextField("");
        miracleButton = new JButton("Let the miracle happen");
        miracleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearTable();
                Context context;
                if (DOMButton.isSelected()) {
                    context = new Context(new DOMStrategy());
                } else {
                    context = new Context(new SAXStrategy());
                }
                softwareList = context.executeStrategy(new File(filePath.getText()), keyWord.getText());
                addColumns(columnNames);
                for (int i = 0; i < softwareList.size(); i++) {
                    setTable(i);
                }
                for (Software software : softwareList)
                    System.out.println(software.getTitle());
                XMLCreator xmlCreator = new XMLCreator();
                xmlCreator.createXML(softwareList);
                XML2HTML toHTML = new XML2HTML();
                toHTML.convert();
                softwareList.clear();
                JOptionPane.showMessageDialog(null, "The Miracle has happened (probably).");
            }
        });
        scrollPane = new JScrollPane(table);
        Box contents = new Box(BoxLayout.Y_AXIS);
        contents.add(filePathLabel);
        contents.add(filePath);
        contents.add(keyWordLabel);
        contents.add(keyWord);
        contents.add(DOMButton);
        contents.add(SAXButton);
        contents.add(miracleButton);
        contents.add(scrollPane);
        setContentPane(contents);
        setSize(500, 200);
        setVisible(true);
    }

    private void setTable(int index) {
        addRow();
        table.setValueAt(softwareList.get(index).getTitle(), index, 0);
        table.setValueAt(softwareList.get(index).getAnnotation(), index, 1);
        table.setValueAt(softwareList.get(index).getType(), index, 2);
        table.setValueAt(softwareList.get(index).getVersion(), index, 3);
        table.setValueAt(softwareList.get(index).getTermsOfUse(), index, 4);
        table.setValueAt(softwareList.get(index).getAuthor(), index, 5);
        table.setValueAt(softwareList.get(index).getLocation(), index, 6);
    }

    private void clearTable() {
        tableModel.setColumnCount(0);
        tableModel.setRowCount(0);
    }

    private void addRow() {
        CodingCrimeScene.this.tableModel.addRow(new Vector());
    }

    private void addColumns(String[] array) {
        for (int i = 0; i < array.length; i++) {
            String name = array[i];
            CodingCrimeScene.this.tableModel.addColumn(name);
        }
    }

    private void initTable() {
        tableModel = new DefaultTableModel(information, columns);
        table = new JTable(tableModel);
        table.setShowGrid(true);
        table.setGridColor(Color.BLACK);
        table.setColumnSelectionAllowed(false);
        table.setPreferredSize(new Dimension(800, 450));
        table.setRowHeight(30);
        table.setEnabled(false);
    }

    public static void main(String[] args) {
        new CodingCrimeScene();
    }
}