package br.com.tcp.client.configuration;

import org.xml.sax.helpers.DefaultHandler;

public class SaxHandler extends DefaultHandler {

    private StringBuilder currentValue = new StringBuilder();
    private String host;
    private Integer port;
    private String encoding;

    @Override
    public void endElement(
            String uri,
            String localName,
            String qName) {

        if (qName.equalsIgnoreCase("host")) {
            host = currentValue.toString().trim();
        }

        if(qName.equalsIgnoreCase("port")) {
            port = Integer.parseInt(currentValue.toString().trim());
        }

        if(qName.equalsIgnoreCase("encoding")) {
            encoding = currentValue.toString().trim();
        }

        currentValue.setLength(0);
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        currentValue.append(ch, start, length);
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
}
