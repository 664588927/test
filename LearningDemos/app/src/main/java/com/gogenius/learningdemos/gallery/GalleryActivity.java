package com.gogenius.learningdemos.gallery;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.gogenius.learningdemos.R;

import java.util.List;

import cn.finalteam.galleryfinal.CoreConfig;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.ImageLoader;
import cn.finalteam.galleryfinal.ThemeConfig;
import cn.finalteam.galleryfinal.model.PhotoInfo;

/**
 * Created by shijiwei on 2016/10/7.
 */
public class GalleryActivity extends FragmentActivity {

    private final int REQUEST_CODE_CAMERA = 1000;
    private final int REQUEST_CODE_GALLERY = 1001;
    private final int REQUEST_CODE_CROP = 1002;
    private final int REQUEST_CODE_EDIT = 1003;


    private FunctionConfig functionConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        //设置主题
        //ThemeConfig.CYAN
        ThemeConfig theme = new ThemeConfig.Builder()
                .setTitleBarBgColor(Color.rgb(0xFF, 0x57, 0x22))
                .setTitleBarTextColor(Color.BLACK)
                .setTitleBarIconColor(Color.BLACK)
                .setFabNornalColor(Color.RED)
                .setFabPressedColor(Color.BLUE)
                .setCheckNornalColor(Color.WHITE)
                .setCheckSelectedColor(Color.BLACK)
                .setIconBack(R.mipmap.ic_action_previous_item)
                .setIconRotate(R.mipmap.ic_action_repeat)
                .setIconCrop(R.mipmap.ic_action_crop)
                .setIconCamera(R.mipmap.ic_action_camera)
                .build();
        //theme = ThemeConfig.CYAN;

        //配置功能
        functionConfig = new FunctionConfig.Builder()
                .setEnableCamera(true)
                .setEnableEdit(true)
                .setEnableCrop(true)
                .setEnableRotate(true)
                .setCropSquare(true)
                .setEnablePreview(true)
                .setMutiSelectMaxSize(9)
                .build();

        //配置imageloader
        ImageLoader imageloader = new GlideImageLoader();

        CoreConfig coreConfig = new CoreConfig.Builder(this, imageloader, theme)
                .setFunctionConfig(functionConfig)
                .setNoAnimcation(false)
                .build();

        GalleryFinal.init(coreConfig);

    }

    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_open_gallery:

                GalleryFinal.openGalleryMuti(REQUEST_CODE_GALLERY, functionConfig, onHanlderResultCallback);
//                GalleryFinal.openGallerySingle(REQUEST_CODE_GALLERY, functionConfig, onHanlderResultCallback);
                break;
            case R.id.btn_camear:

                GalleryFinal.openCamera(REQUEST_CODE_CAMERA, functionConfig, onHanlderResultCallback);
                break;
            case R.id.btn_crop:

                break;
            case R.id.btn_edit:
                break;
            case R.id.btn_cancel:

                fingerTapEvent();
                break;
        }
    }

    private GalleryFinal.OnHanlderResultCallback onHanlderResultCallback = new GalleryFinal.OnHanlderResultCallback() {
        @Override
        public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
            Toast.makeText(GalleryActivity.this, resultList.size() + "", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onHanlderFailure(int requestCode, String errorMsg) {
            Toast.makeText(GalleryActivity.this, errorMsg, Toast.LENGTH_SHORT).show();
            Log.e("onHanlderFailure", errorMsg);
        }
    };


// 实现双击和单击

    private final int NUM_DOUBLE_CLICK = 2;
    private final int NUM_SINGLE_CLICK = 1;
    private final int NUM_NO_CLICK = 0;

    private long mLastTapTimeMillis;
    private int mTapCount;
    private boolean mIsDoubleClick;

    public void fingerTapEvent() {


        mTapCount++;

        long currentTapTimeMillis = System.currentTimeMillis();

        if (currentTapTimeMillis - mLastTapTimeMillis < 500) {

            if (mTapCount == NUM_DOUBLE_CLICK) {

                Log.e("----", "双击");
                mIsDoubleClick = true;
                mTapCount = NUM_NO_CLICK;
            }

        }

        mLastTapTimeMillis = currentTapTimeMillis;

        mTapHandler.removeCallbacks(mCheckTapRunnable);
        mTapHandler.postDelayed(mCheckTapRunnable, 500);


    }

    private Handler mTapHandler = new Handler();
    private Runnable mCheckTapRunnable = new Runnable() {
        @Override
        public void run() {

            if (mTapCount == NUM_SINGLE_CLICK && !mIsDoubleClick) {

                Log.e("----", "单击");
            }

            mIsDoubleClick = false;
            mTapCount = NUM_NO_CLICK;
        }
    };
}
