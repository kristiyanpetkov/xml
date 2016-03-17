package com.clouway.firsttask;

import javax.xml.parsers.SAXParserFactory;
import java.io.InputStream;
import java.util.List;

/**
 * Created by clouway on 16-3-17.
 */
public class SaxParserXML {
    public <T> List<T> parse(Class<T> clazz, InputStream inputStream){
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();

    }

}
