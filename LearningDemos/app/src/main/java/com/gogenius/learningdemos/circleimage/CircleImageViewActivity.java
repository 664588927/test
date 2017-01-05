package com.gogenius.learningdemos.circleimage;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.gogenius.learningdemos.R;
import com.gogenius.learningdemos.view.CircleImageView;
import com.squareup.picasso.Picasso;

/**
 * Created by shijiwei on 2016/11/5.
 */
public class CircleImageViewActivity extends Activity {

    private CircleImageView circleImageView;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_image);
        circleImageView  = (CircleImageView) findViewById(R.id.image);
        imageView = (ImageView) findViewById(R.id.image1);

        Picasso.with(this).load("http://i.imgur.com/DvpvklR.png").placeholder(R.mipmap.cat).error(R.mipmap.sea).into(circleImageView);
        Glide.with(this).load("http://i.imgur.com/DvpvklR.png").listener(new RequestListener<String, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
               imageView.setImageDrawable(resource);
                return false;
            }
        }).placeholder(R.mipmap.cat).error(R.mipmap.sea);
    }
}
