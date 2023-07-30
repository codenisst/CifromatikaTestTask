package ru.codenisst;

import ru.codenisst.models.Employee;
import ru.codenisst.models.Organization;

import java.util.ArrayList;
import java.util.Date;

public class Main {

    public static void main(String[] args) {

        XMLService xmlService = new XMLService();

        // подготовка тестовых данных
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

        // сериализация вышеописанных тестовых данных в файл organizations.xml
        xmlService.writeOrganizations(organization1, organization2, organization3);
        // десериализация и вывод в консоль из указанного xml файла организаций со статусом "Открыта" (true)
        xmlService.parseOpenOrganizations("organizations.xml");
    }
}
