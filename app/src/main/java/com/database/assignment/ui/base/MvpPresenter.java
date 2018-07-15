package com.database.assignment.ui.base;

/**
 * Every presenter in the app must either implement this interface or extend BasePresenter
 * indicating the MvpView type that wants to be attached with.
 */
public interface MvpPresenter<V extends BaseView> {

    void onAttach(V mvpView);

    void onDetach();
}
