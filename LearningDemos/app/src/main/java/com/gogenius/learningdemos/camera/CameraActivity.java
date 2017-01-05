package com.gogenius.learningdemos.camera;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.ImageFormat;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.gogenius.learningdemos.R;
import com.gogenius.learningdemos.util.BitmapUtil;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by shijiwei on 2016/11/22.
 */
public class CameraActivity extends Activity implements SurfaceHolder.Callback, Camera.PictureCallback {


    private static final String TAG = "CameraActivity";
    private final static float initCamerRatioW2H = 1.33f;

    private SurfaceView surfaceView;
    private ImageView mPicture;
    private Camera camera;
    private boolean isPreview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        //设置高亮
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        surfaceView = (SurfaceView) findViewById(R.id.surfacxe_view);
        mPicture = (ImageView) findViewById(R.id.iv_picture);

        //下面设置surfaceView不维护自己的缓冲区,而是等待屏幕的渲染引擎将内容推送到用户面前
        surfaceView.getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        surfaceView.getHolder().addCallback(this);
    }

    public void onTakePhoto(View view) {
        camera.takePicture(null, null, this);//将拍到的照片给第三个对象中，这里的TakePictureCallback()是自己定义的，在下面的代码中
    }

    @Override
    public void onPictureTaken(byte[] data, Camera camera) {
        Bitmap bitmap = BitmapUtil.Bytes2Bitmap(data);
        mPicture.setImageBitmap(bitmap);
        mPicture.setVisibility(View.VISIBLE);
        Log.e(TAG," onPictureTaken mPicture.width = " + mPicture.getWidth() + " mPicture.height = " + mPicture.getHeight());

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mPicture.setImageBitmap(null);
                mPicture.setVisibility(View.GONE);
            }
        },2 *1000);
        camera.stopPreview();
        camera.startPreview();//处理完数据之后可以预览

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        Log.e(TAG, "surfaceCreated");
        try {
            camera = Camera.open();//打开硬件摄像头，这里导包得时候一定要注意是android.hardware.Camera

            setParameters(camera);

            camera.setDisplayOrientation(90);
            camera.setPreviewDisplay(surfaceView.getHolder());//通过SurfaceView显示取景画面
            camera.startPreview();//开始预览
            camera.autoFocus(null);//自动对焦
            isPreview = true;//设置是否预览参数为真

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Log.e(TAG, "surfaceChanged  format = " + format + " width = " + width + " height = " + height + " w/h = " + (float) height / width);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.e(TAG, "surfaceDestroyed");
        if (camera != null) {
            if (isPreview) {//如果正在预览
                camera.stopPreview();
                camera.release();
            }
        }
    }


    private void setParameters(Camera camera) {

        int surfaceWidth = surfaceView.getWidth();
        int surfaceHeight = surfaceView.getHeight();

        //得到摄像头的参数
        Camera.Parameters parameters = camera.getParameters();
        //先将获取手机支持预览的尺寸列表通过方法
        List<Camera.Size> camerSizeList = parameters.getSupportedPreviewSizes();
        List<Camera.Size> sameRationCamerSize = new ArrayList<>();
        float tempCamerRatioW2H;
        int bestX = 0;
        int bestY = 0;
        int diff = Integer.MAX_VALUE;
        if (camerSizeList.size() > 0) {
            for (int i = 0; i < camerSizeList.size(); i++) {
                Log.e(TAG, "size " + (i + 1)
                        + " ,width = " + camerSizeList.get(i).width
                        + " ,height = " + camerSizeList.get(i).height
                        + " ,w/h = " + (float) camerSizeList.get(i).width  / camerSizeList.get(i).height);
                Camera.Size tempCamerSize = camerSizeList.get(i);
                tempCamerRatioW2H = new BigDecimal((float) tempCamerSize.width / tempCamerSize.height).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
                if (tempCamerRatioW2H == initCamerRatioW2H) {
                    sameRationCamerSize.add(tempCamerSize);
                }

                int newX = tempCamerSize.width;
                int newY = tempCamerSize.height;

                int newDiff = Math.abs(newX - (int) (surfaceHeight * initCamerRatioW2H)) + Math.abs(newY - surfaceHeight);

                if (newDiff == 0) {
                    bestX = newX;
                    bestY = newY;
                    break;
                } else if (newDiff < diff) {
                    bestX = newX;
                    bestY = newY;
                    diff = newDiff;
                }
            }
        }

        //根据摄像头分辨率，动态调整视图宽高
        RelativeLayout.LayoutParams surfaceLayoutParams = (RelativeLayout.LayoutParams) surfaceView.getLayoutParams();
        surfaceLayoutParams.height = bestY;
        surfaceLayoutParams.width = bestX;
        surfaceView.setLayoutParams(surfaceLayoutParams);

        RelativeLayout.LayoutParams avatorLayoutParams = (RelativeLayout.LayoutParams) mPicture.getLayoutParams();
        avatorLayoutParams.height = bestY;
        avatorLayoutParams.width = bestX;
        mPicture.setLayoutParams(avatorLayoutParams);

        Collections.sort(sameRationCamerSize, Collections.reverseOrder(new Comparator<Camera.Size>() {
            @Override
            public int compare(Camera.Size o1, Camera.Size o2) {
                return new Integer(o1.width).compareTo(new Integer(o2.width));
            }
        }));
        //同比例取最大分辨率
        Camera.Size myCamerSize = sameRationCamerSize.get(0);
        parameters.setPreviewSize(myCamerSize.width, myCamerSize.height);
        parameters.setPictureFormat(ImageFormat.JPEG);//设置照片的格式
        parameters.setJpegQuality(100);//设置照片的质量
        camera.setParameters(parameters);
    }

}
