package com.yqc.influx.ThreadTest;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.BatchPoints;
import org.influxdb.dto.Point;

import java.util.concurrent.TimeUnit;

/**
 * Created by yangqc on 2017/7/28
 */
public class ApiTask implements Runnable {

    private static final InfluxDB influxDB = InfluxDBFactory.connect("http://localhost:8086", "root", "123");

    private final String dataBaseName;

    private int i = 0;

    public ApiTask(String dataBaseName) {
        this.dataBaseName = dataBaseName;
    }

    @Override
    public void run() {
        BatchPoints batchPoints = BatchPoints
                .database(dataBaseName)
                .tag("type", "api")
                .consistency(InfluxDB.ConsistencyLevel.ALL)
                .build();
        batchPoints.point(Point.measurement("cpu")
                .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                .addField("idle", 90L)
                .addField("user", 9L)
                .addField("system", 1L)
                .build());
        influxDB.write(batchPoints);
        System.out.println(Thread.currentThread() + "**" + (++i));
    }
}
