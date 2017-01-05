package com.gogenius.learningdemos;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.gogenius.learningdemos.ItemTouch.ItemTouchActivity;
import com.gogenius.learningdemos.PullToRefreshSwipeMenuListView.PullToRefreshSwipeMenuListViewActivity;
import com.gogenius.learningdemos.TabLayyout.TabLayoutActivity;
import com.gogenius.learningdemos.action.FeedbackActivity;
import com.gogenius.learningdemos.action.SearchActivity;
import com.gogenius.learningdemos.annotations.Test;
import com.gogenius.learningdemos.annotations.TestBean;
import com.gogenius.learningdemos.banner.BannerActivity;
import com.gogenius.learningdemos.camera.CameraActivity;
import com.gogenius.learningdemos.cardlist.CardListActivity;
import com.gogenius.learningdemos.circleimage.CircleImageViewActivity;
import com.gogenius.learningdemos.coordinatorlayout.behavior.BehaviorActivity;
import com.gogenius.learningdemos.designnewfeature.DesignNewFeatureActivity;
import com.gogenius.learningdemos.designnewfeature.ProjectDemoActivity;
import com.gogenius.learningdemos.dialogUI.DialogMainActivity;
import com.gogenius.learningdemos.gallery.GalleryActivity;
import com.gogenius.learningdemos.game2048.Game2048Activity;
import com.gogenius.learningdemos.gesture.GestureActivity;
import com.gogenius.learningdemos.keywordsynchronized.SynchronizedActivity;
import com.gogenius.learningdemos.matrix.MatrixActivity;
import com.gogenius.learningdemos.menuscroll.MenuScrollActivity;
import com.gogenius.learningdemos.multiService.LocalService;
import com.gogenius.learningdemos.multiService.RemoteService;
import com.gogenius.learningdemos.okhttp.OkHttpActivity;
import com.gogenius.learningdemos.parallax.ParallaxActivity;
import com.gogenius.learningdemos.pathAnimation.PathAnimationActivity;
import com.gogenius.learningdemos.picker.PickerActivity;
import com.gogenius.learningdemos.ripple.RippleActivity;
import com.gogenius.learningdemos.sortListView.SortListViewActivity;
import com.gogenius.learningdemos.swipemenulistview.SwipeMenuListViewActivity;
import com.gogenius.learningdemos.velocitytracker.VelocityTrackerActivity;
import com.gogenius.learningdemos.xRefreshView.XRefreshViewActivity;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by shijiwei on 2016/9/6.
 */
public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {


    private Context mContext;

    private ListView mMainMenuList;
    private ImageView mBackgroud;
    private ArrayAdapter<String> mMainMenuAdapter;

    private List<Class> dataSet;

    private String[] names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;

        initialMenu();

        setValues("A", "B", "C", "D", "E", "F");

        for (int i = 0; i < names.length; i++)
            Log.e("MainActivity", "  value " + names[i]);


        startService(new Intent(this, LocalService.class));
        startService(new Intent(this, RemoteService.class));

        Intent intent = getIntent();
        if (intent != null) {
            if (intent.getAction().equalsIgnoreCase("1"))
                Log.e("MainActivity", getIntent() == null ? " null" : getIntent().getStringExtra("name"));
        }

        TestBean testBean = new TestBean();
        Class<? extends TestBean> aClass = testBean.getClass();
       for (Field field : aClass.getFields()){
           if (field.isAnnotationPresent(Test.class)){
               Test test = field.getAnnotation(Test.class);
               Log.e("----",field.getName() + " , " + test.id() + " , " + test.name());
           }
       }


    }

    private void initialMenu() {

        mBackgroud = (ImageView) findViewById(R.id.iv_backgroud);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.translate_anim);
        mBackgroud.startAnimation(animation);

        mMainMenuList = (ListView) findViewById(R.id.main_menu_list);
        //        mMainMenuAdapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_list_item_1);
        mMainMenuAdapter = new ArrayAdapter<String>(mContext, R.layout.item_translate_single_text);
        mMainMenuList.setAdapter(mMainMenuAdapter);
        mMainMenuList.setOnItemClickListener(this);

        dataSet = new ArrayList<>();
        //仿QQ侧滑删除
        dataSet.add(SwipeMenuListViewActivity.class);
        dataSet.add(ItemTouchActivity.class);
        dataSet.add(BehaviorActivity.class);
        dataSet.add(DesignNewFeatureActivity.class);
        dataSet.add(ProjectDemoActivity.class);
        dataSet.add(TabLayoutActivity.class);
        dataSet.add(CardListActivity.class);
        dataSet.add(ParallaxActivity.class);
        dataSet.add(OkHttpActivity.class);
        dataSet.add(SynchronizedActivity.class);
        dataSet.add(DialogMainActivity.class);
        dataSet.add(FeedbackActivity.class);
        dataSet.add(ParallaxActivity.class);
        dataSet.add(XRefreshViewActivity.class);
        dataSet.add(MenuScrollActivity.class);
        dataSet.add(BannerActivity.class);
        dataSet.add(GalleryActivity.class);
        dataSet.add(GestureActivity.class);
        dataSet.add(PathAnimationActivity.class);
        dataSet.add(Game2048Activity.class);
        dataSet.add(VelocityTrackerActivity.class);
        dataSet.add(RippleActivity.class);
        dataSet.add(SortListViewActivity.class);
        dataSet.add(PullToRefreshSwipeMenuListViewActivity.class);
        dataSet.add(CircleImageViewActivity.class);
        dataSet.add(SearchActivity.class);
        dataSet.add(CameraActivity.class);
        dataSet.add(FlyingLetterActivity.class);
        dataSet.add(PickerActivity.class);
        dataSet.add(MatrixActivity.class);


        for (int i = 0; i < dataSet.size(); i++)
            mMainMenuAdapter.add(dataSet.get(i).getSimpleName());

        mMainMenuAdapter.notifyDataSetChanged();

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long resourceId) {
        startActivity(new Intent(mContext, dataSet.get(position)));
    }


    /**
     * test the variable number of arguments
     * @param names
     */
    public void setValues(String... names) {

        this.names = names;
    }
}
