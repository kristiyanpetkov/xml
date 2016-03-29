package com.clouway.firsttask;

import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by clouway on 16-3-22.
 */
public class ReflectionSAXParserTest {

    private InputStream xmlFile;
    private ReflectionSAXParser reflectionSAXParser = new ReflectionSAXParser();
    private List<Employee> expected;

    @Test
    public void happyPath() throws ParserConfigurationException, IllegalAccessException, InstantiationException, SAXException, IOException {
        String xmlFile =
                "<employees>" +
                "    <Employee>" +
                "        <firstname>Ivan</firstname>" +
                "        <lastname>Ivanov</lastname>" +
                "        <age>25</age>" +
                "        <position>programmer</position>" +
                "            <employer>" +
                "                <name>Dimityr Dimitrov</name>" +
                "                <startDate>27.08.1998</startDate>" +
                "                <endDate>16.01.2002</endDate>" +
                "            </employer>" +
                "            <address>" +
                "                <street>Georgi Izmirliev</street>" +
                "                <streetNo>17</streetNo>" +
                "                <section>Veliko Tarnovo</section>" +
                "                <city>Veliko Tarnovo</city>" +
                "            </address>" +
                "     </Employee>" +
                "</employees>";

        InputStream in = new ByteArrayInputStream(xmlFile.getBytes(StandardCharsets.UTF_8));

        Employee want = new Employee("Ivan", "Ivanov", 25, "programmer", new Employer("Dimityr Dimitrov", "27.08.1998", "16.01.2002"), new Address("Georgi Izmirliev", 17, "Veliko Tarnovo", "Veliko Tarnovo"));
        expected = Lists.newArrayList(want);
        List<Employee> list = reflectionSAXParser.parse(Employee.class, in);
        assertThat(expected, is(equalTo(list)));
    }

    @Test
    public void employeeNumberTest(){
        Employee employee = EmployeeBuilder.createEmployee().build();
        Employee employee5 = EmployeeBuilder.createEmployee().firstname("Ivan").build();
        Employee employee2 = EmployeeBuilder.createEmployee().build();
        Employee employee3 = EmployeeBuilder.createEmployee().build();
        Employee employee4 = EmployeeBuilder.createEmployee().build();
        List<Employee> emp = Lists.newArrayList(employee,employee2,employee3,employee4,employee5);
        assertThat(emp.size(),is(5));
        assertThat("Ivan",is(employee5.getFirstname()));
    }
}
