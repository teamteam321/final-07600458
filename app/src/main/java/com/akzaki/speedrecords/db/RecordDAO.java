package com.akzaki.speedrecords.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import com.akzaki.speedrecords.model.Record;

@Dao
public interface RecordDAO {

    @Query("SELECT * FROM record_2")
    Record [] getAllRecord();

    @Query("SELECT * FROM record_2 WHERE ID = :id")
    Record getRecordById(int id);

    @Insert
    void addRecord(Record... u);

    @Delete
    void deleteRecord(Record u);
}
