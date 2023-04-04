package com.prospring.ch5.method_interceptor;

public class WorkerBean {
    public void doSomeWork(int times) {
        for (int i = 0; i < times; i++) {
            work();
        }
    }

    private void work() {
        System.out.print("");
    }
}
