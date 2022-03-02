package com.positioning.gps.entity;

import java.util.Date;

public class JobCardEntity {
    private String time; // 时间
    private String deveui;  // 工卡ID
    private Double latitude; // 纬度
    private Double longitude; // 经度
    private Integer speed; // 速度
    private Integer azimuth; // 方位角
    private Double altitude; // 海拔
    private Integer stepCount; // 步数
    private Integer battery; // 电量

    public JobCardEntity() {
    }

    public JobCardEntity(String time, String deveui, Double latitude, Double longitude, Integer speed, Integer azimuth, Double altitude, Integer stepCount, Integer battery) {
        this.time = time;
        this.deveui = deveui;
        this.latitude = latitude;
        this.longitude = longitude;
        this.speed = speed;
        this.azimuth = azimuth;
        this.altitude = altitude;
        this.stepCount = stepCount;
        this.battery = battery;
    }

    @Override
    public String toString() {
        return "JobCardEntity{" +
                "time='" + time + '\'' +
                ", deveui='" + deveui + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", speed=" + speed +
                ", azimuth=" + azimuth +
                ", altitude=" + altitude +
                ", stepCount=" + stepCount +
                ", battery=" + battery +
                '}';
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDeveui() {
        return deveui;
    }

    public void setDeveui(String deveui) {
        this.deveui = deveui;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public Integer getAzimuth() {
        return azimuth;
    }

    public void setAzimuth(Integer azimuth) {
        this.azimuth = azimuth;
    }

    public Double getAltitude() {
        return altitude;
    }

    public void setAltitude(Double altitude) {
        this.altitude = altitude;
    }

    public Integer getStepCount() {
        return stepCount;
    }

    public void setStepCount(Integer stepCount) {
        this.stepCount = stepCount;
    }

    public Integer getBattery() {
        return battery;
    }

    public void setBattery(Integer battery) {
        this.battery = battery;
    }
}
