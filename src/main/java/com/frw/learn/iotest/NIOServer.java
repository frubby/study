package com.frw.learn.iotest;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NIOServer {

	private Selector selector;
	private ExecutorService tp=Executors.newCachedThreadPool();

	private void startServer() throws IOException{
		selector =SelectorProvider.provider().openSelector();
		ServerSocketChannel ssc=ServerSocketChannel.open();
		InetSocketAddress isa=new InetSocketAddress(InetAddress.getLocalHost(),8000);

		ssc.socket().bind(isa);

		SelectionKey acceptKey=ssc.register(selector,SelectionKey.OP_ACCEPT);

		for(;;){
			selector.select();
			Set<SelectionKey> readyKeys=selector.selectedKeys();
			Iterator<SelectionKey> it=readyKeys.iterator();
			long start=0;
			while(it.hasNext()){
				SelectionKey sk= it.next();
				it.remove();
				if(sk.isAcceptable()){
					doAccept(sk);
				}

			}


		}




	}




	private void doAccept(SelectionKey sk){
		ServerSocketChannel serverchannel=(ServerSocketChannel) sk.channel();
		SocketChannel clientChannel;

		try {
			clientChannel=serverchannel.accept();

			clientChannel.configureBlocking(false);
			SelectionKey clientKey=clientChannel.register(selector, SelectionKey.OP_READ);
			clientKey.attach(new EchoClient());
			System.out.println(clientChannel.socket().getRemoteSocketAddress()+" connnected accept .");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	class EchoClient {
		private LinkedList<ByteBuffer> outq;
		public EchoClient(){
			outq=new LinkedList<ByteBuffer>();
		}
		public LinkedList<ByteBuffer> getOutputQueue(){
			return outq;
		}
		public void enqueue(ByteBuffer bb){
			outq.addFirst(bb);
		}
	}

}
