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
        if (args.length == 1) {
            port = Integer.parseInt(args[0]);
        }
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        System.out.println("server start .");
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                System.out.println("accept from ... " + socket.getPort());
                new Thread(new ServerRealDataHandler(socket)).start();


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}