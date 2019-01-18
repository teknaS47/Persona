package com.mit.persona;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface MyDao {

    @Insert
    public void addEvent(Events event);

    @Query("select * from events where event_category = :event_category")
    public List<Events> getEvents(String event_category);

    @Query("select * from events")
    public List<Events> listEvents();

    @Query("DELETE FROM events")
    public void deleteAllEvents();

    @Delete
    public void deleteEvent(Events event);

    @Insert
    public void addMessage(Table_Messages message);

    @Query("select * from table_messages ORDER BY id DESC")
    public List<Table_Messages> getMessages();

    @Delete
    public void deleteMessage(Table_Messages message);

    @Query("select * from table_session")
    public List<Table_Sessions> getsession();

    @Insert
    public void addSession(Table_Sessions session);

    @Query("DELETE FROM table_session")
    public void clearSessionTable();

    @Query("DELETE FROM table_registeredEvents")
    public void clearRegisteredEventsTable();

    @Query("select * from table_registeredEvents")
    public List<Table_registeredEvents> getRegisteredEvents();


    @Query("UPDATE table_version SET 'version' = :updated_version WHERE id=1")
    public void updateVersion(Double updated_version);

    @Query("select * from table_version")
    public List<Table_DbVersionCheck> getVersion();

    @Insert
    public void addVersion(Table_DbVersionCheck version);


}
