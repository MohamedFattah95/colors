package com.fci.colors_app.di.module;

import com.fci.colors_app.ui.base.BaseDialog;

import dagger.Module;


@Module
public class DialogModule {

    private BaseDialog dialog;

    public DialogModule(BaseDialog dialog) {
        this.dialog = dialog;
    }


}
