package com.yqc.influx;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.BatchPoints;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by yangqc on 2017/7/27
 */
public class InfluxInsert {
    public static void main(String[] args) {
        InfluxDB influxDB = InfluxDBFactory.connect("http://localhost:8086", "root", "123");
        String dbName = "mydb";
//        influxDB.createDatabase(dbName);
        influxDB.setDatabase(dbName);
        influxDB.enableBatch(2000, 100, TimeUnit.NANOSECONDS);
        BatchPoints batchPoints = BatchPoints
                .database(dbName)
                .tag("async", "true")
                .consistency(InfluxDB.ConsistencyLevel.ALL)
                .build();
        Map<String, String> map = new HashMap<>();
        map.put("disk", "90");
        map.put("kk", "90%");
        map.put("async","false");
        influxDB.write(map.toString());
        influxDB.close();
    }
}
