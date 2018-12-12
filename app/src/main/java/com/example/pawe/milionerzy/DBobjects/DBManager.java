package com.example.pawe.milionerzy.DBobjects;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.pawe.milionerzy.models.Record;

import java.util.ArrayList;

public class DBManager {

    private SQLiteDatabase db;
    private Context context;
    private DaoRecord daoRecord;

    public DBManager(Context context)
    {
        this.context = context;
        OpenHelper sqLiteOpenHelper = new OpenHelper(context);
        this.db = sqLiteOpenHelper.getWritableDatabase();
        daoRecord = new DaoRecord(db);
    }

    public Record getRecord(int id)
    {
        return daoRecord.findById(id);
    }

    public ArrayList<Record> getRecords()
    {
        return daoRecord.findall();
    }

    public int getCountOfRecords()
    {
        return daoRecord.getCount();
    }

    public void insertRecord(Record record)
    {
        daoRecord.insert(record);
    }

    public void updateRecord(Record record)
    {
        daoRecord.update(record);
    }
}
