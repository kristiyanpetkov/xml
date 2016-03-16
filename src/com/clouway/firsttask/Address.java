package com.clouway.firsttask;

/**
 * Created by clouway on 16-3-8.
 */
public class Address {
    private String street;
    private Integer streetNo;
    private String section;
    private String city;

    public Address() {
    }

    public Address(String street, Integer streetNo, String section, String city) {
        this.street = street;
        this.streetNo = streetNo;
        this.section = section;
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public Integer getStreetNo() {
        return streetNo;
    }

    public String getSection() {
        return section;
    }

    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", streetNo=" + streetNo +
                ", section='" + section + '\'' +
                ", city='" + city + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        if (street != null ? !street.equals(address.street) : address.street != null) return false;
        if (streetNo != null ? !streetNo.equals(address.streetNo) : address.streetNo != null) return false;
        if (section != null ? !section.equals(address.section) : address.section != null) return false;
        return !(city != null ? !city.equals(address.city) : address.city != null);

    }

    @Override
    public int hashCode() {
        int result = street != null ? street.hashCode() : 0;
        result = 31 * result + (streetNo != null ? streetNo.hashCode() : 0);
        result = 31 * result + (section != null ? section.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        return result;
    }
}
