package tcpdata;

import java.io.*;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * Created by yoho on 2016/11/15.
 */

public class TcpLinkImpl implements TcpLink {
    Socket socket = null;
    InputStream input;
    OutputStream out;

    @Override
    public boolean tcpConnect() {
        try {
            socket = new Socket("172.16.6.29", 1234);
        } catch (IOException e) {
            return false;
        }
        if (socket.isConnected()) {
            try {
                input = socket.getInputStream();
                out = socket.getOutputStream();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }
        return false;

    }

    @Override
    public boolean disconnected() {
        if (socket == null)
            return true;
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public int tcpSend(int num) {

        return 0;
    }

    @Override
    public int tcpReceive(int num) {
        byte[] read = new byte[num];
        try {
            int dataNum = input.read(read);
            String str = new String(read, Charset.forName("GBK"));
            System.out.println("impl read data : " + str);
            return dataNum;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return 0;
    }
}
