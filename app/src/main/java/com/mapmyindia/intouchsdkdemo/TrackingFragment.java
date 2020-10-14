package com.mapmyindia.intouchsdkdemo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.mapmyindia.intouchsdkdemo.databinding.FragmentTrackingBinding;
import com.mapmyindia.sdk.intouch.InTouch;
import com.mapmyindia.sdk.tracking.Config;
import com.mapmyindia.sdk.tracking.TrackingStateObserver;
import com.mapmyindia.sdk.tracking.utils.TrackingError;

public class TrackingFragment extends Fragment implements TrackingStateObserver.OnTrackingStateChangeListener {
    private FragmentTrackingBinding mBinding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_tracking, container, false);


        mBinding.setIsBeaconEnabled(InTouch.isRunning());
        InTouch.addNotificationIconsAndTitle(R.drawable.ic_stat_phone_iphone,
                R.drawable.ic_stat_phone_iphone,
                "MapmyIndia Tracking Sdk",
                "Tracking your position",
                "com.mapmyindia.intouchsdkdemo.MainActivity",
                R.color.colorWhite);

        mBinding.setOnClickStartBeacon(v -> {
            if (getActivity() == null) {
                return;
            }
            startTracking();
        });
        mBinding.setOnClickRedirect(v -> {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://intouch.mapmyindia.com")));
        });
        InTouch.addTrackingStateListener(this);
        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    private void stopTracking() {
        InTouch.stopTracking();
    }

    private void startTracking() {
        if (getActivity() == null) {
            return;
        }
        if (!InTouch.isRunning()) {
            new Config.Builder(getContext())
                    .setPriority(InTouch.BEACON_PRIORITY_FAST)
                    .build();
            InTouch.startTracking();
        } else {
            stopTracking();
        }
    }


    @Override
    public void onError(TrackingError trackingError) {

    }

    @Override
    public void onTrackingStart() {
        mBinding.setIsBeaconEnabled(true);
        if (getActivity() != null)
            Toast.makeText(getActivity(), "onTrackingStart", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTrackingStop() {
        if (getActivity() != null)
            Toast.makeText(getActivity(), "onTrackingStop", Toast.LENGTH_SHORT).show();
        mBinding.setIsBeaconEnabled(false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }
}