package br.com.tcp.client.communication;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.Scanner;

public class SendMessage implements Runnable {
    private final Socket socket;
    private final Charset encoding;

    public SendMessage(Socket socket, Charset encoding) {
        this.socket = socket;
        this.encoding = encoding;
    }

    @Override
    public void run() {
        try {
            System.out.println("Insert a message or press Enter to exit");
            Scanner keyboardScanner = new Scanner(System.in);
            OutputStream outputStream = socket.getOutputStream();
            PrintStream printStream = new PrintStream(outputStream, false, encoding);

            while(keyboardScanner.hasNextLine()) {
                String message = keyboardScanner.nextLine();
                if(message.trim().equals("")) {
                    break;
                }
            printStream.println(message);
        }

        keyboardScanner.close();
        outputStream.close();
        printStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
