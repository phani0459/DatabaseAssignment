package com.database.assignment.ui.launcher;

import com.database.assignment.database.Contacts;
import com.database.assignment.database.Extensions;
import com.database.assignment.di.PerActivity;
import com.database.assignment.ui.base.MvpPresenter;

@PerActivity
public interface LauncherMVPPresenter<V extends LauncherView> extends MvpPresenter<V> {
    void onContactIdSelected();

    void checkDataInDB();

    void saveDataintoDB();

    void loadIdsinSpinner();

    void getSelectedContact(String contactId);

    void getExtensionByContat(Contacts contact);

    void getAccountByExtension(Extensions extension);

}
