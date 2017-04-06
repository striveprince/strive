package com.app.ui.activity.main;

import android.content.Context;
import android.databinding.Bindable;

import com.app.BR;
import com.app.R;
import com.app.ui.base.activity.ContentView;
import com.app.ui.base.respond.Respond;
import com.app.ui.base.viewModel.BaseViewModel;

/**
 * Created by maomao on 2017/3/29.
 */
@ContentView(R.layout.activity_main)
public class MainViewModel extends BaseViewModel<Respond> {
    private String test = "22222";

    public MainViewModel(Context context) {
        super(context);
    }
    @Bindable
    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
        notifyPropertyChanged(BR.test);

    }
}
