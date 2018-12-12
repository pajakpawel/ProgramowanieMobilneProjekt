package com.example.pawe.milionerzy.DBobjects;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class OpenHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "Milionerzy.db";
    private SQLiteDatabase db;
    private DaoRecord daoRecord;

    public OpenHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
        daoRecord = new DaoRecord(db);
        RecordTable.createTable(db);
        RecordTable.insertIntoTable(daoRecord);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        RecordTable.deleteTable(db);
        onCreate(db);
    }
}
