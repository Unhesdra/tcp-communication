package br.com.tcp.client.encoding;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class ISO88591 implements CommunicationEncoding {

    @Override
    public Charset getEncodingType() {
        return StandardCharsets.ISO_8859_1;
    }
}
