package com.yqc.influx.pojo;

import lombok.Getter;
import lombok.Setter;
import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;

import java.time.Instant;

/**
 * Created by yangqc on 2017/7/27
 */
@Measurement(name = "cpu")
public class Cpu {

    @Getter
    @Setter
    @Column(name = "time")
    private Instant time;

    @Getter
    @Setter
    @Column(name = "host", tag = true)
    private String hostname;

    @Getter
    @Setter
    @Column(name = "region", tag = true)
    private String region;

    @Getter
    @Setter
    @Column(name = "idle")
    private Double idle;

    @Getter
    @Setter
    @Column(name = "happydevop")
    private Boolean happydevop;

    @Getter
    @Setter
    @Column(name = "uptimesecs")
    private Long uptimeSecs;

    @Override
    public String toString() {
        return "Cpu{" +
                "time=" + time +
                ", hostname='" + hostname + '\'' +
                ", region='" + region + '\'' +
                ", idle=" + idle +
                ", happydevop=" + happydevop +
                ", uptimeSecs=" + uptimeSecs +
                '}';
    }
}
