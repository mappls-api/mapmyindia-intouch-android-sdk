package com.mapmyindia.intouchsdkdemo.device;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.mapmyindia.sdk.intouch.InTouch;
import com.mapmyindia.sdk.intouch.callbacks.IRecentDeviceDataListener;
import com.mapmyindia.sdk.intouch.devicelastdata.vo.InTouchLastDeviceStateResponse;

public class DeviceRepo {

    public LiveData<InTouchLastDeviceStateResponse> getLastDeviceState(Long deviceId, String trackingCode) {
        MutableLiveData<InTouchLastDeviceStateResponse> liveData = new MutableLiveData<InTouchLastDeviceStateResponse>();
        InTouch.getLastDeviceState(deviceId, trackingCode, new IRecentDeviceDataListener() {

            @Override
            public void onSuccess(InTouchLastDeviceStateResponse response) {
                liveData.postValue(response);
            }

            @Override
            public void onError(String reason, String errorIdentifier, String errorDescription) {
                liveData.postValue(null);
            }
        });
        return liveData;
    }

}
