package com.database.assignment.di.module;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.database.assignment.di.ActivityContext;
import com.database.assignment.di.PerActivity;
import com.database.assignment.ui.launcher.LauncherMVPPresenter;
import com.database.assignment.ui.launcher.LauncherPresenter;
import com.database.assignment.ui.launcher.LauncherView;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private AppCompatActivity mActivity;

    public ActivityModule(AppCompatActivity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    AppCompatActivity provideActivity() {
        return mActivity;
    }

    @Provides
    @PerActivity
    LauncherMVPPresenter<LauncherView> provideLauncherPresenter(LauncherPresenter<LauncherView> presenter) {
        return presenter;
    }

}
