package com.database.assignment.database.dao;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.database.assignment.database.Accounts;
import com.database.assignment.utils.Constants;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;


@Dao

public interface AccountsDao {

    @Query("SELECT * FROM " + Constants.ACNTS_TABLE_NAME)
    Maybe<List<Accounts>> getAllUsers();

    @Query("SELECT * FROM " + Constants.ACNTS_TABLE_NAME + " WHERE  context LIKE (:dates)")
    Flowable<List<Accounts>> loadByDates(String dates);

    @Query("SELECT * FROM " + Constants.ACNTS_TABLE_NAME + " WHERE context IN (:context)")
    Flowable<Accounts> getAccountFromExtension(String context);

    @Query("SELECT * FROM " + Constants.ACNTS_TABLE_NAME + " WHERE context IN (SELECT context FROM " + Constants.EXTNS_TABLE_NAME + " WHERE phoneContactId = (:id))")
    Flowable<Accounts> getAccountByContext(int id);

    @Insert
    void insert(Accounts user);

}