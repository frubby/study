package tcpdata;

public class Data {
	public float Ia;
	public float Ib;
	public float Ic;
	public float imbalance;//三相不平衡度
	
	public int num;//有效开关个数
	public SwitchData[] sdata = new SwitchData[8];
}
