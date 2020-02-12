package com.mapmyindia.intouchsdkdemo.device;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.mapmyindia.sdk.intouch.InTouch;
import com.mapmyindia.sdk.intouch.callbacks.InTouchDeviceRecentDataCallBack;
import com.mapmyindia.sdk.intouch.callbacks.IntouchDeviceCallBack;
import com.mapmyindia.sdk.intouch.devicelastdata.vo.InTouchLastDeviceStateResponse;
import com.mapmyindia.sdk.intouch.devices.device.vo.DevicesResponse;

public class DeviceRepo {

    public LiveData<InTouchLastDeviceStateResponse> getLastDeviceState(Long deviceId, String trackingCode) {
        MutableLiveData<InTouchLastDeviceStateResponse> liveData = new MutableLiveData<InTouchLastDeviceStateResponse>();
        InTouch.getLastDeviceState(deviceId, trackingCode, new InTouchDeviceRecentDataCallBack() {

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
