package com.clouway.firsttask;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by clouway on 16-3-14.
 */
public class DOMParser {

    public <T> List<T> parse(Class<T> clazz, File file) {

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = null;

        try {
            documentBuilder = dbFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        Document doc = null;
        try {
            doc = documentBuilder.parse(file);
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }
        doc.getDocumentElement().normalize();

        List<T> employees = new ArrayList<>();
        NodeList nodeList = doc.getElementsByTagName(clazz.getSimpleName());
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE && node.hasChildNodes()) {
                T t = parse(clazz, node);
                employees.add((t));
            }
        }
        return employees;
    }

    private <T> T parse(Class<T> clazz, Node parrent) {

        T instance = null;

        try {
            instance = clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        NodeList nodeChilds = parrent.getChildNodes();

        for (int j = 0; j < nodeChilds.getLength(); j++) {
            Node child = nodeChilds.item(j);
            if (isEmbedded(child)) {
                String childName = child.getNodeName();
                Field field = null;
                try {
                    field = clazz.getDeclaredField(childName);
                    field.setAccessible(true);
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                }
                try {
                    field.set(instance, parse(field.getType(), child));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            if (child.getNodeType() != Node.TEXT_NODE) {
                Field field = null;
                try {
                    field = clazz.getDeclaredField(child.getNodeName());
                    field.setAccessible(true);
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                }
                if (field.getType() == Integer.class) {
                    try {
                        field.set(instance, new Integer(child.getTextContent()));
                        field.setAccessible(true);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
                if (field.getType() == String.class) {
                    try {
                        field.set(instance, child.getTextContent());
                        field.setAccessible(true);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return instance;
    }

    private boolean isEmbedded(Node node) {
        int length = node.getChildNodes().getLength();
        return node.getNodeType() != Node.TEXT_NODE && length > 1;
    }
}
