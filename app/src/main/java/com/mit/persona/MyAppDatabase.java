package com.mit.persona;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;


@Database(entities = {Events.class, Table_Messages.class}, version = 1)

public abstract class MyAppDatabase extends RoomDatabase {

    public abstract MyDao myDao();



}
