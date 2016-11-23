package com.frw.net.server;

import com.alibaba.fastjson.JSON;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by fruwei on 2016/11/9.
 */
public class ServerMain {


    static public int port = 1234;


    public static void main(String args[]) throws IOException {
        if (args.length == 1) {
            port = Integer.parseInt(args[0]);
        }

        File f = new File("./switchdata.txt");
        if (f.exists()) {
            System.out.println("read data from  switchdata.txt");
            BufferedReader bf = new BufferedReader(new FileReader(f));
            String line;
            StringBuilder sb;
            int num = 0;
            Data data = new Data();
            while ((line = bf.readLine()) != null) {
                String split[] = line.trim().split(",");
                long address = Long.parseLong(split[0], 16);
                if (num == 0) {
                    data.address = address;
                    data.name = split[1];
                } else {
                    SwitchData switchData = new SwitchData();
                    switchData.address = address;
                    switchData.name = split[1];
                    data.sdata.add(switchData);
                }
                num++;
            }
            data.num = num - 1;
            ServerRealDataHandler.strData = JSON.toJSONString(data);
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
