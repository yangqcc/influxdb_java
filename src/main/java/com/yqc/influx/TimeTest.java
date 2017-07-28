package com.yqc.influx;

import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * Created by yangqc on 2017/7/27
 */
public class TimeTest {

    public static void main(String[] args) {
        System.out.println(System.nanoTime());
        System.out.println(System.currentTimeMillis());
        System.out.println(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().getNano());
    }
}
