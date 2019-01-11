package com.mit.persona;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "table_version")
public class Table_DbVersionCheck {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "version")
    private Double version;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getVersion() {
        return version;
    }

    public void setVersion(Double version) {
        this.version = version;
    }
}
