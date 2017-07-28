package com.yqc.influx.ThreadTest;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by yangqc on 2017/7/28
 */
public class ExecuteMain {

    public static void main(String[] args) throws InterruptedException {
        String dataBaseName = "mydb";
        ScheduledExecutorService service = Executors.newScheduledThreadPool(3);
        // 第二个参数为首次执行的延时时间，第三个参数为定时执行的间隔时间
        service.scheduleAtFixedRate(new IotTask(dataBaseName), 1, 2, TimeUnit.MILLISECONDS);
        service.scheduleAtFixedRate(new ApiTask(dataBaseName), 2, 2, TimeUnit.MILLISECONDS);
        service.scheduleAtFixedRate(new ITTask(dataBaseName), 3, 2, TimeUnit.MILLISECONDS);
        Thread.sleep(1000 * 60 * 60);
        service.shutdown();
    }
}
