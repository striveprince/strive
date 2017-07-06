package com.read.group.ui.register;

import android.databinding.ObservableField;
import android.view.View;

import com.read.group.R;
import com.read.group.base.annotation.ModelView;
import com.read.group.base.model.ViewModel;
import com.read.group.data.api.ReadApi;
import com.read.group.data.entity.InfoEntity;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by apple on 2017/7/5.
 */
@ModelView(R.layout.activity_register)
public class RegisterModel extends ViewModel<RegisterActivity>{
    public ObservableField<String> phone = new ObservableField<>();
    public ObservableField<String> password = new ObservableField<>();
    public ObservableField<String> code = new ObservableField<>();
    @Inject
    RegisterModel() {}

    @Inject ReadApi readApi;

    public void onRegisterClick(View view){
//        Observable<InfoEntity<String>> message = readApi.register(phone.get(),password.get(),code.get());
    }

}
