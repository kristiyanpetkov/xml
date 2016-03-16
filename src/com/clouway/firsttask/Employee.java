package com.clouway.firsttask;

/**
 * Created by clouway on 16-3-8.
 */
public class Employee {
    public String firstname;
    public String lastname;
    public Integer age;
    public String position;
    public Employer employer;
    public Address address;

    public Employee(String firstname, String lastname, Integer age, String position, Employer employer, Address address) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.position = position;
        this.employer = employer;
        this.address = address;
    }

    public Employee() {

    }

    @Override
    public String toString() {
        return "Employee{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", age=" + age +
                ", position='" + position + '\'' +
                ", employer=" + employer +
                ", address=" + address +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        if (firstname != null ? !firstname.equals(employee.firstname) : employee.firstname != null) return false;
        if (lastname != null ? !lastname.equals(employee.lastname) : employee.lastname != null) return false;
        if (age != null ? !age.equals(employee.age) : employee.age != null) return false;
        if (position != null ? !position.equals(employee.position) : employee.position != null) return false;
        if (employer != null ? !employer.equals(employee.employer) : employee.employer != null) return false;
        return !(address != null ? !address.equals(employee.address) : employee.address != null);

    }

    @Override
    public int hashCode() {
        int result = firstname != null ? firstname.hashCode() : 0;
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (age != null ? age.hashCode() : 0);
        result = 31 * result + (position != null ? position.hashCode() : 0);
        result = 31 * result + (employer != null ? employer.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }
}
