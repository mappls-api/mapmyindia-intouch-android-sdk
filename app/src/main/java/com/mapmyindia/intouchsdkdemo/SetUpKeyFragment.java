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
import com.mapmyindia.sdk.intouch.InTouch;
import com.mapmyindia.sdk.intouch.callbacks.IAuthListener;
import com.mapmyindia.sdk.tracking.utils.AutoStartPermissionHelper;

public class SetUpKeyFragment extends Fragment {

    private final String KEY_CLIENT_ID = "<your client id>";
    private final String KEY_CLIENT_SECRET = "<your client secret>";

    private FragmentKeyInitializationBinding mBinding;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_key_initialization, container, false);

        mBinding.setOnClickInItKey(v -> {
            if (getActivity() == null)
                return;
            if (!TextUtils.isEmpty(KEY_CLIENT_ID) && !TextUtils.isEmpty(KEY_CLIENT_SECRET)) {
                InTouch.initialize(mBinding.textName.getText().toString(), KEY_CLIENT_ID, KEY_CLIENT_SECRET, new IAuthListener() {
                    @Override
                    public void onSuccess() {
                        if (getActivity() != null) {
                            ((MainActivity) getActivity()).replaceFragment(new TrackingFragment());
                        }
                    }

                    @Override
                    public void onError(String reason, String errorIdentifier, String errorDescription) {
                        Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                mBinding.textKey.setError("Invalid Key");
            }
        });
        if (getActivity() != null) {
            AutoStartPermissionHelper.getPermissionHelper().getAutoStartPermission(getActivity());
        }
        return mBinding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }
}
