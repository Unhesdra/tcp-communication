package br.com.tcp.client.encoding;

import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;

public class EncodingSelector {

    private final String encoding;
    private static final String PACKAGE = "br.com.tcp.client.encoding.";

    public EncodingSelector(String encoding) {
        this.encoding = encoding;
    }

    public Charset getEncoding() {
        try {
            String className = encoding.replaceAll("[^a-zA-Z0-9]", "");
            Class classDefinition = Class.forName(PACKAGE + className.toUpperCase());
            CommunicationEncoding encodingClass =
                    (CommunicationEncoding) classDefinition
                    .getDeclaredConstructor()
                    .newInstance();

            return  encodingClass.getEncodingType();
        } catch (ClassNotFoundException e) {
            CommunicationEncoding utf8 = new UTF8();
            return utf8.getEncodingType();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
