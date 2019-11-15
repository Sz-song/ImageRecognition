package com.yuanyu.imagerecognition.social;



import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;



public class SocialFragmentAdapter extends FragmentPagerAdapter {
    private String[] mTitles = new String[]{"贴子","识别","文章"};
    private FragmentManager fm;
    public SocialFragmentAdapter(FragmentManager fm){
        super(fm);
        this.fm=fm;
    }
    @Override
    public Fragment getItem(int position) {
        if (position==0)return new TieziFragment();
        else if (position==1)return new ShibieFragment();
        else if (position==2){
            return new WenzhangFragment();
        }

        return null;
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position){
        return mTitles[position];
    }
}
