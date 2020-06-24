package com.mapmyindia.intouchsdkdemo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.os.Handler;

import com.mapmyindia.intouchsdkdemo.databinding.ActivityMainBinding;
import com.mapmyindia.intouchsdkdemo.utils.PreferenceHelper;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        new Handler().postDelayed(() -> {
            if (PreferenceHelper.getInstance().isInitialized(this)) {
                replaceFragment(MainFragment.newInstance(), false);
            } else {
                replaceFragment(SetUpKeyFragment.newInstance(), false);
            }
        }, 3000);


    }

    public void replaceFragment(Fragment fragment, boolean addToBackStack) {
        Fragment f = getSupportFragmentManager().findFragmentByTag(fragment.getClass().getName());
        if (f == null) {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(mBinding.mainContainer.getId(), fragment, fragment.getClass().getName());
            if (addToBackStack) {
                ft.addToBackStack(fragment.getClass().getName());
            }
            try {
                ft.commit();
            } catch (IllegalStateException e) {
                ft.commitAllowingStateLoss();
            }
        }
    }
}
