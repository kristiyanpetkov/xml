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
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by clouway on 16-3-14.
 */
public class DOMParser {
    public <T> List<T> parse(Class<T> clazz, InputStream inputStream) throws DOMParseException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = null;
        try {
            documentBuilder = dbFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        Document doc = null;

        try {
            doc = documentBuilder.parse(inputStream);
        } catch (SAXException | IOException e) {
            throw new DOMParseException();
        }

        doc.getDocumentElement().normalize();

        List<T> results = new ArrayList<>();
        NodeList nodeList = doc.getElementsByTagName(clazz.getSimpleName());

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() != Node.TEXT_NODE) {
                T t = parse(clazz, node);
                results.add((t));
            }
        }

        return results;
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

            if (child.getNodeType() != Node.TEXT_NODE) {
                try {
                    String childName = child.getNodeName();
                    Field field = clazz.getDeclaredField(childName);
                    field.setAccessible(true);

                    if (field.getType() == Integer.class) {
                        field.set(instance, new Integer(child.getTextContent()));
                        field.setAccessible(true);

                        continue;
                    }

                    if (field.getType() == String.class) {
                        field.set(instance, child.getTextContent());
                        field.setAccessible(true);

                        continue;
                    }

                    field.set(instance, parse(field.getType(), child));
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        return instance;
    }
}
