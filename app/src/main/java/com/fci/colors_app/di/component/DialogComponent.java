package com.fci.colors_app.di.component;

import com.fci.colors_app.di.module.DialogModule;
import com.fci.colors_app.di.scope.DialogScope;

import dagger.Component;

@DialogScope
@Component(modules = DialogModule.class, dependencies = AppComponent.class)
public interface DialogComponent {


}
