package com.yqc.influx;

import com.yqc.influx.pojo.Cpu;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.influxdb.impl.InfluxDBResultMapper;

import java.util.List;

/**
 * Created by yangqc on 2017/7/27
 */
public class InfluxSearch {
    public static void main(String[] args) {
        InfluxDB influxDB = InfluxDBFactory.connect("http://localhost:8086", "root", "123");
        String dbName = "aTimeSeries";
        QueryResult queryResult = influxDB.query(new Query("SELECT * FROM cpu", dbName));

        InfluxDBResultMapper resultMapper = new InfluxDBResultMapper(); // thread-safe - can be reused
        List<Cpu> cpuList = resultMapper.toPOJO(queryResult, Cpu.class);
        System.out.println(cpuList.size());
       /* for(Cpu cpu:cpuList) {
            System.out.println(cpu);
        }*/
    }
}
