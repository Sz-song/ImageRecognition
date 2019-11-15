package com.yuanyu.imagerecognition;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yuanyu.imagerecognition.social.SocialFragment;
import com.yuanyu.imagerecognition.wiki.WikiFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.fragment_container)
    FrameLayout fragmentContainer;
    @BindView(R.id.img_shejiao)
    ImageView imgShejiao;
    @BindView(R.id.shejiao)
    TextView shejiao;
    @BindView(R.id.navigation_shejiao)
    LinearLayout navigationShejiao;
    @BindView(R.id.img_shibie)
    ImageView imgShibie;
    @BindView(R.id.shibie)
    TextView shibie;
    @BindView(R.id.navigation_shibie)
    LinearLayout navigationShibie;
    @BindView(R.id.img_baike)
    ImageView imgBaike;
    @BindView(R.id.baike)
    TextView baike;
    @BindView(R.id.navigation_baike)
    LinearLayout navigationBaike;
    @BindView(R.id.img_wode)
    ImageView imgWode;
    @BindView(R.id.wode)
    TextView wode;
    @BindView(R.id.navigation_wode)
    LinearLayout navigationWode;
    private FragmentManager fragmentManager;
    private SocialFragment fragment1;
    private WikiFragment fragment2;
    private WodeFragment fragment3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        fragmentManager = getSupportFragmentManager();
        setDefaultFragment();
    }

    @OnClick({R.id.navigation_baike, R.id.navigation_shejiao, R.id.navigation_wode})
    public void change(View view) {
        changeFragment(view);
    }

    @OnClick(R.id.navigation_shibie)
    public void camera() {
        Intent intent = new Intent(MainActivity.this, CameraActivity.class);
        startActivity(intent);
    }

    private void changeFragment(View view) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (fragment1 != null) {
            fragmentTransaction.hide(fragment1);
        }
        if (fragment2 != null) {
            fragmentTransaction.hide(fragment2);
        }

        if (fragment3 != null) {
            fragmentTransaction.hide(fragment3);
        }
        switch (view.getId()) {
            case R.id.navigation_shejiao:
                if (fragment1 == null) {
                    fragment1 = new SocialFragment();
                    fragmentTransaction.add(R.id.fragment_container, fragment1);
                } else fragmentTransaction.show(fragment1);
                fragmentTransaction.commit();
                changeImg(0);
                break;
            case R.id.navigation_baike:
                if (fragment2 == null) {
                    fragment2 = new WikiFragment();
                    fragmentTransaction.add(R.id.fragment_container, fragment2);
                } else fragmentTransaction.show(fragment2);
                fragmentTransaction.commit();
                changeImg(2);
                break;
            case R.id.navigation_wode:
                if (fragment3 == null) {
                    fragment3 = new WodeFragment();
                    fragmentTransaction.add(R.id.fragment_container, fragment3);
                } else fragmentTransaction.show(fragment3);
                fragmentTransaction.commit();
                changeImg(3);
                break;

        }
    }

    private void setDefaultFragment() {
        if (fragment1 == null) {
            fragment1 = new SocialFragment();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.add(R.id.fragment_container, fragment1);
            transaction.commit();
            shejiao.setTextColor(getResources().getColor(R.color.darkGray));
            imgShejiao.setImageResource(R.mipmap.nav_shejiao_focus);
        }

    }
    private void changeImg(int type){
        shejiao.setTextColor(getResources().getColor(R.color.gray));
        imgShejiao.setImageResource(R.mipmap.nav_shejiao);
        shibie.setTextColor(getResources().getColor(R.color.gray));
        imgShibie.setImageResource(R.mipmap.nav_shibie);
        baike.setTextColor(getResources().getColor(R.color.gray));
        imgBaike.setImageResource(R.mipmap.nav_baike);
        wode.setTextColor(getResources().getColor(R.color.gray));
        imgWode.setImageResource(R.mipmap.nav_wode);
        switch (type){
            case 0:
                shejiao.setTextColor(getResources().getColor(R.color.darkGray));
                imgShejiao.setImageResource(R.mipmap.nav_shejiao_focus);
                break;
            case 2:
                baike.setTextColor(getResources().getColor(R.color.darkGray));
                imgBaike.setImageResource(R.mipmap.nav_baike_focus);
                break;
            case 3:
                wode.setTextColor(getResources().getColor(R.color.darkGray));
                imgWode.setImageResource(R.mipmap.nav_wode_focus);
                break;
        }
    }
}
