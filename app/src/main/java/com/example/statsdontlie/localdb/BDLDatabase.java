package com.example.statsdontlie.localdb;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.sql.NBAPlayerQueries;
import com.example.statsdontlie.Database;
import com.example.statsdontlie.app.DatabaseImplKt;
import com.squareup.sqldelight.android.AndroidSqliteDriver;
import com.squareup.sqldelight.db.SqlDriver;

import static kotlin.jvm.JvmClassMappingKt.getKotlinClass;

public final class BDLDatabase {
    private static BDLDatabase instance;
    private static Database database;
    private static NBAPlayerQueries nbaPlayerQueries;

    private BDLDatabase(@NonNull Context context) {
        database = getDatabase(context);
        nbaPlayerQueries = database.getNBAPlayerQueries();
    }

    public static BDLDatabase getInstance(@NonNull Context context) {
        if (instance == null) {
            instance = new BDLDatabase(context);
        }
        return instance;
    }

    private Database getDatabase(Context context) {
        SqlDriver sqlDriver =
          new AndroidSqliteDriver(Database.Companion.getSchema(), context, "BDL.db");
        if (database == null) {
            database = DatabaseImplKt.newInstance(getKotlinClass(Database.class), sqlDriver);
        }
        return database;
    }

    public final NBAPlayerQueries getNBAPlayerQueries() {
        return nbaPlayerQueries;
    }

}
