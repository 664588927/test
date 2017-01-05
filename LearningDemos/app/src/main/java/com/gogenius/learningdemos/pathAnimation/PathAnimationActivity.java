package com.gogenius.learningdemos.pathAnimation;

import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.gogenius.learningdemos.R;
import com.gogenius.learningdemos.ripple.Titanic;
import com.gogenius.learningdemos.ripple.TitanicTextView;

/**
 * Created by shijiwei on 2016/10/11.
 */
public class PathAnimationActivity extends Activity implements ValueAnimator.AnimatorUpdateListener, View.OnClickListener {

    private ImageView mPathView;
    private DynamicHeartView love;

    private float width, height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path_animation);
        intialize();
    }

    private void intialize() {

        mPathView = (ImageView) findViewById(R.id.iv_point);

        ValueAnimator valueAnimator = ValueAnimator.ofObject(new BezierEvaluator(), new PointF(0, 0), new PointF(300, 800));
        valueAnimator.setDuration(2000);
//        valueAnimator.start();
        valueAnimator.addUpdateListener(this);


        love = (DynamicHeartView) findViewById(R.id.love);

        love.startPathAnim(5 * 1000);

        love.setOnClickListener(this);



    }

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        PointF pointF = (PointF) animation.getAnimatedValue();

        Log.e("onAnimationUpdate", pointF.x + "  " + pointF.y);

        mPathView.setX(pointF.x);
        mPathView.setY(pointF.y);
    }

    @Override
    public void onClick(View v) {
        love.startPathAnim(5 * 1000);
    }


    class BezierEvaluator implements TypeEvaluator<PointF> {

        @Override
        public PointF evaluate(float fraction, PointF startValue,
                               PointF endValue) {
            final float t = fraction;
            float oneMinusT = 1.0f - t;

            Log.e("evaluate", "  " + fraction);

            PointF point = new PointF();

            PointF point0 = startValue;

            PointF point1 = new PointF();
            point1.set(width, 0);

            PointF point2 = new PointF();
            point2.set(0, height);

            PointF point3 = endValue;

            point.x = oneMinusT * oneMinusT * oneMinusT * (point0.x)
                    + 3 * oneMinusT * oneMinusT * t * (point1.x)
                    + 3 * oneMinusT * t * t * (point2.x)
                    + t * t * t * (point3.x);

            point.y = oneMinusT * oneMinusT * oneMinusT * (point0.y)
                    + 3 * oneMinusT * oneMinusT * t * (point1.y)
                    + 3 * oneMinusT * t * t * (point2.y)
                    + t * t * t * (point3.y);
            return point;
        }
    }


}
