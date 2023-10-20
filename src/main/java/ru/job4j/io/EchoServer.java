package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    String msg = in.readLine().split(" ")[1];
                    if ("/?msg=Hello".equals(msg)) {
                        out.write("Hello".getBytes());
                    }
                    if ("/?msg=Exit".equals(msg)) {
                        out.write("Завершить работу сервера.".getBytes(Charset.forName("WINDOWS-1251")));
                        server.close();
                    }
                    if (!"/?msg=Hello".equals(msg) && !"/?msg=Exit".equals(msg)) {
                        out.write("What".getBytes());
                    }
                    out.flush();
                }
            }
        }
    }
}