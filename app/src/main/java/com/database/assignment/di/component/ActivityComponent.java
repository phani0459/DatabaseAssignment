package com.database.assignment.di.component;

import com.database.assignment.di.PerActivity;
import com.database.assignment.di.module.ActivityModule;
import com.database.assignment.ui.launcher.LauncherActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(LauncherActivity activity);

}
