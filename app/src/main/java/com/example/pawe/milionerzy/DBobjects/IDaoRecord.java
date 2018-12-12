package com.example.pawe.milionerzy.DBobjects;

import com.example.pawe.milionerzy.models.Record;

import java.util.ArrayList;

public interface IDaoRecord {
    Record findById(int id);
    ArrayList<Record> findall();
    int getCount();
    void insert(Record record);
    void update (Record record);
}
