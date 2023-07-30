package ru.codenisst.models;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@XmlType(propOrder = {"name", "creationDate", "status", "employeesList"})
public class Organization {

    private String name;
    private Date creationDate;
    private boolean status;
    private List<Employee> employeesList;
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", new Locale("ru"));

    public Organization() {
    }

    public Organization(String name, Date creationDate, boolean status, List<Employee> employeesList) {
        this.name = name;
        this.creationDate = creationDate;
        this.status = status;
        this.employeesList = employeesList;
    }

    @XmlElement(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name = "creationDate")
    public String getCreationDate() {
        return simpleDateFormat.format(creationDate);
    }

    public void setCreationDate(String creationDate) throws ParseException {
        this.creationDate = simpleDateFormat.parse(creationDate);
    }

    @XmlElement(name = "status")
    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @XmlElementWrapper(name = "employees")
    @XmlElement(name = "employee")
    public List<Employee> getEmployeesList() {
        return employeesList;
    }

    public void setEmployeesList(List<Employee> employeesList) {
        this.employeesList = employeesList;
    }
}
