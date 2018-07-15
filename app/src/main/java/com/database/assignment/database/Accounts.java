package com.database.assignment.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.database.assignment.utils.Constants;

@Entity(tableName = Constants.ACNTS_TABLE_NAME)
public class Accounts {

    @PrimaryKey(autoGenerate = true)
    private int _id;

    @ColumnInfo(name = "status")
    private String status;

    @ColumnInfo(name = "userID")
    private String userID;

    @ColumnInfo(name = "context")
    private String context;

    public Accounts(String status, String userID, String context) {
        this.status = status;
        this.userID = userID;
        this.context = context;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}