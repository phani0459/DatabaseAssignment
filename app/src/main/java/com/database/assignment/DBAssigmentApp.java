package com.database.assignment;

import android.app.Application;

import com.database.assignment.di.component.ApplicationComponent;
import com.database.assignment.di.component.DaggerApplicationComponent;
import com.database.assignment.di.module.ApplicationModule;

public class DBAssigmentApp extends Application {

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();

        mApplicationComponent.inject(this);
    }

    public ApplicationComponent getComponent() {
        return mApplicationComponent;
    }

}
