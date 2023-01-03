package br.com.tcp.client.init;

import br.com.tcp.client.configuration.SaxHandler;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ConnectionParameters {

    private String host;
    private Integer port;
    private String encoding;
    private static final String FILENAME = "config.xml";

    public ConnectionParameters() {
        checkIfXmlFileExists();
        getParametersFromXml();
    }

    public String getHost() {
        return host;
    }

    public Integer getPort() {
        return port;
    }

    public String getEncoding() {
        return encoding;
    }

    private void getParametersFromXml() {
        SAXParserFactory saxFactory = SAXParserFactory.newInstance();

        try {
            SAXParser saxParser = saxFactory.newSAXParser();
            SaxHandler saxHandler = new SaxHandler();
            saxParser.parse(FILENAME, saxHandler);

            host = saxHandler.getHost();
            port = saxHandler.getPort();
            encoding = saxHandler.getEncoding();
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    private void checkIfXmlFileExists() {
        File xmlFile = new File(FILENAME);
        if(xmlFile.length() == 0) {
            try {
                FileWriter xmlWriter = new FileWriter(xmlFile, true);
                xmlWriter.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
                xmlWriter.append("<Configuration>\n");
                xmlWriter.append("\t<host>localhost</host>\n");
                xmlWriter.append("\t<port>55555</port>\n");
                xmlWriter.append("\t<encoding>utf-8</encoding>\n");
                xmlWriter.append("</Configuration>");
                xmlWriter.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
