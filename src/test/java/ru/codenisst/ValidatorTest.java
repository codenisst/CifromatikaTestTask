package ru.codenisst;

import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import java.io.File;
import java.io.IOException;

class Validator {

    @Test
    void xmlValidationAgainstXsd() throws SAXException, IOException {
        String xmlFilePath = "organizations.xml";
        String xsdFilePath = "organizations.xsd";

        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = schemaFactory.newSchema(new File(xsdFilePath));
        javax.xml.validation.Validator validator = schema.newValidator();
        validator.validate(new StreamSource(new File(xmlFilePath)));
    }
}