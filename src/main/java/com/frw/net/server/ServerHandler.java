package com.frw.net.server;

import java.io.*;
import java.net.Socket;

/**
 * Created by fruwei on 2016/11/9.
 */
public class ServerHandler implements Runnable {

    public Socket socket;


    public ServerHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream());

            while (true) {

                String str = in.readLine();

                System.out.println(str);

                out.println("has receive.");

                out.flush();

                if (str.equals("end"))

                    break;

            }

            socket.close();

        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
