package com.dafen.xuejie.activity.shouye;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dafen.xuejie.R;
import com.dafen.xuejie.view.MyScrollView;

import java.util.ArrayList;


public class BerserkActivity extends Activity implements View.OnClickListener,RadioGroup.OnCheckedChangeListener {
    private ImageView iv_back; //返回
    private RadioGroup           mRadioGroup;
    private RadioButton          mRadioButton1;
    private RadioButton          mRadioButton2;
    private RadioButton          mRadioButton3;
    private RadioButton          mRadioButton4;
    private RadioButton          mRadioButton5;
    private ImageView            mImageView;
    private float                mCurrentCheckedRadioLeft;
    private HorizontalScrollView mHorizontalScrollView;
    private ViewPager            mViewPager;
    private ArrayList<View>      mViews;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_berserk);
        iniController();
        iniListener();
        iniVariable();
        mRadioButton1.setChecked(true);
        mViewPager.setCurrentItem(1);
        mCurrentCheckedRadioLeft = getCurrentCheckedRadioLeft();
    }
    /**
     * 实例化控件
     */
    public void iniController() {
        iv_back = (ImageView) findViewById(R.id.iv_back);
        //横向滑动
        mRadioGroup = (RadioGroup)findViewById(R.id.radioGroup);
        mRadioButton1 = (RadioButton)findViewById(R.id.btn1);
        mRadioButton2 = (RadioButton)findViewById(R.id.btn2);
        mRadioButton3 = (RadioButton)findViewById(R.id.btn3);
        mRadioButton4 = (RadioButton)findViewById(R.id.btn4);
        mRadioButton5 = (RadioButton)findViewById(R.id.btn5);
        mImageView = (ImageView)findViewById(R.id.img1);
        mHorizontalScrollView = (HorizontalScrollView)findViewById(R.id.horizontalScrollView);
        mViewPager = (ViewPager)findViewById(R.id.pager);

    }

    /**
     * 实例化监听
     */
    public void iniListener() {
        iv_back.setOnClickListener(this);//返回
        //横向滑动
        mRadioGroup.setOnCheckedChangeListener(this);
        mViewPager.setOnPageChangeListener(new MyPagerOnPageChangeListener());
    }

    /**
     * 数据
     */
    public void iniVariable() {
        mViews = new ArrayList<View>();
        mViews.add(getLayoutInflater().inflate(R.layout.layout_0, null));
        mViews.add(getLayoutInflater().inflate(R.layout.layout_1, null));
        mViews.add(getLayoutInflater().inflate(R.layout.layout_2, null));
        mViews.add(getLayoutInflater().inflate(R.layout.layout_3, null));
        mViews.add(getLayoutInflater().inflate(R.layout.layout_4, null));
        mViews.add(getLayoutInflater().inflate(R.layout.layout_5, null));
        mViews.add(getLayoutInflater().inflate(R.layout.layout_0, null));

        mViewPager.setAdapter(new MyPagerAdapter());
    }
    private float getCurrentCheckedRadioLeft() {
        // TODO Auto-generated method stub
        if (mRadioButton1.isChecked()) {
            return getResources().getDimension(R.dimen.rdo1);
        }else if (mRadioButton2.isChecked()) {
            return getResources().getDimension(R.dimen.rdo2);
        }else if (mRadioButton3.isChecked()) {
            return getResources().getDimension(R.dimen.rdo3);
        }else if (mRadioButton4.isChecked()) {
            return getResources().getDimension(R.dimen.rdo4);
        }else if (mRadioButton5.isChecked()) {
            return getResources().getDimension(R.dimen.rdo5);
        }
        return 0f;
    }

    /**
     * adapter
     */
    private class MyPagerAdapter extends PagerAdapter {

        @Override
        public void destroyItem(View v, int position, Object obj) {
            // TODO Auto-generated method stub
            ((ViewPager)v).removeView(mViews.get(position));
        }

        @Override
        public void finishUpdate(View arg0) {
            // TODO Auto-generated method stub

        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return mViews.size();
        }

        @Override
        public Object instantiateItem(View v, int position) {
            ((ViewPager)v).addView(mViews.get(position));
            return mViews.get(position);
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            // TODO Auto-generated method stub
            return arg0 == arg1;
        }

        @Override
        public void restoreState(Parcelable arg0, ClassLoader arg1) {
            // TODO Auto-generated method stub

        }

        @Override
        public Parcelable saveState() {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public void startUpdate(View arg0) {
            // TODO Auto-generated method stub

        }

    }

    private class MyPagerOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int arg0) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
            // TODO Auto-generated method stub

        }
        /**
         * ��
         */
        @Override
        public void onPageSelected(int position) {
            // TODO Auto-generated method stub

            if (position == 0) {
                mViewPager.setCurrentItem(1);
            }else if (position == 1) {
                mRadioButton1.performClick();
            }else if (position == 2) {
                mRadioButton2.performClick();
            }else if (position == 3) {
                mRadioButton3.performClick();
            }else if (position == 4) {
                mRadioButton4.performClick();
            }else if (position == 5) {
                mRadioButton5.performClick();
            }else if (position == 6) {
                mViewPager.setCurrentItem(5);
            }
        }

    }

    /**
     * 滑动距离的计算
     * @param group
     * @param checkedId
     */
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        AnimationSet _AnimationSet = new AnimationSet(true);
        TranslateAnimation _TranslateAnimation;
        if (checkedId == R.id.btn1) {
            _TranslateAnimation = new TranslateAnimation(mCurrentCheckedRadioLeft, getResources().getDimension(R.dimen.rdo1), 0f, 0f);
            _AnimationSet.addAnimation(_TranslateAnimation);
            _AnimationSet.setFillBefore(false);
            _AnimationSet.setFillAfter(true);
            _AnimationSet.setDuration(100);

            mImageView.startAnimation(_AnimationSet);
            mViewPager.setCurrentItem(1);
        }else if (checkedId == R.id.btn2) {
            _TranslateAnimation = new TranslateAnimation(mCurrentCheckedRadioLeft, getResources().getDimension(R.dimen.rdo2), 0f, 0f);

            _AnimationSet.addAnimation(_TranslateAnimation);
            _AnimationSet.setFillBefore(false);
            _AnimationSet.setFillAfter(true);
            _AnimationSet.setDuration(100);

            mImageView.startAnimation(_AnimationSet);

            mViewPager.setCurrentItem(2);
        }else if (checkedId == R.id.btn3) {
            _TranslateAnimation = new TranslateAnimation(mCurrentCheckedRadioLeft, getResources().getDimension(R.dimen.rdo3), 0f, 0f);

            _AnimationSet.addAnimation(_TranslateAnimation);
            _AnimationSet.setFillBefore(false);
            _AnimationSet.setFillAfter(true);
            _AnimationSet.setDuration(100);


            mImageView.startAnimation(_AnimationSet);

            mViewPager.setCurrentItem(3);
        }else if (checkedId == R.id.btn4) {
            _TranslateAnimation = new TranslateAnimation(mCurrentCheckedRadioLeft, getResources().getDimension(R.dimen.rdo4), 0f, 0f);

            _AnimationSet.addAnimation(_TranslateAnimation);
            _AnimationSet.setFillBefore(false);
            _AnimationSet.setFillAfter(true);
            _AnimationSet.setDuration(100);

            mImageView.startAnimation(_AnimationSet);
            mViewPager.setCurrentItem(4);
        }else if (checkedId == R.id.btn5) {
            _TranslateAnimation = new TranslateAnimation(mCurrentCheckedRadioLeft, getResources().getDimension(R.dimen.rdo5), 0f, 0f);

            _AnimationSet.addAnimation(_TranslateAnimation);
            _AnimationSet.setFillBefore(false);
            _AnimationSet.setFillAfter(true);
            _AnimationSet.setDuration(100);

            //mImageView.bringToFront();
            mImageView.startAnimation(_AnimationSet);

            mViewPager.setCurrentItem(5);
        }
        mCurrentCheckedRadioLeft = getCurrentCheckedRadioLeft();


        mHorizontalScrollView.smoothScrollTo((int)mCurrentCheckedRadioLeft-(int)getResources().getDimension(R.dimen.rdo2), 0);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //返回按钮
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
