package com.database.assignment.database.db;


import android.arch.persistence.room.Room;
import android.content.Context;

import com.database.assignment.database.Accounts;
import com.database.assignment.database.Contacts;
import com.database.assignment.database.Extensions;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class LocalCacheManager {
    private static final String DB_NAME = "database-name";
    private Context context;
    private static LocalCacheManager _instance;
    private ContactsDB db;

    public static LocalCacheManager getInstance(Context context) {
        if (_instance == null) {
            _instance = new LocalCacheManager(context);
        }
        return _instance;
    }

    public LocalCacheManager(Context context) {
        this.context = context;
        db = Room.databaseBuilder(context, ContactsDB.class, DB_NAME).build();
    }

    public void getContactById(final DatabaseCallBack databaseCallback, final String Id) {
        db.contactsDao().getContactsById(Id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Contacts>() {

            @Override
            public void accept(@io.reactivex.annotations.NonNull Contacts contact) throws Exception {
                databaseCallback.singleContact(contact);
            }

        });
    }

    public void getAccount(final DatabaseCallBack databaseCallback, final Extensions extension) {
        db.accountsDao().getAccountByContext(extension.getContext()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Accounts>() {

            @Override
            public void accept(@io.reactivex.annotations.NonNull Accounts account) throws Exception {
                databaseCallback.singleAccount(account);
            }

        });
    }

    public void getExtensionsById(final DatabaseCallBack databaseCallback, final Contacts contact) {
        db.extensionsDao().getExtensionById(contact.get_id()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Extensions>() {

            @Override
            public void accept(@io.reactivex.annotations.NonNull Extensions extension) throws Exception {
                databaseCallback.singleExtension(extension);
            }

        });
    }

    public void getContacts(final DatabaseCallBack databaseCallback, String filtered) {
        db.contactsDao().loadByDates("%" + filtered + "%").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<Contacts>>() {

            @Override

            public void accept(@io.reactivex.annotations.NonNull List<Contacts> users) throws Exception {
                databaseCallback.onContactsLoaded(users);
            }

        });
    }

    public void getContacts(final DatabaseCallBack databaseCallback) {
        db.contactsDao().getAllContacts().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<Contacts>>() {

            @Override

            public void accept(@io.reactivex.annotations.NonNull List<Contacts> contacts) throws Exception {
                databaseCallback.onContactsLoaded(contacts);
            }

        });
    }

    public void addContacts(final DatabaseCallBack databaseCallback, final int id, final String contactId, final String stagingId) {
        Completable.fromAction(new Action() {

            @Override

            public void run() throws Exception {
                Contacts contact = new Contacts(id, contactId, stagingId);
                db.contactsDao().insert(contact);
            }

        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onComplete() {
                databaseCallback.onContactAdded();
            }

            @Override
            public void onError(Throwable e) {
                databaseCallback.onDataNotAvailable();
            }
        });
    }

    public void addExtensions(final DatabaseCallBack databaseCallback, final String context, final int phoneContactId) {
        Completable.fromAction(new Action() {

            @Override

            public void run() throws Exception {
                Extensions extension = new Extensions(context, phoneContactId);
                db.extensionsDao().insert(extension);
            }

        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onComplete() {
                databaseCallback.onContactAdded();
            }

            @Override
            public void onError(Throwable e) {
                databaseCallback.onDataNotAvailable();
            }
        });
    }

    public void addAccounts(final DatabaseCallBack databaseCallback, final String status, final String userId, final String context, final boolean isLast) {
        Completable.fromAction(new Action() {

            @Override

            public void run() throws Exception {
                Accounts account = new Accounts(status, userId, context);
                db.accountsDao().insert(account);
            }

        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onComplete() {
                databaseCallback.onAccountAdded(isLast);
            }

            @Override
            public void onError(Throwable e) {
                databaseCallback.onDataNotAvailable();
            }
        });
    }

}