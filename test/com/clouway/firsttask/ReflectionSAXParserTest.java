package com.clouway.firsttask;

import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by clouway on 16-3-22.
 */
public class ReflectionSAXParserTest {

   InputStream xmlFile;
    ReflectionSAXParser reflectionSAXParser;
    List<Employee> expected;

    @Before
    public void setUp() {
        try {
            xmlFile = new FileInputStream(new File(getClass().getResource("employees.xml").getPath()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        reflectionSAXParser = new ReflectionSAXParser();

        Employee employee = new Employee("Ivan", "Ivanov", 25, "programmer", new Employer("Dimityr Dimitrov", "27.08.1998", "16.01.2002"), new Address("Georgi Izmirliev", 17, "Veliko Tarnovo", "Veliko Tarnovo"));
        Employee employee1 = new Employee("Georgi", "Georgiev", 35, "doctor", new Employer("Stefan Georgiev", "15.11.2003", "10.03.2007"), new Address("Nikola Gabrovski", 41, "Veliko Tarnovo", "Veliko Tarnovo"));
        Employee employee2 = new Employee("Mihail", "Mihov", 28, "police officer", new Employer("Rumyana Bachvarova", "01.01.2015", "01.01.2016"), new Address("Bacho Kiro", 7, "Veliko Tarnovo", "Veliko Tarnovo"));
        expected = Lists.newArrayList(employee,employee1,employee2);
    }

    @Test
    public void happyPath() throws ParserConfigurationException, IllegalAccessException, InstantiationException, SAXException, IOException {
        List<Employee> list = reflectionSAXParser.parse(Employee.class,xmlFile);
        assertThat(expected,is(equalTo(list)));
    }
}
