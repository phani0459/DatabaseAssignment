package com.database.assignment.ui.launcher;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.database.assignment.R;
import com.database.assignment.database.Accounts;
import com.database.assignment.database.Contacts;
import com.database.assignment.database.Extensions;
import com.database.assignment.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemSelected;

import static butterknife.OnItemSelected.Callback.NOTHING_SELECTED;

public class LauncherActivity extends BaseActivity implements LauncherView{

    @Inject
    LauncherMVPPresenter<LauncherView> mPresenter;
    @BindView(R.id.pbLoading)
    ProgressBar pbLoading;

    @BindView(R.id.spnr_load_contactIds)
    Spinner contactIdsSpinner;

    @BindView(R.id.txt_context)
    TextView contextTextView;

    @BindView(R.id.txt_stagingId)
    TextView statgingTextView;

    @BindView(R.id.txt_status)
    TextView statusTextView;

    @BindView(R.id.txt_userId)
    TextView userIdTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(LauncherActivity.this);
        mPresenter.saveDataintoDB();

    }

    @OnItemSelected(R.id.spnr_load_contactIds)
    public void onItemSelected(int position) {
        if (!contactIdsSpinner.getSelectedItem().toString().equalsIgnoreCase("Select Contact Id")) {
            mPresenter.getSelectedContact(contactIdsSpinner.getSelectedItem().toString());
        }
    }

    @OnItemSelected(value = R.id.spnr_load_contactIds, callback = NOTHING_SELECTED)
    public void onNothingSelected() {
    }


    @Override
    public void showLoading() {
        pbLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        pbLoading.setVisibility(View.GONE);
    }

    @Override
    public void onContactSelected(Contacts contact) {
        statgingTextView.setText("Staging Id: " + contact.getStagingId());
        mPresenter.getAccountByExtension(contact.get_id());
    }

    @Override
    public void onAccountLoaded(Accounts account) {
        contextTextView.setText("Context: " + account.getContext());
        statusTextView.setText("Status: " + account.getStatus());
        userIdTextView.setText("User: " + account.getUserID());
    }

    @Override
    public void updateSpinnerAdapter(List<Contacts> contacts) {
        ArrayList<String> ids = new ArrayList<>();
        ids.add("Select Contact Id");
        for (int i = 0; i < contacts.size(); i++) {
            ids.add(contacts.get(i).getContactId());
        }
        contactIdsSpinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, ids));
    }

    @Override
    public void onDBSaved() {
        mPresenter.loadIdsinSpinner();
    }

}
