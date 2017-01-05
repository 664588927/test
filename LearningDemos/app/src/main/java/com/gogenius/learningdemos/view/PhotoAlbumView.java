package com.gogenius.learningdemos.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shijiwei on 2016/9/13.
 */
public class PhotoAlbumView extends FrameLayout {

    private Context mContext;

    private List<PhotoView> mPhotoSet;

    public PhotoAlbumView(Context context) {
        super(context);

        mContext = context;

        initialize();
    }

    public PhotoAlbumView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mContext = context;

        initialize();
    }

    public PhotoAlbumView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mContext = context;

        initialize();
    }


    private void initialize() {

        mPhotoSet = new ArrayList<>();
    }

    public void setmPhotos(List<Bitmap> photos) {

        for (int i = 0; i < photos.size(); i++){
            PhotoView p = new PhotoView(mContext, photos.get(i));
        }



    }


    private class PhotoView extends CardView {

        private int mDefaultWidth = 200;
        private int mDefaultHeight = 300;

        private ImageView mPhoto;

        public PhotoView(Context context, Bitmap photo) {
            super(context);

            initialize(context, photo);
        }

        private void initialize(Context context, Bitmap photo) {


            mPhoto = new ImageView(context);
            mPhoto.setImageBitmap(photo);
            CardView.LayoutParams l = new CardView.LayoutParams(mDefaultWidth, mDefaultHeight);
            mPhoto.setLayoutParams(l);
            addView(mPhoto);

        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {

            switch (event.getAction()) {

                case MotionEvent.ACTION_DOWN:


                    break;
                case MotionEvent.ACTION_UP:


                    break;
                case MotionEvent.ACTION_MOVE:

                    setX(event.getRawX() - getWidth() / 2);

                    break;
            }

            return true;
        }
    }


}
