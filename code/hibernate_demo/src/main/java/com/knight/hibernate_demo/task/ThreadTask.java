package com.knight.hibernate_demo.task;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class ThreadTask implements InitializingBean {

    private static final String A = "A";
    private static final String B = "B";


    public void deadLock() {
        Thread thread1 = new Thread(() -> {
            synchronized (A) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                synchronized (B) {
                    System.out.println("1");
                }
            }
        });
        Thread thread2 = new Thread(() -> {
            synchronized (B){
                synchronized(A){
                    System.out.print("2");
                }
            }
        });
        thread1.start();
        thread2.start();
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        deadLock();
    }
}
