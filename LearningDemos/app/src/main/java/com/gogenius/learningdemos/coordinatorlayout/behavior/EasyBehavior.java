package com.gogenius.learningdemos.coordinatorlayout.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.gogenius.learningdemos.view.FlyCardVie;

/**
 * Created by shijiwei on 2016/9/9.
 */
public class EasyBehavior extends CoordinatorLayout.Behavior<FlyCardVie> {//这里的泛型是child的类型，也就是观察者View

    public EasyBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, FlyCardVie child, View dependency) {
        //告知监听的dependency是Button
        return dependency instanceof FlyCardVie;
    }

    @Override
    //当 dependency(Button)变化的时候，可以对child(TextView)进行操作
    public boolean onDependentViewChanged(CoordinatorLayout parent, FlyCardVie child, View dependency) {

        Log.e("EasyBehavior","onDependentViewChanged");
        child.setX(dependency.getX() + 6);
        child.setY(dependency.getY() + 20);

        return true;
    }
}
