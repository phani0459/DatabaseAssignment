package com.database.assignment.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.database.assignment.utils.Constants;

@Entity(tableName = Constants.EXTNS_TABLE_NAME)
public class Extensions {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "context")
    private String context;

    @ColumnInfo(name = "phoneContactId")
    private int phoneContactId;

    public Extensions(String context, int phoneContactId) {
        this.context = context;
        this.phoneContactId = phoneContactId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public int getPhoneContactId() {
        return phoneContactId;
    }

    public void setPhoneContactId(int phoneContactId) {
        this.phoneContactId = phoneContactId;
    }
}