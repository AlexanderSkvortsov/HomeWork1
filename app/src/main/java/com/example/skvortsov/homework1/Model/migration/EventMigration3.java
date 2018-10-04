package com.example.skvortsov.homework1.Model.migration;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.migration.Migration;
import android.support.annotation.NonNull;

public class EventMigration3 extends Migration {
    /**
     * Creates a new migration between {@code startVersion} and {@code endVersion}.
     *
     * @param startVersion The start version of the database.
     * @param endVersion   The end version of the database after this migration is applied.
     */
    public EventMigration3(int startVersion, int endVersion) {
        super(startVersion, endVersion);
    }

    public EventMigration3() {
        super(2,3);
    }

    @Override
    public void migrate(@NonNull SupportSQLiteDatabase database) {
        database.execSQL("ALTER TABLE event ADD COLUMN remote_id TEXT");
        database.execSQL("CREATE UNIQUE INDEX index_event_remote_id ON event(remote_id)");
    }
    }
