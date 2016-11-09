package com.frw.net.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by fruwei on 2016/11/9.
 */
public class ServerMain {


    static public int port = 1234;


    public static void main(String args[]) {
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                System.out.println("accept...");
                new Thread(new ServerHandler(socket)).start();


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
