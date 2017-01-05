package com.gogenius.learningdemos.banner;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.gogenius.learningdemos.R;
import com.gogenius.learningdemos.gallery.GalleryDisplayActivity;
import com.squareup.picasso.Picasso;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerClickListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import cn.finalteam.galleryfinal.PhotoPreviewActivity;
import cn.finalteam.galleryfinal.model.PhotoInfo;

/**
 * Created by shijiwei on 2016/10/7.
 */
public class BannerActivity extends Activity {

    private Banner mBanner;
    private ArrayList<String> mBannerUrls;
    private List<String> mBannerTitles;

    private ArrayList<PhotoInfo> mSelectPhotoList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);
        initialize();
    }

    private void initialize() {

        mBannerUrls = new ArrayList<>();
        mBannerUrls.add("http://g.hiphotos.baidu.com/image/h%3D200/sign=593f67d3c0fcc3ceabc0ce33a244d6b7/1f178a82b9014a90e82cad4ca1773912b31bee35.jpg");
        mBannerUrls.add("http://desk.fd.zol-img.com.cn/t_s960x600c5/g4/M01/0D/04/Cg-4WVP_npmIY6GRAKcKYPPMR3wAAQ8LgNIuTMApwp4015.jpg");
        mBannerUrls.add("http://img.pconline.com.cn/images/upload/upc/tx/wallpaper/1609/27/c0/27587202_1474952311163_800x600.jpg");
        mBannerUrls.add("http://desk.fd.zol-img.com.cn/t_s960x600c5/g3/M0B/00/0F/Cg-4WFRsCFKIDGOGAAgP2nRuX54AARgugJ3uFkACA_y761.jpg");
        mBannerUrls.add("http://img3.duitang.com/uploads/item/201609/12/20160912192529_SXazQ.thumb.700_0.jpeg");

        for (int i = 0; i < mBannerUrls.size(); i++) {
            PhotoInfo photoInfo = new PhotoInfo();
            photoInfo.setPhotoPath(mBannerUrls.get(i));
            mSelectPhotoList.add(photoInfo);
        }

        mBannerTitles = new ArrayList<>();
        mBannerTitles.add("今天打折了");
        mBannerTitles.add("小心骨折");
        mBannerTitles.add("跳樓了");
        mBannerTitles.add("老板娘不卖");
        mBannerTitles.add("哈哈哈哈哈");

        mBanner = (Banner) findViewById(R.id.banner);
        //设置图片加载器
        mBanner.setImageLoader(new PicassoImageLoader());
        //设置图片集合
        mBanner.setImages(mBannerUrls);
        //设置banner样式
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        //设置banner动画效果
        mBanner.setBannerAnimation(Transformer.DepthPage);
        //设置标题集合（当banner样式有显示title时）
//        mBanner.setBannerTitles(mBannerTitles);
        //设置自动轮播，默认为true
        mBanner.isAutoPlay(true);
        //设置轮播时间
        mBanner.setDelayTime(1500);
        //设置指示器位置（当banner模式中有指示器时）
        mBanner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        mBanner.start();

        mBanner.setOnBannerClickListener(new OnBannerClickListener() {
            @Override
            public void OnBannerClick(int position) {
                //下标是从1开始

                Toast.makeText(BannerActivity.this, " " + position, Toast.LENGTH_SHORT).show();


                Intent intent = new Intent(BannerActivity.this, GalleryDisplayActivity.class);
                intent.putExtra("urls", mBannerUrls);
                intent.putExtra("position", position - 1);
                startActivity(intent);

            }
        });
    }


}



