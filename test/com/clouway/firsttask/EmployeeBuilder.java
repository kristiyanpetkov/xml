package com.clouway.firsttask;

/**
 * Created by clouway on 16-3-29.
 */
public class EmployeeBuilder {
    private String firstname = "Peter";
    private String lastname = "lName";
    private Integer age = 0;
    private String position = "pPosition";
    private Employer employer = new Employer("Dimityr Dimitrov", "27.08.1998", "16.01.2002");
    private Address address = new Address("Georgi Izmirliev", 17, "Veliko Tarnovo", "Veliko Tarnovo");

    public static EmployeeBuilder createEmployee() {
        return new EmployeeBuilder();
    }

    public EmployeeBuilder firstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public EmployeeBuilder lastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public EmployeeBuilder age(Integer age) {
        this.age = age;
        return this;
    }

    public EmployeeBuilder position(String position) {
        this.position = position;
        return this;
    }

    public EmployeeBuilder employer(Employer employer) {
        this.employer = employer;
        return this;
    }

    public EmployeeBuilder address(Address address) {
        this.address = address;
        return this;
    }

    public Employee build() {
        return new Employee(firstname, lastname, age, position, employer, address);
    }


}
