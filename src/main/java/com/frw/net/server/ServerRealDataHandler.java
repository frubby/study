package com.frw.net.server;

import com.alibaba.fastjson.JSON;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Date;
import java.util.Random;

/**
 * Created by fruwei on 2016/11/9.
 */
public class ServerRealDataHandler implements Runnable {

    public Socket socket;


    public static String strData = "{\"Ia\":0,\"Ib\":0,\"Ic\":0,\"address\":600000,\"imbalance\":0,\"name\":\"小区A\",\"num\":7," +
            "\"sdata\":[{\"Ia\":0,\"Ib\":0,\"Ic\":0,\"address\":600001,\"load\":0,\"name\":\"开关B\",\"num\":0},{\"Ia\":0,\"Ib\":0,\"Ic\":0,\"address\":600002,\"load\":0,\"name\":\"开关C\",\"num\":0},{\"Ia\":0,\"Ib\":0,\"Ic\":0,\"address\":600003,\"load\":0,\"name\":\"开关D\",\"num\":0},{\"Ia\":0,\"Ib\":0,\"Ic\":0,\"address\":600004,\"load\":0,\"name\":\"开关E\",\"num\":0},{\"Ia\":0,\"Ib\":0,\"Ic\":0,\"address\":600005,\"load\":0,\"name\":\"开关F\",\"num\":0},{\"Ia\":0,\"Ib\":0,\"Ic\":0,\"address\":600006,\"load\":0,\"name\":\"开关G\",\"num\":0},{\"Ia\":0,\"Ib\":0,\"Ic\":0,\"address\":600007,\"load\":0,\"name\":\"开关H\",\"num\":0},null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null]}\n";


    public ServerRealDataHandler(Socket socket) {
        this.socket = socket;
    }


    public static void generateData(Data area) {
        Random random = new Random();
        area.Ia = (random.nextInt(100) / 50.0f);
        area.Ib = (random.nextInt(100) / 50.0f);
        area.Ic = (random.nextInt(100) / 50.0f);
        area.imbalance = (float) (random.nextInt(360));
        SwitchData switchData[] = area.sdata;
        for (int i = 0; i < area.num; i++) {
            SwitchData device = area.sdata[i];
            device.Ia = (random.nextInt(100) / 50.0f);
            device.Ib = (random.nextInt(100) / 50.0f);
            device.Ic = (random.nextInt(100) / 50.0f);


            device.load = (random.nextInt(100) / 50.0f);
            device.num = (random.nextInt(20));
            device.switchState = "无效";

            device.loadType = "重要";
        }
    }


    public static void main(String args[]) {
        Data data = JSON.parseObject(strData, Data.class);

        generateData(data);

        System.out.println(JSON.toJSONString(data, true));

        char[] chars = new char[4096];

        int pos = 0;
        chars[pos++] = 0x68;
        chars[pos++] = 0x01;
        chars[pos++] = 0x00;
        chars[pos++] = 0x00;
        chars[pos++] = 0x00;
        chars[pos++] = 0x00;
        chars[pos++] = 0x00;
        chars[pos++] = 0x68;
        chars[pos++] = 0x81;
        int num = 1 + data.num;
        chars[pos++] = (char) (26 * num); //length

        char temp[] = writeLongData(data.address, 6);
        System.arraycopy(temp, 0, chars, pos, 6);
        pos += 6;

        temp = writeLongData((long) (data.Ia * 1000), 4);
        System.arraycopy(temp, 0, chars, pos, 4);
        pos += 4;
        temp = writeLongData((long) (data.Ib * 1000), 4);
        System.arraycopy(temp, 0, chars, pos, 4);
        pos += 4;
        temp = writeLongData((long) (data.Ic * 1000), 4);
        System.arraycopy(temp, 0, chars, pos, 4);
        pos += 4;
        temp = writeLongData((long) 0, 4);
        System.arraycopy(temp, 0, chars, pos, 4);
        pos += 4;
        temp = writeLongData((long) 0, 4);
        System.arraycopy(temp, 0, chars, pos, 4);
        pos += 4;

        for (int i = 0; i < data.num; i++) {
            SwitchData switchData = data.sdata[i];
            temp = writeLongData(switchData.address, 6);
            System.arraycopy(temp, 0, chars, pos, 6);
            pos += 6;

            temp = writeLongData((long) (switchData.Ia * 1000), 4);
            System.arraycopy(temp, 0, chars, pos, 4);
            pos += 4;
            temp = writeLongData((long) (switchData.Ib * 1000), 4);
            System.arraycopy(temp, 0, chars, pos, 4);
            pos += 4;
            temp = writeLongData((long) (switchData.Ic * 1000), 4);
            System.arraycopy(temp, 0, chars, pos, 4);
            pos += 4;
            temp = writeLongData((long) 0, 4);
            System.arraycopy(temp, 0, chars, pos, 4);
            pos += 4;
            temp = writeLongData((long) 0, 4);
            System.arraycopy(temp, 0, chars, pos, 4);
            pos += 4;

        }


        System.out.println("\npos size : " + pos);
        for (int i = 10; i < 26 * num; i++) {
            if ((i - 10) % 26 == 0)
                System.out.println("");
            System.out.print(" " + Integer.toHexString((int) chars[i]));

        }


//        char ch = 0x01;
//
//        char test1[] = {0x01, 0x02, 0x00, 0x00};
//        System.out.print(getLongData(4, test1));
//
        System.out.println("\n write test");
//
        writeLongData(600001, 6);
    }


    public static char[] writeLongData(long val, int len) {

        int n = 0;
        char[] chs = new char[len];
        long mark = 0x00FF;

        for (n = 0; n < len; n++) {
            chs[n] = (char) (val >> (8 * n) & mark);
            System.out.print(" " + Integer.toHexString((int) chs[n]));
//            System.out.print(" "+Integer.toHexString((int) mark));

        }

        return chs;
    }

    public static long getLongData(int len, char[] chars) {
        long sum = 0;
        int n = -1;
        while (++n < len) {
            sum |= ((long) chars[n]) << (8 * n);
            System.out.println(sum);
        }

        return sum;
    }

    public void run() {
        try {
            Data data = JSON.parseObject(strData, Data.class);

            generateData(data);

            DataInputStream input = new DataInputStream(socket.getInputStream());
//            String clientInputStr = input.readUTF();//这里要注意和客户端输出流的写方法对应,否则会抛 EOFException

//            System.out.println("reveive :" + clientInputStr);


            DataOutputStream out = new DataOutputStream(socket.getOutputStream());


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
