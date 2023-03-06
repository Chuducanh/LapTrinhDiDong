package com.example.giuaky2022.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "records")
public class Record {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo
    @SerializedName("title")
    private String title;
    @ColumnInfo
    @SerializedName("desc")
    private String desc;
    @ColumnInfo
    @SerializedName("timeStamp")
    private String timestamp;
    @ColumnInfo
    @SerializedName("lat")
    private String lat;
    @ColumnInfo
    @SerializedName("lng")
    private String lng;
    @ColumnInfo
    @SerializedName("addr")
    private String addr;
    @ColumnInfo
    @SerializedName("e")
    private String e;
    @ColumnInfo
    @SerializedName("zip")
    private String zip;

    public Record(String title, String desc, String timestamp, String lat, String lng, String addr, String e, String zip) {
        this.title = title;
        this.desc = desc;
        this.timestamp = timestamp;
        this.lat = lat;
        this.lng = lng;
        this.addr = addr;
        this.e = e;
        this.zip = zip;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
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

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
}
