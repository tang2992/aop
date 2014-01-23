package com.coffee.core.aop;

import java.lang.management.ManagementFactory;

public class ProxyTest1 {

    private String name;

    public void process1() {
        System.out.println("process1");
    }

    public String process2(String id, int age) {
        System.out.println(id);
        System.out.println(age);
        return "ok";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        System.out.println(start);

        String className = Thread.currentThread().getStackTrace()[1].getClassName();
        System.out.println(className);
        
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        System.out.println(methodName);

        String processName = ManagementFactory.getRuntimeMXBean().getName();
        System.out.println(processName);

        String threadName = Thread.currentThread().getName();
        System.out.println(threadName);

        long end = System.currentTimeMillis();
        System.out.println(end);
    }
}
