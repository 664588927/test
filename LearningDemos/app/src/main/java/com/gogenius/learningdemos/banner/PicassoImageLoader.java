package com.gogenius.learningdemos.banner;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.youth.banner.loader.ImageLoader;

/**
 * Created by shijiwei on 2016/10/7.
 */
public class PicassoImageLoader implements ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {

        Picasso.with(context).load((String) path).into(imageView);
    }
}
