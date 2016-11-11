package com.frw.net.server;

import java.io.*;
import java.net.Socket;
import java.util.Calendar;
import java.util.Date;

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
            DataInputStream input = new DataInputStream(socket.getInputStream());
//            String clientInputStr = input.readUTF();//这里要注意和客户端输出流的写方法对应,否则会抛 EOFException

//            System.out.println("reveive :" + clientInputStr);


            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            out.writeUTF("now: ");

            out.writeUTF("server time : " + new Date());
            out.flush();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            out.writeUTF("finish");
            out.flush();
            out.close();
            input.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (Exception e) {
                    socket = null;
                    System.out.println("exception : " + e.getMessage());
                }
            }
        }
    }
}
