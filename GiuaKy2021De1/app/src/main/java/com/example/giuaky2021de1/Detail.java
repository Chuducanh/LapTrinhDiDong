package com.example.giuaky2021de1;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "details")
public class Detail {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo
    private String input;
    @ColumnInfo
    private String action;
    @ColumnInfo
    private String output;

    public Detail(String input, String action, String output) {
        this.input = input;
        this.action = action;
        this.output = output;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }
}
