package com.mapmyindia.intouchsdkdemo.device;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.mapmyindia.sdk.intouch.devicelastdata.vo.InTouchLastDeviceStateResponse;

public class DeviceViewModel extends ViewModel {


    private DeviceRepo repo = new DeviceRepo();


    public LiveData<InTouchLastDeviceStateResponse> getLastDeviceState(Long deviceId, String trackingCode) {
        return repo.getLastDeviceState(deviceId, trackingCode);
    }
}
