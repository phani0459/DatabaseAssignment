package com.database.assignment.ui.launcher;

import com.database.assignment.database.Accounts;
import com.database.assignment.database.Contacts;
import com.database.assignment.database.Extensions;
import com.database.assignment.ui.base.BaseView;

import java.util.List;

/**
 * Created by KANDAGATLAs on 14-02-2018.
 */

public interface LauncherView extends BaseView {

    void showLoading();

    void hideLoading();
    void updateSpinnerAdapter(List<Contacts> contacts);

    void onDBSaved();

    void onContactSelected(Contacts contact);

    void onAccountLoaded(Accounts account);

}
