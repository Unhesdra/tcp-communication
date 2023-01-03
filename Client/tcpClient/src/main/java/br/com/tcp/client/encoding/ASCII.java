package br.com.tcp.client.encoding;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class ASCII implements CommunicationEncoding {

    @Override
    public Charset getEncodingType() {
        return StandardCharsets.US_ASCII;
    }
}