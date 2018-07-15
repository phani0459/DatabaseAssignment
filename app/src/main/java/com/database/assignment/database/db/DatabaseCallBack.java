package com.database.assignment.database.db;

import com.database.assignment.database.Accounts;
import com.database.assignment.database.Contacts;
import com.database.assignment.database.Extensions;

import java.util.List;

public interface DatabaseCallBack {
    void onContactsLoaded(List<Contacts> contacts);

    void onContactAdded();

    void onAccountAdded(boolean isLast);

    void onDataNotAvailable();

    void singleContact(Contacts contact);

    void singleExtension(Extensions extension);

    void singleAccount(Accounts accounts);

}