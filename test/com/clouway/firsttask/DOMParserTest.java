package com.clouway.firsttask;

import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by clouway on 16-3-15.
 */
public class DOMParserTest {

    InputStream xmlFile;
    DOMParser domParser;
    List<Employee> expected = Lists.newArrayList();

    @Before
    public void setUp() {
        try {
            xmlFile = new FileInputStream(new File("src/employees.xml"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        domParser = new DOMParser();

        Employee employee = new Employee("Ivan", "Ivanov", 25, "programmer", new Employer("Dimityr Dimitrov", "27.08.1998", "16.01.2002"), new Address("Georgi Izmirliev", 17, "Veliko Tarnovo", "Veliko Tarnovo"));
        Employee employee1 = new Employee("Georgi", "Georgiev", 35, "doctor", new Employer("Stefan Georgiev", "15.11.2003", "10.03.2007"), new Address("Nikola Gabrovski", 41, "Veliko Tarnovo", "Veliko Tarnovo"));
        Employee employee2 = new Employee("Mihail", "Mihov", 28, "police officer", new Employer("Rumyana Bachvarova", "01.01.2015", "01.01.2016"), new Address("Bacho Kiro", 7, "Veliko Tarnovo", "Veliko Tarnovo"));
        expected.add(employee);
        expected.add(employee1);
        expected.add(employee2);

    }

    @Test
    public void happyPath() throws SAXException, IllegalAccessException, IOException, InstantiationException, NoSuchFieldException, ParserConfigurationException, ClassNotFoundException {
        List<Employee> list = domParser.parse(Employee.class, xmlFile);
        assertThat(expected, is(equalTo(list)));
    }
}
