package com.database.assignment.ui.launcher;

import android.content.Context;
import android.util.Log;

import com.database.assignment.database.Accounts;
import com.database.assignment.database.Contacts;
import com.database.assignment.database.Extensions;
import com.database.assignment.database.db.DatabaseCallBack;
import com.database.assignment.database.db.LocalCacheManager;
import com.database.assignment.di.ActivityContext;
import com.database.assignment.ui.base.BasePresenter;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by KANDAGATLAs on 14-02-2018.
 */

public class LauncherPresenter<V extends LauncherView> extends BasePresenter<V> implements LauncherMVPPresenter<V>, DatabaseCallBack {


    Context context;
    @Inject
    public LauncherPresenter(@ActivityContext Context context) {
        this.context = context;
    }

    @Override
    public void onContactIdSelected() {

    }

    @Override
    public void checkDataInDB() {
        LocalCacheManager.getInstance(context).getContacts(this);
    }

    @Override
    public void saveDataintoDB() {
        getMvpView().showLoading();

        LocalCacheManager.getInstance(context).addContacts(this, 2, "48f3", "1196");
        LocalCacheManager.getInstance(context).addContacts(this, 3, "3e47", "f1fe");
        LocalCacheManager.getInstance(context).addContacts(this, 4, "2cac", "036e");

        LocalCacheManager.getInstance(context).addExtensions(this, "Gmail", 2);
        LocalCacheManager.getInstance(context).addExtensions(this, "Gmail", 3);
        LocalCacheManager.getInstance(context).addExtensions(this, "Gmail1", 4);

        LocalCacheManager.getInstance(context).addAccounts(this, "1", "test_one@gmail.com", "Gmail", false);
        LocalCacheManager.getInstance(context).addAccounts(this, "0", "test_one@gmail.com", "Gmail1", true);
        getMvpView().hideLoading();
    }

    @Override
    public void loadIdsinSpinner() {
        LocalCacheManager.getInstance(context).getContacts(this);
    }

    @Override
    public void getSelectedContact(String contactId) {
        LocalCacheManager.getInstance(context).getContactById(this, contactId);
    }

    @Override
    public void getExtensionByContat(Contacts contact) {
        Log.e("TAGTAG", "getExtensionByContat: " );
        LocalCacheManager.getInstance(context).getExtensionsById(this, contact);
    }

    @Override
    public void getAccountByExtension(Extensions extension) {
        LocalCacheManager.getInstance(context).getAccount(this, extension);
    }

    @Override
    public void onContactsLoaded(List<Contacts> contacts) {
        if (contacts != null && contacts.size() > 0) {
            getMvpView().updateSpinnerAdapter(contacts);
        }
    }

    @Override
    public void onContactAdded() {

    }

    @Override
    public void onAccountAdded(boolean isLast) {
        if (isLast) {
            getMvpView().onDBSaved();
        }
    }

    @Override
    public void onDataNotAvailable() {

    }

    @Override
    public void singleContact(Contacts contact) {
        getMvpView().onContactSelected(contact);
    }

    @Override
    public void singleExtension(Extensions extension) {
        getMvpView().onExtensionLoaded(extension);
    }

    @Override
    public void singleAccount(Accounts account) {
        getMvpView().onAccountLoaded(account);

        Log.e("TAGTAG", "singleAccount: " + account.getUserID() );
    }
}
