package com.cutv.ningbo.ui.activity.main;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.cutv.ningbo.R;
import com.cutv.ningbo.data.api.UserApi;
import com.cutv.ningbo.databinding.ActivityMainBinding;
import com.cutv.ningbo.inject.qualifier.context.ActivityContext;
import com.cutv.ningbo.inject.scope.ActivityScope;
import com.cutv.ningbo.ui.base.activity.BaseActivity;
import com.cutv.ningbo.ui.base.respond.Respond;

import javax.inject.Inject;

@ActivityScope
public class MainActivity extends BaseActivity<MainViewModel, ActivityMainBinding> implements Respond.RadioRespond {
    @Inject
    UserApi userApi;
    @Inject
    @ActivityContext
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);
        setBindingView(R.layout.activity_main, savedInstanceState);
        setSupportActionBar(binding.toolLayoutMain.mainToolbar);
//        viewModel.setBinding(binding);

//        binding.mainBottomTab.setOnCheckedChangeListener(checked);
//        binding.mainRadioHomepager.setChecked(true);

//        LinearLayout  gallery = (LinearLayout)binding.navigationView.getMenu().findItem(R.id.menu_message).getActionView();
//        TextView msg= (TextView) gallery.findViewById(R.id.msg);
//        msg.setText("9");
//        msg.setVisibility(View.GONE);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_navigation_main, menu);
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return item.getItemId() == R.id.message || super.onOptionsItemSelected(item);
    }

    @Override
    public void onCheckedChanged(int position) {
        binding.setBar(viewModel.getList().get(position));
    }

    public RadioGroup getGroup() {
        return binding.toolLayoutMain.toolRgTag;
    }

    public HorizontalScrollView getScrollView() {
        return binding.toolLayoutMain.newsHscr;
    }

}
//    public void onMenuClick(View view){
//        Toast.makeText(context,"view:"+view.getId(),Toast.LENGTH_SHORT).show();
//        switch (view.getId()) {
//            case R.id.message:break;
//        }
//    }