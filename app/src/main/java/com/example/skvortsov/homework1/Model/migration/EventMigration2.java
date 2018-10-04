package com.example.skvortsov.homework1.Model.migration;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.migration.Migration;
import android.support.annotation.NonNull;

public class EventMigration2 extends Migration {
    /**
     * Creates a new migration between {@code startVersion} and {@code endVersion}.
     *
     * @param startVersion The start version of the database.
     * @param endVersion   The end version of the database after this migration is applied.
     */
    public EventMigration2(int startVersion, int endVersion) {
        super(startVersion, endVersion);
    }

    public EventMigration2() {
        super(1,2);
    }

    @Override
    public void migrate(@NonNull SupportSQLiteDatabase database) {
       database.execSQL("ALTER TABLE event ADD COLUMN start_date TEXT");
       database.execSQL("ALTER TABLE event ADD COLUMN end_date TEXT");
    }
}
