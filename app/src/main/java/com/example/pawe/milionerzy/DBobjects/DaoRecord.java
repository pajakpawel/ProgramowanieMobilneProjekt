package com.example.pawe.milionerzy.DBobjects;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.pawe.milionerzy.models.Record;

import java.util.ArrayList;

public class DaoRecord implements IDaoRecord {

    private SQLiteDatabase db;

    public DaoRecord(SQLiteDatabase db)
    {
        this.db = db;
    }

    @Override
    public Record findById(int id)
    {
        String query = "SELECT * FROM " + RecordTable.TABLE_NAME + " WHERE " +
                RecordTable.RecordColumns.ID + " = " + id + ";";

        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        if(c.getCount() > 1)
        {
            throw new RuntimeException("TOO MANY ROWS EXCEPTION");
        }
        else if(c.getCount() <= 0)
        {
            throw new RuntimeException("NO DATA FOUND EXCEPTION");
        }

        int id_record = c.getInt(c.getColumnIndex(RecordTable.RecordColumns.ID));
        String question = c.getString(c.getColumnIndex(RecordTable.RecordColumns.QUESTION_));
        String answer1 = c.getString(c.getColumnIndex(RecordTable.RecordColumns.ANSWER1));
        String answer2 = c.getString(c.getColumnIndex(RecordTable.RecordColumns.ANSWER2));
        String answer3 = c.getString(c.getColumnIndex(RecordTable.RecordColumns.ANSWER3));
        String answer4 = c.getString(c.getColumnIndex(RecordTable.RecordColumns.ANSWER4));
        String correct = c.getString(c.getColumnIndex(RecordTable.RecordColumns.CORRECTANSWER));

        Record record = new Record(id_record, question, answer1, answer2, answer3, answer4, correct);

        c.close();
        return record;
    }

    @Override
    public ArrayList<Record> findall()
    {
        ArrayList<Record> records = new ArrayList<>();
        String query = "SELECT * FROM " + RecordTable.TABLE_NAME + ";";

        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        if(c.getCount() <= 0)
        {
            records.clear();
            c.close();
            return records;
        }
        do
        {
            int id = c.getInt(c.getColumnIndex(RecordTable.RecordColumns.ID));
            String question = c.getString(c.getColumnIndex(RecordTable.RecordColumns.QUESTION_));
            String answer1 = c.getString(c.getColumnIndex(RecordTable.RecordColumns.ANSWER1));
            String answer2 = c.getString(c.getColumnIndex(RecordTable.RecordColumns.ANSWER2));
            String answer3 = c.getString(c.getColumnIndex(RecordTable.RecordColumns.ANSWER3));
            String answer4 = c.getString(c.getColumnIndex(RecordTable.RecordColumns.ANSWER4));
            String correct = c.getString(c.getColumnIndex(RecordTable.RecordColumns.CORRECTANSWER));

            Record record = new Record(id, question, answer1, answer2, answer3, answer4, correct);
            records.add(record);
        }while (c.moveToNext());

        c.close();
        return records;
    }

    @Override
    public int getCount()
    {
        String query = "SELECT count(*) AS RESULT FROM " + RecordTable.TABLE_NAME + ";";

        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        int count = c.getInt(c.getColumnIndex("RESULT"));

        c.close();
        return count;
    }

    @Override
    public void insert(Record record)
    {
        ContentValues values = new ContentValues();

        values.put(RecordTable.RecordColumns.QUESTION_, record.getQuestion());
        values.put(RecordTable.RecordColumns.ANSWER1, record.getAnswer1());
        values.put(RecordTable.RecordColumns.ANSWER2, record.getAnswer2());
        values.put(RecordTable.RecordColumns.ANSWER3, record.getAnswer3());
        values.put(RecordTable.RecordColumns.ANSWER4, record.getAnswer4());
        values.put(RecordTable.RecordColumns.CORRECTANSWER, record.getCorrectAnswer());

        db.insert(RecordTable.TABLE_NAME, null, values);
    }

    @Override
    public void update(Record record)
    {

    }
}
