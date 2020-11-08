package com.akzaki.speedrecords.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "record_2")

public class Record{


    @PrimaryKey(
            autoGenerate = true
    )
    public final int id;
    //@ColumnInfo(name = "distance")
    public final double distance;
    //@ColumnInfo(name = "second")
    public final double second;

    public Record(int id, double distance, double second) {
        this.id = id;
        this.second = second;
        this.distance = distance;
    }
}
