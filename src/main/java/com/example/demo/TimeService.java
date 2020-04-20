package com.example.demo;

import lombok.Data;


@Data
public class TimeService {
    private static long currentTime;

    static {
        new Thread(() -> {
            try {
                setTime(System.currentTimeMillis());
                Thread.sleep(5);
            } catch (Exception ex) {
            }
        }).start();
    }

    private static void setTime(long time) {
        currentTime = time;
    }

    public static long getTime() {
        return currentTime;
    }
}
