package com.database.assignment.di.module;

import android.app.Application;
import android.content.Context;

import com.database.assignment.di.ApplicationContext;
import com.database.assignment.di.DatabaseInfo;
import com.database.assignment.utils.Constants;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName() {
        return Constants.DB_NAME;
    }

}
