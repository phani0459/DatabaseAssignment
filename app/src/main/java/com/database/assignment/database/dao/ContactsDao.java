package com.database.assignment.database.dao;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.database.assignment.database.Contacts;
import com.database.assignment.utils.Constants;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;


@Dao

public interface ContactsDao {

    @Query("SELECT * FROM " + Constants.CONTACTS_TABLE_NAME)
    Maybe<List<Contacts>> getAllContacts();

    @Query("SELECT * FROM " + Constants.CONTACTS_TABLE_NAME + " WHERE contactId LIKE (:dates)")
    Flowable<List<Contacts>> loadByDates(String dates);

    @Query("SELECT * FROM " + Constants.CONTACTS_TABLE_NAME + " WHERE contactId IN (:id)")
    Flowable<Contacts> getContactsById(String id);

    @Insert
    void insert(Contacts user);

}