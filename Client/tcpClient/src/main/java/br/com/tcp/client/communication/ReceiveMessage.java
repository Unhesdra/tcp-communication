package br.com.tcp.client.communication;

import java.io.*;
import java.net.Socket;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.Scanner;

public class ReceiveMessage implements Runnable {
    private final Socket socket;
    private final Charset encoding;

    public ReceiveMessage(Socket socket, Charset encoding) {
        this.socket = socket;
        this.encoding = encoding;
    }

    @Override
    public void run() {
        try {
            Scanner serverResponse = new Scanner(socket.getInputStream(), encoding);
            FileWriter fileWriter = new FileWriter("test.txt", true);
            PrintWriter printWriter  = new PrintWriter(fileWriter);

            while(serverResponse.hasNextLine()) {
                String responseLine = serverResponse.nextLine();
                System.out.println(responseLine);
                printWriter.println(LocalDateTime.now() + ": "+ responseLine);
            }

            serverResponse.close();
            printWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
