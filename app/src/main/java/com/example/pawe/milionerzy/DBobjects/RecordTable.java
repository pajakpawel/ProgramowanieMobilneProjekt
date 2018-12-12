package com.example.pawe.milionerzy.DBobjects;

import android.database.sqlite.SQLiteDatabase;

import com.example.pawe.milionerzy.models.Record;

public class RecordTable {
    public static final String TABLE_NAME = "Record";

    @Override
    public String toString() {
        return TABLE_NAME;
    }

    public static final void createTable(SQLiteDatabase db)
    {
        String query = "CREATE TABLE " + TABLE_NAME + "(" +
                        RecordColumns.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        RecordColumns.QUESTION_ + " TEXT, " +
                        RecordColumns.ANSWER1 + " TEXT, " +
                        RecordColumns.ANSWER2 + " TEXT, " +
                        RecordColumns.ANSWER3 + " TEXT, " +
                        RecordColumns.ANSWER4 + " TEXT, " +
                        RecordColumns.CORRECTANSWER + " TEXT" + ");";

        db.execSQL(query);
    }

    public static final void insertIntoTable(DaoRecord daoRecord)
    {
        Record rec1 = new Record("Kto był pierwszym królem Polski?", "Mieszko I", "Bolesław Chrobry", "Zygmunt III Waza", "Stanisław Poniatowski", "B");
        Record rec2 = new Record("Kto jest autorem tekstu polskiego hymnu narodowego?", "Jan Wybicki", "Tadeusz Kościuszko", "Jan Henryk Dąbrowski", "Józef Wybicki", "D");
        Record rec3 = new Record("Podaj daty 3 rozbiorów Polski", "1772,1793,1795", "1792,1793,1795", "1783,1785,1795", "1775,1792,1793", "A");
        Record rec4 = new Record("Jaka jest stolica Hiszpanii?", "Barcelona", "Walencja", "Madryt", "Sewilla", "C");
        Record rec5 = new Record("Jak nazywa się wokalista zespołu Metallica?", "Kirk Hammett", "Dave Mustaine", "Frank Bello", "James Hetfield", "D");
        Record rec6 = new Record("Głównym składnikiem gazu ziemnego jest:", "Metan", "Etan", "Propan", "Butan", "A");
        Record rec7 = new Record("Co w roku 1893 opatentował Rudolf Diesel?", "Samochód z silnikiem benzynowym", "Szybkoobrotowy silnik benzynowy", "Akumulator Ołowiowy", "Silnik wysokoprężny", "D");
        Record rec8 = new Record("Chałwa na obszarach śródziemnomorskich wytwarzana jest głównie z nasion:", "Słonecnika", "Granatu", "Sezamu", "Dyni", "C");
        Record rec9 = new Record("Czerwone krwiki to:", "Trombocyty", "Erytrocyty", "Leukocyty", "Granulocyty", "B");

        daoRecord.insert(rec1);
        daoRecord.insert(rec2);
        daoRecord.insert(rec3);
        daoRecord.insert(rec4);
        daoRecord.insert(rec5);
        daoRecord.insert(rec6);
        daoRecord.insert(rec7);
        daoRecord.insert(rec8);
        daoRecord.insert(rec9);
    }

    public static final void deleteTable(SQLiteDatabase db)
    {
        String query = "DROP TABLE IF EXISTS " + TABLE_NAME + ";";
        db.execSQL(query);
    }

    public class RecordColumns {
        public static final String ID = "id";
        public static final String QUESTION_ = "question";
        public static final String ANSWER1 = "answer1";
        public static final String ANSWER2 = "answer2";
        public static final String ANSWER3 = "answer3";
        public static final String ANSWER4 = "answer4";
        public static final String CORRECTANSWER = "correct_answer";
    }
}
