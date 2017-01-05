package com.gogenius.learningdemos.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.gogenius.learningdemos.GlobalApplication;

import java.util.ArrayList;
import java.util.List;

import cn.trinea.android.common.util.ShellUtils;

/**
 * Created by shijiwei on 2016/11/21.
 */
public class USBReceiver extends BroadcastReceiver {

    private static final String TAG = "USBReceiver";
    private static UsbManager mUsbManager;
    private List<UsbDevice> mUsbDevList = new ArrayList<>();
    private List<String> shellCommand = new ArrayList<>();
    private List<String> mUubCameraList = new ArrayList<>();

    static {
        mUsbManager = (UsbManager) GlobalApplication.getApplication().getSystemService(Context.USB_SERVICE);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        Log.e(TAG, action);

        mUsbDevList.clear();

        for (UsbDevice dev : mUsbManager.getDeviceList().values())
            mUsbDevList.add(dev);

        Log.e(TAG, "UsbDevice number is " + mUsbDevList.size());

        for (int i = 0; i < mUsbDevList.size(); i++) {
            Log.e(TAG, "name = " + mUsbDevList.get(i).getDeviceName()
                    + " pid = " + mUsbDevList.get(i).getProductId()
                    + " vid = " + mUsbDevList.get(i).getVendorId());
        }

        searchUsbCamera();
    }

    public void searchUsbCamera() {

        shellHandler.post(searchRunnable);
    }

    private Runnable searchRunnable = new Runnable() {
        @Override
        public void run() {
            ShellUtils.CommandResult result = ShellUtils.execCommand("ls -l /dev/video*", true, true);
            Message message = new Message();
            message.obj = result;
            shellHandler.sendMessage(message);
        }
    };

    private Handler shellHandler = new Handler(new Handler.Callback() {

        @Override
        public boolean handleMessage(Message msg) {
            mUubCameraList.clear();
            if (msg.obj != null) {
                ShellUtils.CommandResult result = (ShellUtils.CommandResult) msg.obj;
                String string = result.successMsg;

                if (string == null || string.equals("null") || string.equals("")) {
                    return false;
                }
                String[] list = string.split("crw");

                for (int i = 0; i < list.length; i++) {
                    String item = list[i];
                    if (item.indexOf("camera") > 0) {
                        String camera = item.substring(item.indexOf("video"), item.length());
                        mUubCameraList.add(camera);
                        shellCommand.add("chmod 666 /dev/" + camera);
                    }
                }

                Log.e(TAG, "usb camera number is " + mUubCameraList.size());
                ShellUtils.execCommand(shellCommand, true);
            }
            return false;
        }
    });
}
