package com.example.sihuan.contactdemo;

/**
 * CallLogInfo
 * Created by SiHuan on 2017/4/7.
 */

public class CallLogInfo {
    private String number;
    private long date;
    private String duration;
    private String type;
    private String unRead;

    public CallLogInfo() {
    }

    public CallLogInfo(String number, long date, String duration, String type, String unRead) {
        this.number = number;
        this.date = date;
        this.duration = duration;
        this.type = type;
        this.unRead = unRead;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUnRead() {
        return unRead;
    }

    public void setUnRead(String unRead) {
        this.unRead = unRead;
    }
}
