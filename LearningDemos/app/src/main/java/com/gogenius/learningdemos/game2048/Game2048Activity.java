package com.gogenius.learningdemos.game2048;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;

import com.gogenius.learningdemos.R;

import java.util.Random;

/**
 * Created by shijiwei on 2016/10/12.
 */
public class Game2048Activity extends Activity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_2048);

        ((GameLayout2048)findViewById(R.id.game_layout)).creatGameItem(4);


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        VelocityTracker velocityTracker = VelocityTracker.obtain();
        velocityTracker.addMovement(event);
        velocityTracker.computeCurrentVelocity(1000);
        float yVelocity = velocityTracker.getYVelocity();
        return super.onTouchEvent(event);
    }

    @Override
    public void onClick(View v) {

    }
}
