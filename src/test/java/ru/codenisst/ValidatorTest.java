package ru.codenisst;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;
import ru.codenisst.models.Employee;
import ru.codenisst.models.Organization;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

class ValidatorTest {

    @BeforeAll
    static void testDataPreparation() {
        XMLService xmlService = new XMLService();

        Organization organization1 = new Organization("ООО \"Рога и копыта\"", new Date(), true, new ArrayList<>());
        Organization organization2 = new Organization("ООО \"Моя оборона\"", new Date(), false, new ArrayList<>());
        Organization organization3 = new Organization("ООО \"чень большая организация\"", new Date(), true, new ArrayList<>());

        Employee employee1 = new Employee("Сотрудник 1", new Date(), new ArrayList<>());
        Employee employee2 = new Employee("Сотрудник 2", new Date(), new ArrayList<>());
        Employee employee3 = new Employee("Сотрудник 3", new Date(), new ArrayList<>());

        employee1.getOrganizationsList().add(organization1);
        employee2.getOrganizationsList().add(organization1);
        employee3.getOrganizationsList().add(organization1);

        employee2.getOrganizationsList().add(organization2);
        employee3.getOrganizationsList().add(organization2);

        employee3.getOrganizationsList().add(organization3);

        organization1.getEmployeesList().add(employee1);
        organization1.getEmployeesList().add(employee2);
        organization1.getEmployeesList().add(employee3);

        organization2.getEmployeesList().add(employee2);
        organization2.getEmployeesList().add(employee3);

        organization3.getEmployeesList().add(employee3);

        xmlService.writeOrganizations(organization1, organization2, organization3);
    }

    @AfterAll
    static void deleteFile() {
        File file = new File("organizations.xml");
        file.delete();
    }

    @Test
    void checksIfFileIsCreated() {
        File file = new File("organizations.xml");
        Assertions.assertNotNull(file);
    }

    @Test
    void xmlValidationAgainstXsd() throws SAXException, IOException {
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = schemaFactory.newSchema(new File("organizations.xsd"));
        javax.xml.validation.Validator validator = schema.newValidator();
        validator.validate(new StreamSource(new File("organizations.xml")));
    }
}