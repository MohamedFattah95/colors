
package com.fci.colors_app.ui.faqs;

import androidx.lifecycle.MutableLiveData;

import com.fci.colors_app.data.DataManager;
import com.fci.colors_app.data.models.DataWrapperModel;
import com.fci.colors_app.data.models.FAQsModel;
import com.fci.colors_app.ui.base.BaseViewModel;
import com.fci.colors_app.utils.rx.SchedulerProvider;

import java.util.List;

public class FAQsViewModel extends BaseViewModel<FAQsNavigator> {

    private MutableLiveData<DataWrapperModel<List<FAQsModel>>> faqsLiveData;

    public FAQsViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
        faqsLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<DataWrapperModel<List<FAQsModel>>> getFaqsLiveData() {
        return faqsLiveData;
    }

    public void getFAQs() {

    }
}
