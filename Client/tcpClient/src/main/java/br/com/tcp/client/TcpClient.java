package br.com.tcp.client;

import br.com.tcp.client.encoding.EncodingSelector;
import br.com.tcp.client.init.ConnectionParameters;
import br.com.tcp.client.init.CreateSocket;
import br.com.tcp.client.communication.ReceiveMessage;
import br.com.tcp.client.communication.SendMessage;

import java.io.IOException;
import java.net.Socket;
import java.nio.charset.Charset;

public class TcpClient {

    public static void main(String[] args) {

        ConnectionParameters connectionParameters = new ConnectionParameters();

        CreateSocket createSocket = new CreateSocket(connectionParameters);
        Socket socket = createSocket.newSocket();

        EncodingSelector selector = new EncodingSelector(connectionParameters.getEncoding());
        Charset encoding = selector.getEncoding();

        Thread receiveThread = new Thread(new ReceiveMessage(socket, encoding));
        Thread sendThread = new Thread(new SendMessage(socket, encoding));

        sendThread.start();
        receiveThread.start();

        try {
            sendThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        try {
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
