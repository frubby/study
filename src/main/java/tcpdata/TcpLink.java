package tcpdata;

public interface TcpLink {
    public char[] sendData = new char[4096];
    public char[] receiveData = new char[4096];


    /**
     * 连接服务端
     *
     * @return 成功 true
     */
    public boolean tcpConnect();

    /**
     * 关闭连接
     *
     * @return 成功 true
     */
    public boolean disconnected();

    /**
     * 发送接收数据
     *
     * @param num 需处理字节数
     * @return 实际处理字节数
     */
    public int tcpSend(int num);

    public int tcpReceive(int num);
}
