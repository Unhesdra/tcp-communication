package br.com.tcp.client.encoding;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class UTF8 implements CommunicationEncoding {

    @Override
    public Charset getEncodingType() {
        return StandardCharsets.UTF_8;
    }
}
