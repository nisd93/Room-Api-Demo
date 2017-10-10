package com.nisd93.roomapiexample.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.nisd93.roomapiexample.dao.UserDao;
import com.nisd93.roomapiexample.entity.User;

/**
 * Created by Nisarg on 9/29/2017.
 */

@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public static final String DB_NAME = "app_db";

    public abstract UserDao userDao();


}
