package com.database.assignment.database.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.database.assignment.database.Accounts;
import com.database.assignment.database.Contacts;
import com.database.assignment.database.Extensions;
import com.database.assignment.database.dao.AccountsDao;
import com.database.assignment.database.dao.ContactsDao;
import com.database.assignment.database.dao.ExtensionsDao;

@Database(entities = {Contacts.class, Extensions.class, Accounts.class}, version = 1)
public abstract class ContactsDB extends RoomDatabase {
    public abstract ContactsDao contactsDao();
    public abstract ExtensionsDao extensionsDao();
    public abstract AccountsDao accountsDao();
}

 