package com.database.assignment.database.dao;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.database.assignment.database.Accounts;
import com.database.assignment.database.Extensions;
import com.database.assignment.utils.Constants;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;


@Dao

public interface ExtensionsDao {

    @Query("SELECT * FROM " + Constants.EXTNS_TABLE_NAME)
    Maybe<List<Extensions>> getAllUsers();

    @Query("SELECT * FROM " + Constants.EXTNS_TABLE_NAME + " WHERE phoneContactId LIKE (:dates)")
    Flowable<List<Extensions>> loadByDates(String dates);

    @Query("SELECT * FROM " + Constants.EXTNS_TABLE_NAME + " WHERE phoneContactId IN (:contactID)")
    Flowable<Extensions> getExtensionById(int contactID);

    @Insert
    void insert(Extensions user);

}