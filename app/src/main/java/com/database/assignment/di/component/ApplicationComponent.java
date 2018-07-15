package com.database.assignment.di.component;

import android.app.Application;
import android.content.Context;

import com.database.assignment.DBAssigmentApp;
import com.database.assignment.di.ApplicationContext;
import com.database.assignment.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    @ApplicationContext
    Context context();

    void inject(DBAssigmentApp app);

    Application application();

//    DataManager getDataManager();
}