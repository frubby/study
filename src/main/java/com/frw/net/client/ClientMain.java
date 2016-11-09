package com.frw.net.client;

import java.io.*;
import java.net.Socket;

/**
 * Created by fruwei on 2016/11/9.
 */
public class ClientMain {


    public static void main(String args[]) {
        Socket socket = null;

        while (true) {
            try {
                socket = new Socket("127.0.0.1", 1234);
                DataInputStream input = new DataInputStream(socket.getInputStream());
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                System.out.print("enter: \t");
                String str = new BufferedReader(new InputStreamReader(System.in)).readLine();
                out.writeUTF(str);

                String ret = input.readUTF();
                System.out.println("ret: " + ret);
                if ("OK".equals(ret)) {
                    System.out.println("close client");
                    Thread.sleep(500);
                    break;
                }

                out.close();
                input.close();


            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if (socket != null) {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        socket = null;
                        System.out.println("exception:" + e.getMessage());
                    }
                }
            }
        }
    }


}
