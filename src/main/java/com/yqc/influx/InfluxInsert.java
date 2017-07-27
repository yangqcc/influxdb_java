package com.yqc.influx;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.BatchPoints;
import org.influxdb.dto.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by yangqc on 2017/7/27
 */
public class InfluxInsert {
    public static void main(String[] args) {
        InfluxDB influxDB = InfluxDBFactory.connect("http://localhost:8086", "root", "123");
        String dbName = "aTimeSeries";
        influxDB.setDatabase(dbName);
        influxDB.enableBatch(2000, 100, TimeUnit.MILLISECONDS);
        BatchPoints batchPoints = BatchPoints
                .database(dbName)
                .tag("async", "true")
                .consistency(InfluxDB.ConsistencyLevel.ALL)
                .build();
        for (int i = 0; i < 100000; i++) {
            batchPoints.point(Point.measurement("cpu")
                    .time(System.nanoTime(), TimeUnit.NANOSECONDS)
                    .addField("idle", 90L)
                    .addField("user", 9L)
                    .addField("system", 1L)
                    .addField("gpu",21)
                    .addField("memory","90%")
                    .addField("disk","89.8%")
                    .build());
        }
        long t1 = System.currentTimeMillis();
        influxDB.write(batchPoints);
        long t2 = System.currentTimeMillis();
        System.out.println("时间为：" + (t2 - t1));
        influxDB.close();
    }
}
