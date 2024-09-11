package com.poortoys.examples;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SleepDemo extends Thread {
	
	private ReentrantLock lock = new ReentrantLock();
	private Logger logger = LoggerFactory.getLogger(SleepDemo.class);
	
	@Override
	public void run() {
		//sleep不能解锁,wait可以解锁，但是只能作用在同步代码块/同步方法
		//根据什么同步,那就得用什么对象调用wait()方法，否则报错 这段代码中根据this同步的，那就得用this.wait()来等待
		try {
			synchronized (this) {
				lock.tryLock(1, TimeUnit.SECONDS);
				logger.info("你好");
				this.sleep(1000);
				if(lock.isHeldByCurrentThread()) {
					logger.info("当前线程被锁住了");
					this.wait(1000);
					if(lock.isHeldByCurrentThread())
						logger.info("当前线程锁被解除了");
				}
			}
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
