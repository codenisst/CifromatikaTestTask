package ru.codenisst;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import ru.codenisst.models.Employee;
import ru.codenisst.models.Organization;
import ru.codenisst.models.OrganizationArray;

import java.io.File;
import java.util.StringJoiner;

public class XMLService {

    public void writeOrganizations(Organization... organizations) {

        OrganizationArray organisationArray = new OrganizationArray(organizations);

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(OrganizationArray.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(organisationArray, new File("organizations.xml"));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public void parseOpenOrganizations(String pathXml) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(OrganizationArray.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            OrganizationArray organizationArray = (OrganizationArray) unmarshaller.unmarshal(new File(pathXml));

            StringJoiner stringJoiner = new StringJoiner("\n\n***\n\n");

            for (Organization org : organizationArray.getOrganizationsArray()) {
                if (org.isStatus()) {
                    StringBuilder output = new StringBuilder();
                    output.append("Название: ").append(org.getName())
                            .append("\nДата создания: ").append(org.getCreationDate())
                            .append("\nСтатус: ").append(org.isStatus() ? "открыта\n\n" : "закрыта\n\n");

                    if (!org.getEmployeesList().isEmpty()) {
                        StringBuilder employeeList = new StringBuilder("Список сотрудников:\n");
                        for (Employee emp : org.getEmployeesList()) {
                            employeeList.append("ФИО: ").append(emp.getFullName())
                                    .append("\nДата рождения: ").append(emp.getDob()).append("\n");
                        }
                        output.append(employeeList);
                    }

                    stringJoiner.add(output);
                }
            }

            System.out.println(stringJoiner);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
