package tcpdata;

public class Protocol {
    private TcpLink link;
    private boolean linkState;
    private int linkStateNum;

    private int sendPoint;

    private Data data;


    public Protocol(TcpLink linkPro) {
        // TODO Auto-generated constructor stub
        link = linkPro;
        linkState = false;
        linkStateNum = 0;

        sendPoint = 0;

        data = new Data();
    }

    public void dataProcess() {
        stateProcess();
        dataReceiveProcess();
        dataSendProcess();
    }

    public void stateProcess() {
        ++linkStateNum;

        if (linkStateNum > 10)
            linkState = false;
        else
            linkState = true;
    }

    public void dataSendProcess() {
        int num = makeData();

        if (num > 0)
            sendPoint = link.tcpSend(sendPoint + num);
    }

    public void dataReceiveProcess() {
        int num = link.tcpReceive(4096);

        if (num >= 12) {
            System.out.println("dataReceiveProcess : " + num);
        } else
            return;

        linkStateNum = 0;
    }

    public int makeData() {

        return 8;
    }

    public boolean getLinkState() {
        return linkState;
    }

    public void setLinkStateNum(int linkStateNum) {
        this.linkStateNum = linkStateNum;
    }

    public Data getData() {
        return data;
    }
}
