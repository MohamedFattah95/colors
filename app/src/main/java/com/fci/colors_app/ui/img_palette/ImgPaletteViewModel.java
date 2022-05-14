
package com.fci.colors_app.ui.img_palette;

import com.fci.colors_app.data.DataManager;
import com.fci.colors_app.ui.base.BaseViewModel;
import com.fci.colors_app.utils.rx.SchedulerProvider;


public class ImgPaletteViewModel extends BaseViewModel<ImgPaletteNavigator> {

    public ImgPaletteViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

}
