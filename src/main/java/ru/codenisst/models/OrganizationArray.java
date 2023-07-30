package ru.codenisst.models;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "organizations")
public class OrganizationArray {

    private Organization[] organizationsArray;

    public OrganizationArray() {
    }

    public OrganizationArray(Organization[] organizationsArray) {
        this.organizationsArray = organizationsArray;
    }

    @XmlElement(name = "organization")
    public Organization[] getOrganizationsArray() {
        return organizationsArray;
    }

    public void setOrganizationsArray(Organization[] organizationsArray) {
        this.organizationsArray = organizationsArray;
    }
}
