package ru.codenisst.models;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlTransient;
import jakarta.xml.bind.annotation.XmlType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@XmlType(propOrder = {"fullName", "dob"})
public class Employee {

    private String fullName;
    private Date dob;
    private List<Organization> organizationsList;
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", new Locale("ru"));

    public Employee() {
    }

    public Employee(String fullName, Date dob, List<Organization> organizationsList) {
        this.fullName = fullName;
        this.dob = dob;
        this.organizationsList = organizationsList;
    }

    @XmlElement(name = "name")
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @XmlElement(name = "dateOfBirth")
    public String getDob() {
        return simpleDateFormat.format(dob);
    }

    public void setDob(String dob) throws ParseException {
        this.dob = simpleDateFormat.parse(dob);
    }

    @XmlTransient
    public List<Organization> getOrganizationsList() {
        return organizationsList;
    }

    public void setOrganizationsList(List<Organization> organizationsList) {
        this.organizationsList = organizationsList;
    }
}
