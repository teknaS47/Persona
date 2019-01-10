package com.mit.persona;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.RoomWarnings;

import java.util.List;

@Dao
public interface MyDao {

    @Insert
    public void addEvent(Events event);

    @Query("select * from events where event_category = :event_category")
    public List<Events> getEvents(String event_category);

    @Delete
    public void deleteEvent(Events event);

    @Insert
    public void addMessage(Table_Messages message);

    @Query("select * from table_messages ORDER BY id DESC")
    public List<Table_Messages> getMessages();

    @Delete
    public void deleteMessage(Table_Messages message);

    @Insert
    public void addSession(Table_Sessions session);

    @Delete
    public void deleteSession(Table_Sessions session);

}
