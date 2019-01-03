package com.bcil.roomdatabase.database_holder;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.bcil.roomdatabase.dao.TaskDao;
import com.bcil.roomdatabase.entity.Task;

@Database(entities = {Task.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TaskDao taskDao();
}
