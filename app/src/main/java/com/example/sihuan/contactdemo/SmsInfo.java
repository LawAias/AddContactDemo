package com.example.sihuan.contactdemo;

/**
 * SmsInfo
 * Created by SiHuan on 2017/4/7.
 */

public class SmsInfo {
    private String body;
    private String date;
    private int read;
    private String address;
    private int type;
    private String service_center;

    public SmsInfo() {

    }

    public SmsInfo(String body, String date, int read, String address, int type, String service_center) {
        this.body = body;
        this.date = date;
        this.read = read;
        this.address = address;
        this.type = type;
        this.service_center = service_center;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getRead() {
        return read;
    }

    public void setRead(int read) {
        this.read = read;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getService_center() {
        return service_center;
    }

    public void setService_center(String service_center) {
        this.service_center = service_center;
    }
}
