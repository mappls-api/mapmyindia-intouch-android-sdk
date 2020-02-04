package com.mapmyindia.intouchsdkdemo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.mapmyindia.intouchsdkdemo.databinding.FragmentMainBinding;

public class MainFragment extends Fragment {

    public static MainFragment newInstance() {
        Bundle args = new Bundle();
        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentMainBinding mainBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
        if (getActivity() != null)
            ((MainActivity) getActivity()).replaceFragment(TrackingFragment.newInstance(), false);

        return mainBinding.getRoot();
    }

}
