package com.mit.persona;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "table_version")
public class Table_DbVersionCheck {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "version")
    private Number version;

}
