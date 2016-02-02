package com.frw.learn.thread;

public class WaitBlockTest {

	class Store {
		private volatile int num = 0;

		public void add() {
			num++;
		}

		public void minus() {
			num--;
		}

		public int get() {
			return num;
		}
	}

	public static void main(String args[]) {
		final Object lock = new Object();
		new Thread() {
			public void run() {
				synchronized (lock) {
					try {
						lock.wait();
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					for(int i=0;i<100;i++){

						try {
							Thread.sleep(10000);
							System.out.println("thread A run "+i);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					System.out.println("thread finish");
				}
			}
		}.start();


		new Thread() {
			public void run() {
				synchronized (lock) {
					try {
						Thread.sleep(10000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("thread finish");
					lock.notify();
				}
			}
		}.start();


	}

}
