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
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.gson.Gson;
import com.mapmyindia.intouchsdkdemo.databinding.FragmentTrackingBinding;
import com.mapmyindia.intouchsdkdemo.device.DeviceViewModel;
import com.mapmyindia.intouchsdkdemo.utils.PreferenceHelper;
import com.mapmyindia.sdk.intouch.InTouch;
import com.mmi.beacon.Config;
import com.mmi.beacon.MapmyIndiaBeacon;
import com.mmi.beacon.TrackingStateObserver;
import com.mmi.beacon.utils.TrackingError;

import timber.log.Timber;

public class TrackingFragment extends Fragment implements TrackingStateObserver.OnTrackingStateChangeListener {
    private FragmentTrackingBinding mBinding;
    private int mPrioritySelectedIndex = -1;
    private int mGenderSelectedIndex = -1;
    private int mVehicleTypeSelectedIndex = -1;
    private DeviceViewModel viewModel;

    public static TrackingFragment newInstance() {

        Bundle args = new Bundle();

        TrackingFragment fragment = new TrackingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(DeviceViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_tracking, container, false);
        mBinding.setOnClickGender(v -> {
            if (getActivity() == null)
                return;
            new MaterialDialog.Builder(getActivity()).title(getString(R.string.txt_select_gender))
                    .items(R.array.gender)
                    .itemsCallbackSingleChoice(mGenderSelectedIndex, (dialog, itemView, which, text) -> {
                        mGenderSelectedIndex = which;
                        mBinding.textGender.setText(text);
                        Toast.makeText(getActivity(), "Selected item $text", Toast.LENGTH_SHORT).show();
                        return true;
                    }).show();
        });
        mBinding.setOnClickVehicleType(v -> {
            if (getActivity() == null)
                return;
            new MaterialDialog.Builder(getActivity()).title(getString(R.string.txt_select_vehicle_type))
                    .items(R.array.vehicle_type)
                    .itemsCallbackSingleChoice(mVehicleTypeSelectedIndex, (dialog, itemView, which, text) -> {
                        mVehicleTypeSelectedIndex = which;
                        mBinding.textVehicleType.setText(text);
                        //Toast.makeText(act, "Selected item $text", Toast.LENGTH_SHORT).show()
                        return true;

                    }).show();
        });

        mBinding.setOnClickPriority(v -> {
            if (getActivity() == null)
                return;
            new MaterialDialog.Builder(getActivity()).title(getString(R.string.txt_select_pooling_frequency))
                    .items(R.array.priority)
                    .itemsCallbackSingleChoice(mPrioritySelectedIndex, (dialog, itemView, which, text) -> {
                        mPrioritySelectedIndex = which;
                        mBinding.textPriority.setText(text);
                        //      Toast.makeText(act, "Selected item $text", Toast.LENGTH_SHORT).show()
                        return true;
                    }).show();
        });
        mBinding.setIsEnabled(false);
        mBinding.setIsBeaconEnabled(MapmyIndiaBeacon.getInstance().isTracking());
        MapmyIndiaBeacon.getInstance().addNotificationIconsAndTitle(R.drawable.ic_stat_phone_iphone,
                R.drawable.ic_stat_phone_iphone,
                "MapmyIndia Tracking Sdk",
                "Tracking your position",
                "com.wishmaster.notification.MainActivity");
        reStoreConfiguration();

        mBinding.setOnClickStartBeacon(v -> {
            if (getActivity() == null) {
                return;
            }
            if (mPrioritySelectedIndex == -1) {
                Toast.makeText(getActivity(), "Please select priority", Toast.LENGTH_SHORT).show();
            }
            InTouch.setDeviceName(PreferenceHelper.getInstance().getDeviceName(getActivity()));
            startTracking();
        });
        mBinding.setOnClickStopBeacon(v -> {
            if (getActivity() == null) {
                return;
            }
            stopTracking();
        });
        mBinding.setOnClickRedirect(v -> {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://intouch.mapmyindia.com")));
        });

        mBinding.setOnClickLastState(v -> {
            viewModel.getLastDeviceState(192340L, null).observe(getViewLifecycleOwner(), response -> {
                Timber.d("%s", new Gson().toJson(response).toString());
            });
        });
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
            InTouch.addTrackingStateListener(this);


            Config config = new Config.Builder(getActivity())
                    .setPriority(getPriorityType(mBinding.textPriority.getText().toString()))
                    .setVehicleType(mBinding.textVehicleType.getText().toString())
                    .setGender(mBinding.textGender.getText().toString())
                    .build();

            PreferenceHelper.getInstance().setConfiguration(getActivity(), config);
            InTouch.startTracking();

        } else {
            //Toast.makeText(getActivity(), "Beacon already running", Toast.LENGTH_SHORT).show();
            stopTracking();
        }
    }

    private void reStoreConfiguration() {
        if (getActivity() == null)
            return;
        Config config = PreferenceHelper.getInstance().getBeaconConfiguration(getActivity());
        if (config != null) {
            mBinding.textVehicleType.setText(config.vehicleType != null ? config.vehicleType : "");
            mBinding.textGender.setText(config.gender != null ? config.gender : "");
            mBinding.textPriority.setText(getPriorityText(config.priority));
            mPrioritySelectedIndex = getPriorityType(getPriorityText(config.priority));
            // mBinding.get().textPoolingTimeMoving.setText("${config.timeWhileMovingInSec}")

        }
    }

    private int getPriorityType(String selectedPriority) {
        if (selectedPriority.equals("Fast")) {
            return MapmyIndiaBeacon.BEACON_PRIORITY_FAST;
        } else if (selectedPriority.equals("Slow")) {
            return MapmyIndiaBeacon.BEACON_PRIORITY_SLOW;
        } else {
            return MapmyIndiaBeacon.BEACON_PRIORITY_OPTIMAL;
        }
    }

    private String getPriorityText(int selectedPriority) {
        if (getActivity() == null)
            return null;

        String[] priorityArray = getActivity().getResources().getStringArray(R.array.priority);
        String text = null;
        text = priorityArray[selectedPriority];

        return text;
    }

    @Override
    public void onError(TrackingError trackingError) {
        mBinding.setIsBeaconEnabled(mBinding.getIsEnabled());
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
        MapmyIndiaBeacon.getInstance().removeTrackingStateListener(this);
    }
}