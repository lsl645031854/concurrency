package com.mmall.concurrency.juc;

import java.util.concurrent.locks.ReentrantLock;

class Ticket {
    private int num = 60;

    private ReentrantLock lock = new ReentrantLock();

    public void sale() {

        try {
            lock.lock();
            if (num > 0) {
                System.out.println(Thread.currentThread().getName()
                        + "\t卖出第" + num-- + "张票，剩余票数：" + num);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}

/**
 * @Author: lsl
 * @Description: 买票问题
 * @Date: Created on 16:25 2020/8/9
 */
public class SellTicketDemo {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();

        for (int i = 0; i < 40; i++) {
            new Thread(() -> {
                ticket.sale();

            }, "AAA").start();

            new Thread(() -> {
                ticket.sale();
            }, "BBB").start();

            new Thread(() -> {
                ticket.sale();
            }, "CCC").start();
        }


    }
}
