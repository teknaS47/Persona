package com.mit.persona;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "table_registeredEvents")
public class Table_registeredEvents {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name= "event_id")
    private String event_id;

    @ColumnInfo(name = "event_name")
    private String event_name;

    @ColumnInfo(name = "event_type")
    private String event_type;

    @ColumnInfo(name = "event_group_1")
    private String event_group_1;

    @ColumnInfo(name = "event_group_2")
    private String event_group_2;

    @ColumnInfo(name = "event_group_3")
    private String event_group_3;

    @ColumnInfo(name = "event_group_4")
    private String event_group_4;

    @ColumnInfo(name = "event_group_5")
    private String event_group_5;

    public String getEvent_id() {
        return event_id;
    }

    public void setEvent_id(String event_id) {
        this.event_id = event_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public String getEvent_type() {
        return event_type;
    }

    public void setEvent_type(String event_type) {
        this.event_type = event_type;
    }

    public String getEvent_group_1() {
        return event_group_1;
    }

    public void setEvent_group_1(String event_group_1) {
        this.event_group_1 = event_group_1;
    }

    public String getEvent_group_2() {
        return event_group_2;
    }

    public void setEvent_group_2(String event_group_2) {
        this.event_group_2 = event_group_2;
    }

    public String getEvent_group_3() {
        return event_group_3;
    }

    public void setEvent_group_3(String event_group_3) {
        this.event_group_3 = event_group_3;
    }

    public String getEvent_group_4() {
        return event_group_4;
    }

    public void setEvent_group_4(String event_group_4) {
        this.event_group_4 = event_group_4;
    }

    public String getEvent_group_5() {
        return event_group_5;
    }

    public void setEvent_group_5(String event_group_5) {
        this.event_group_5 = event_group_5;
    }
}

