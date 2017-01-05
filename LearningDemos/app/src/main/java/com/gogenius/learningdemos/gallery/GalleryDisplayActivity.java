package com.gogenius.learningdemos.gallery;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gogenius.learningdemos.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import cn.finalteam.galleryfinal.widget.GFViewPager;
import cn.finalteam.galleryfinal.widget.zoonview.PhotoView;

/**
 * Created by shijiwei on 2016/10/7.
 */
public class GalleryDisplayActivity extends FragmentActivity
        implements ViewPager.OnPageChangeListener {

    private FrameLayout mContainer;
    private TextView mNumOfPictureLable;
    private GFViewPager mViewPager;
    private GalleryDisplayAdapter mGalleryDisplayAdapter;
    private List<ImageView> mImageViewSet;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_display);

        initialize();

    }

    private void initialize() {

        mContainer = (FrameLayout) findViewById(R.id.container);
        mNumOfPictureLable = (TextView) findViewById(R.id.lable);
        mViewPager = (GFViewPager) findViewById(R.id.viewpager);
        mImageViewSet = new ArrayList<>();
        mGalleryDisplayAdapter = new GalleryDisplayAdapter(mImageViewSet);
        mViewPager.setAdapter(mGalleryDisplayAdapter);
        mViewPager.addOnPageChangeListener(this);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        Intent requstIntent = getIntent();
        ArrayList<String> urls = requstIntent.getStringArrayListExtra("urls");
        int position = requstIntent.getIntExtra("position", 0);

        if (urls == null || urls.size() == 0)
            return;

        for (int i = 0; i < urls.size(); i++) {
            ImageView imageView = new PhotoView(this);
            imageView.setLayoutParams(lp);
            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            Picasso.with(this).load(urls.get(i)).into(imageView);
            mImageViewSet.add(imageView);

        }

        mViewPager.setCurrentItem(position);
        mGalleryDisplayAdapter.notifyDataSetChanged();
        mNumOfPictureLable.setText((position + 1) + "/" + mImageViewSet.size());

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

        mNumOfPictureLable.setText((position + 1) + "/" + mImageViewSet.size());

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    class GalleryDisplayAdapter extends PagerAdapter {

        private List<ImageView> mPhotoSet;

        public GalleryDisplayAdapter(List<ImageView> photoSet) {
            this.mPhotoSet = photoSet;
        }

        @Override
        public int getCount() {
            return mPhotoSet.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mPhotoSet.get(position));
            return mPhotoSet.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mPhotoSet.get(position));
        }
    }

}
