package com.frw.learn.lock;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class MyLock {

	private static class Sync extends AbstractQueuedSynchronizer{

		
		
		@Override
		protected boolean tryAcquire(int arg) {
			if(this.compareAndSetState(0, 1)){
				this.setExclusiveOwnerThread(Thread.currentThread());
				return true;
			}
//			else if(this.getExclusiveOwnerThread()==Thread.currentThread()){
//				return true;
//			}
			return false;
		}

		@Override
		protected boolean tryRelease(int arg) {
			if(this.getState()==0)
				throw new IllegalMonitorStateException();
			setExclusiveOwnerThread(null);
			setState(0);
			return true;
		}

		@Override
		protected int tryAcquireShared(int arg) {
			return super.tryAcquireShared(arg);
		}

		@Override
		protected boolean tryReleaseShared(int arg) {
			return super.tryReleaseShared(arg);
		}
	

		
	}
	
	private final Sync sync=new Sync();
	public void lock(){
		sync.acquire(1);
	}
	public void unLock(){
		
		sync.release(1)  ;  
	}
	
	public   static void main(String args[]){
		final MyLock mylock=new MyLock();
		new Thread(){
			public void run(){
				System.out.println("thread 1 start");
				mylock.lock();
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("thread 1 step 1 ");
				System.out.println("thread 1 lock 2 ");
				mylock.lock();
				System.out.println("thread 1 unlock 2 ");
				mylock.unLock();
				mylock.unLock();
			}
		}.start();
	
		new Thread(){
			public void run(){
				System.out.println("thread 2 start");
				mylock.lock();
//				try {
//					Thread.sleep(2000);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
				System.out.println("thread 2 end");
			}
		}.start();
		
		
	}
	
	
	
	
	
}
