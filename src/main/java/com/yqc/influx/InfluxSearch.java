package com.yqc.influx;

import com.yqc.influx.pojo.Cpu;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.influxdb.impl.InfluxDBResultMapper;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by yangqc on 2017/7/27
 */
public class InfluxSearch {
    public static void main(String[] args) {
        InfluxDB influxDB = InfluxDBFactory.connect("http://localhost:8086", "root", "123");
        String dbName = "mydb";
        QueryResult queryResult = influxDB.query(new Query("SELECT * FROM cpu limit 10", dbName), TimeUnit.NANOSECONDS);
        for (QueryResult.Result result : queryResult.getResults()) {
            for (QueryResult.Series series : result.getSeries()) {
                System.out.println(series);
            }
        }

        InfluxDBResultMapper resultMapper = new InfluxDBResultMapper(); // thread-safe - can be reused
        List<Cpu> cpuList = resultMapper.toPOJO(queryResult, Cpu.class);
        System.out.println(cpuList.size());
        for (Cpu cpu : cpuList) {
            System.out.println(cpu);
        }
    }
}
