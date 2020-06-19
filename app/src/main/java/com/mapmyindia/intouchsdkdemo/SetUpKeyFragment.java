package com.mapmyindia.intouchsdkdemo;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.mapmyindia.intouchsdkdemo.databinding.FragmentKeyInitializationBinding;
import com.mapmyindia.intouchsdkdemo.utils.PreferenceHelper;
import com.mapmyindia.sdk.intouch.InTouch;
import com.mapmyindia.sdk.intouch.callbacks.IAuthListener;
import com.mapmyindia.sdk.tracking.utils.AutoStartPermissionHelper;

import java.util.Objects;

public class SetUpKeyFragment extends Fragment {

    private final String PUBLISHABLE_KEY = "<Your Publishable Key>?";
  

    private FragmentKeyInitializationBinding mBinding;

    public static SetUpKeyFragment newInstance() {

        Bundle args = new Bundle();

        SetUpKeyFragment fragment = new SetUpKeyFragment();
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
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_key_initialization, container, false);
        mBinding.textKey.setText(PUBLISHABLE_KEY);

        mBinding.setOnClickInItKey(v -> {
            if (getActivity() == null)
                return;
            String key = mBinding.textKey.getText().toString();
            if (!TextUtils.isEmpty(key)) {
                //InTouch.initialize(key, mBinding.textName.getText().toString(), this);
                InTouch.initialize(mBinding.textName.getText().toString(), key, new IAuthListener() {
                    @Override
                    public void onSuccess() {
                        if (getActivity() != null) {
                            PreferenceHelper.getInstance().setInitializeSuccess(getActivity(), true);
                            PreferenceHelper.getInstance().setDeviceName(getActivity(), Objects.requireNonNull(mBinding.textName.getText()).toString());
                            ((MainActivity) getActivity()).replaceFragment(MainFragment.newInstance(), false);
                        }
                    }

                    @Override
                    public void onError(String reason, String errorIdentifier, String errorDescription) {
                        if (getActivity() != null) {
                            Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            } else {
                mBinding.textKey.setError("Invalid Key");
            }
        });
        if (

                getActivity() != null) {
            AutoStartPermissionHelper.getPermissionHelper().getAutoStartPermission(getActivity());
        }
        return mBinding.getRoot();
    }

}
