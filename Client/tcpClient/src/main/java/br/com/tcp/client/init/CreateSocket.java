package br.com.tcp.client.init;

import java.io.IOException;
import java.net.Socket;

public class CreateSocket {

    private final String host;
    private final Integer port;

    public CreateSocket(ConnectionParameters connectionParameters) {
        this.host = connectionParameters.getHost();
        this.port = connectionParameters.getPort();
    }

    public Socket newSocket() {
        try {
            Socket socket = new Socket(host, port);
            System.out.println("Connection established successfully");
            return socket;
        } catch (IOException e) {
            System.out.println("Connection could not be established");
            throw new RuntimeException(e);
        }
    }
}
