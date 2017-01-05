package com.gogenius.learningdemos.matrix;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.gogenius.learningdemos.R;

/**
 * Created by shijiwei on 2017/1/5.
 */
public class MatrixActivity extends Activity {

    private Matrix mMatrix;
    private ImageView source;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_matrix);
        mMatrix = new Matrix();
        source = (ImageView) findViewById(R.id.image);
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.bg);
        source.setImageBitmap(bitmap);
    }

    public void onClick(View v) {
        mMatrix.postScale(0.5f, 2f, source.getWidth() / 2, source.getHeight() / 2);
        source.setImageMatrix(mMatrix);
    }
}
