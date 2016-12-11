package com.frw.net.server;

import com.alibaba.fastjson.JSON;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

/**
 * Created by fruwei on 2016/11/9.
 */
public class ServerRealDataHandler implements Runnable {

    public Socket socket;


    public static String strData = "{\"Ia\":0,\"Ib\":0,\"Ic\":0,\"address\":600000,\"imbalance\":0,\"name\":\"小区A\",\"num\":7," +
            "\"sdata\":[{\"Ia\":0,\"Ib\":0,\"Ic\":0,\"address\":600001,\"load\":0,\"name\":\"开关B\",\"num\":0},{\"Ia\":0,\"Ib\":0,\"Ic\":0,\"address\":600002,\"load\":0,\"name\":\"开关C\",\"num\":0},{\"Ia\":0,\"Ib\":0,\"Ic\":0,\"address\":600003,\"load\":0,\"name\":\"开关D\",\"num\":0},{\"Ia\":0,\"Ib\":0,\"Ic\":0,\"address\":600004,\"load\":0,\"name\":\"开关E\",\"num\":0},{\"Ia\":0,\"Ib\":0,\"Ic\":0,\"address\":600005,\"load\":0,\"name\":\"开关F\",\"num\":0},{\"Ia\":0,\"Ib\":0,\"Ic\":0,\"address\":600006,\"load\":0,\"name\":\"开关G\",\"num\":0},{\"Ia\":0,\"Ib\":0,\"Ic\":0,\"address\":600007,\"load\":0,\"name\":\"开关H\",\"num\":0}]}\n";


    public ServerRealDataHandler(Socket socket) {
        this.socket = socket;
    }


    public static void generateData(Data area) {
        Random random = new Random();
        area.Ia = (random.nextInt(100) / 50.0f);
        area.Ib = (random.nextInt(100) / 50.0f);
        area.Ic = (random.nextInt(100) / 50.0f);
        area.imbalance = (float) (random.nextInt(360));
        for (int i = 0; i < area.sdata.size(); i++) {
            SwitchData device =area.sdata.get(i);
            device.Ia = (random.nextInt(100) / 50.0f);
            device.Ib = (random.nextInt(100) / 50.0f);
            device.Ic = (random.nextInt(100) / 50.0f);


            device.load = (random.nextInt(100) / 50.0f);
            device.num = (random.nextInt(20));
            device.switchState = "无效";
            device.int_switchState = random.nextInt(5);
            device.int_loadType = random.nextInt(5);

            device.loadType = "重要";
        }
    }


    public int createData(byte[] chars) {
        Data data = JSON.parseObject(strData, Data.class);


        generateData(data);
        int pos = 0;
        chars[pos++] = 0x68;
        chars[pos++] = 0x01;
        chars[pos++] = 0x00;
        chars[pos++] = 0x00;
        chars[pos++] = 0x00;
        chars[pos++] = 0x00;
        chars[pos++] = 0x00;
        chars[pos++] = 0x68;
        chars[pos++] = (byte) 0x81;
        int markSizePos=pos++;  //mark size pos

        byte temp[] = writeLongData(data.address, 6);
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

        Random rd=new Random();
        int realNum=0;
        for (int i = 0; i < data.sdata.size(); i++) {

            if(rd.nextInt(100)>50)  // test 数据缺失
                continue;
            realNum++;
            SwitchData switchData = data.sdata.get(i);
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
            temp = writeLongData(switchData.num, 4);
            System.arraycopy(temp, 0, chars, pos, 4);
            pos += 4;
            temp = writeLongData(switchData.int_switchState, 2);
            System.arraycopy(temp, 0, chars, pos, 2);
            pos += 2;
            temp = writeLongData(switchData.int_loadType, 2);
            System.arraycopy(temp, 0, chars, pos, 2);
            pos += 2;

        }

        chars[markSizePos] = (byte) (26 * (realNum+1)) ; //length

        int sum = 0;
        for (int j = 0; j < pos; j++) {
            sum += chars[j];
        }
        chars[pos++] = (byte) (sum & 0xFF);

        chars[pos++] = 0x16;

        System.out.println("\nsend data size ok  : " + pos);
        for (int i = 0; i < 10; i++) {
            System.out.print(" " + Integer.toHexString(chars[i] & 0xFF));

        }
        for (int i = 10; i < 26 * (realNum+1); i++) {
            if ((i - 10) % 26 == 0)
                System.out.println("");
            System.out.print(" " + Integer.toHexString(chars[i] & 0xFF));

        }


        return pos;
    }


//    public static void main(String args[]) {
//        Data data = JSON.parseObject(strData, Data.class);
//
//        generateData(data);
//
//        System.out.println(JSON.toJSONString(data, true));
//
//        byte[] chars = new byte[4096];
//
//        int pos = 0;
//        chars[pos++] = 0x68;
//        chars[pos++] = 0x01;
//        chars[pos++] = 0x00;
//        chars[pos++] = 0x00;
//        chars[pos++] = 0x00;
//        chars[pos++] = 0x00;
//        chars[pos++] = 0x00;
//        chars[pos++] = 0x68;
//        chars[pos++] = (byte) 0x81;
//        int num = 1 + data.num;
//        chars[pos++] = (byte) (26 * num); //length
//
//        byte temp[] = writeLongData(data.address, 6);
//        System.arraycopy(temp, 0, chars, pos, 6);
//        pos += 6;
//
//        temp = writeLongData((long) (data.Ia * 1000), 4);
//        System.arraycopy(temp, 0, chars, pos, 4);
//        pos += 4;
//        temp = writeLongData((long) (data.Ib * 1000), 4);
//        System.arraycopy(temp, 0, chars, pos, 4);
//        pos += 4;
//        temp = writeLongData((long) (data.Ic * 1000), 4);
//        System.arraycopy(temp, 0, chars, pos, 4);
//        pos += 4;
//        temp = writeLongData((long) 0, 4);
//        System.arraycopy(temp, 0, chars, pos, 4);
//        pos += 4;
//        temp = writeLongData((long) 0, 4);
//        System.arraycopy(temp, 0, chars, pos, 4);
//        pos += 4;
//
//        for (int i = 0; i < data.num; i++) {
//            SwitchData switchData = data.sdata[i];
//            temp = writeLongData(switchData.address, 6);
//            System.arraycopy(temp, 0, chars, pos, 6);
//            pos += 6;
//
//            temp = writeLongData((long) (switchData.Ia * 1000), 4);
//            System.arraycopy(temp, 0, chars, pos, 4);
//            pos += 4;
//            temp = writeLongData((long) (switchData.Ib * 1000), 4);
//            System.arraycopy(temp, 0, chars, pos, 4);
//            pos += 4;
//            temp = writeLongData((long) (switchData.Ic * 1000), 4);
//            System.arraycopy(temp, 0, chars, pos, 4);
//            pos += 4;
//            temp = writeLongData((long) switchData.num, 4);
//            System.arraycopy(temp, 0, chars, pos, 4);
//            pos += 4;
//            temp = writeLongData(switchData.int_switchState, 2);
//            System.arraycopy(temp, 0, chars, pos, 2);
//            pos += 2;
//            temp = writeLongData((long) 0, 2);
//            System.arraycopy(temp, 0, chars, pos, 2);
//            pos += 2;
//
//        }
//
//        int sum = 0;
//        for (int j = 0; j < pos; j++) {
//            sum += chars[j];
//        }
//        chars[pos++] = (byte) (sum & 0xFF);
//
//        chars[pos++] = 0x16;
//
//        System.out.println("\npos size : " + pos);
//        for (int i = 10; i < 26 * num; i++) {
//            if ((i - 10) % 26 == 0)
//                System.out.println("");
//            System.out.print(" " + Integer.toHexString(chars[i] & 0xff));
//
//        }
//
//
////        char ch = 0x01;
////
////        char test1[] = {0x01, 0x02, 0x00, 0x00};
////        System.out.print(getLongData(4, test1));
////
//        System.out.println("\n write test");
////
//        writeLongData(600001, 6);
//    }


    public static byte[] writeLongData(long val, int len) {

        int n = 0;
        byte[] chs = new byte[len];
        long mark = 0x00FF;

        for (n = 0; n < len; n++) {

            chs[n] = (byte) (val >> (8 * n) & mark);
            System.out.print(" " + Integer.toHexString(chs[n] & 0xff));
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

            OutputStream outputStream = socket.getOutputStream();
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            while (true) {
                byte sends[] = new byte[4096];
                int num = createData(sends);
                int i = 0;
                outputStream.write(sends, 0, num);

                out.flush();
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

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
