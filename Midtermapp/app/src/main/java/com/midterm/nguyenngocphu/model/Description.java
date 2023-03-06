package com.midterm.nguyenngocphu.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Description implements Serializable {
    @SerializedName("lat")
    private float lat;
    @SerializedName("lng")
    private float lng;
    @SerializedName("desc")
    private String desc;
    @SerializedName("zip")
    private String zip;
    @SerializedName("title")
    private String title;
    @SerializedName("timeStamp")
    private String timeStamp;
    @SerializedName("twp")
    private String twp;
    @SerializedName("addr")
    private String addr;
    @SerializedName("e")
    private String e;

    public Description(float lat, float lng, String desc, String zip, String title, String timeStamp, String twp, String addr, String e) {
        this.lat = lat;
        this.lng = lng;
        this.desc = desc;
        this.zip = zip;
        this.title = title;
        this.timeStamp = timeStamp;
        this.twp = twp;
        this.addr = addr;
        this.e = e;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLng() {
        return lng;
    }

    public void setLng(float lng) {
        this.lng = lng;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getTwp() {
        return twp;
    }

    public void setTwp(String twp) {
        this.twp = twp;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getE() {
        return e;
    }

    public void setE(String e) {
        this.e = e;
    }
}
