package com.pattern.chain;

public class ThrottlingMiddleWare extends MiddleWare {
    private final int requestPerMinute;
    private int request;
    private long currentTime;

    public ThrottlingMiddleWare(int requestPerMinute){
        this.requestPerMinute=requestPerMinute;
        this.currentTime=System.currentTimeMillis();
    }

    @Override
    public boolean check(String email, String password) {
        if (System.currentTimeMillis()>currentTime+60_000){
            request=0;
            currentTime=System.currentTimeMillis();
        }
        request++;
        if (request>requestPerMinute){
            System.out.println("Request limit exceeded!");
            Thread.currentThread().stop();
        }
        return checkNext(email,password);
    }
}
