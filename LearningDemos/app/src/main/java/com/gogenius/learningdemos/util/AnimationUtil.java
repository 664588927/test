package com.gogenius.learningdemos.util;

import android.animation.ObjectAnimator;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shijiwei on 2016/5/31.
 * <p>
 * 属性动画封装
 */
public class AnimationUtil {

    private final String[] modeNames = {"alpha",
            "rotation", "rotationX", "rotationY",
            "translationX", "translationY", "scaleX", "scaleY"};

    private static List<AnimationUtil> instanceList = new ArrayList<>();
    private View targetView;

    private AnimationUtil(View targetView) {
        this.targetView = targetView;
    }

    private void addAnimation(String modeName, long duration, float... values) {
        try {
            ObjectAnimator animator = ObjectAnimator.ofFloat(targetView, modeName, values);
            animator.setDuration(duration);
            animator.start();
        } catch (Exception e) {
            if (targetView == null)
                throw new UnsupportedOperationException("targetView is null");
        }
    }

    /**
     * 绑定控件   可以自己选择保存对象与否
     */
    public static AnimationUtil bindView(View targetView) {
//        for (int i = 0; i < instanceList.size(); i++) {
//            if (instanceList.get(i).targetView.getId() == targetView.getId()) {
//                return instanceList.get(i);
//            }
//        }
//        instanceList.add(new AnimationUtil(targetView));
//        return instanceList.get(instanceList.size() - 1);
        return new AnimationUtil(targetView);
    }

    /**
     * 释放控件动画
     */
    public AnimationUtil clear() {
        this.targetView.clearAnimation();
        return this;
    }

    /**
     * 透明度
     */
    public AnimationUtil setAlpha(long duration, float... values) {
        addAnimation(modeNames[0], duration, values);
        return this;
    }

    /**
     * 旋转
     */
    public AnimationUtil setRotation(long duration, float... values) {
        addAnimation(modeNames[1], duration, values);
        return this;
    }

    /**
     * 围绕X轴旋转
     */
    public AnimationUtil setRotationX(long duration, float... values) {
        addAnimation(modeNames[2], duration, values);
        return this;
    }

    /**
     * 围绕Y轴旋转
     */
    public AnimationUtil setRotationY(long duration, float... values) {
        addAnimation(modeNames[3], duration, values);
        return this;
    }

    /**
     * X轴平移
     */
    public AnimationUtil setTranslationX(long duration, float... values) {
        addAnimation(modeNames[4], duration, values);
        return this;
    }

    /**
     * Y轴平移
     */
    public AnimationUtil setTranslationY(long duration, float... values) {
        addAnimation(modeNames[5], duration, values);
        return this;
    }

    /**
     * X轴缩放（按倍数缩放）
     */
    public AnimationUtil setScaleX(long duration, float... values) {
        addAnimation(modeNames[6], duration, values);
        return this;
    }

    /**
     * Y轴缩放（按倍数缩放）
     */
    public AnimationUtil setScaleY(long duration, float... values) {
        addAnimation(modeNames[7], duration, values);
        return this;
    }


}
