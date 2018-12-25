package com.mit.persona;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface MyDao {

    @Insert
    public void addEvent(Events event);

    @Query("select * from events")
    public List<Events> getEvents();

    @Delete
    public void deleteEvent(Events event);

}
