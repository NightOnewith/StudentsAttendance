package com.yzj.studentsattendance.activitys;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yzj.studentsattendance.fragments.MeFragment;

import com.yzj.studentsattendance.R;
import com.yzj.studentsattendance.fragments.AttendanceFragment;
import com.yzj.studentsattendance.fragments.CourseFragment;
import com.yzj.studentsattendance.models.MenuTag;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView iv_me;
    private ImageView iv_course;
    private ImageView iv_attend;
    private TextView tv_me;
    private TextView tv_course;
    private TextView tv_attend;
    private LinearLayout layout_me;
    private LinearLayout layout_course;
    private LinearLayout layout_attend;
    private FragmentManager fManager;
    private View[] menuViews = new View[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initialize();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public interface OnMenuClick {
        void onMenuClick();
    }

    private void initViews() {
        iv_me = (ImageView) findViewById(R.id.custom_tab_bar_iv_me);
        iv_course = (ImageView) findViewById(R.id.custom_tab_bar_iv_course);
        iv_attend = (ImageView) findViewById(R.id.custom_tab_bar_iv_attendance);
        tv_me = (TextView) findViewById(R.id.custom_tab_bar_tv_me);
        tv_course = (TextView) findViewById(R.id.custom_tab_bar_tv_course);
        tv_attend = (TextView) findViewById(R.id.custom_tab_bar_tv_attendance);
        layout_me = (LinearLayout) findViewById(R.id.custom_tab_bar_layout_me);
        layout_course = (LinearLayout) findViewById(R.id.custom_tab_bar_layout_course);
        layout_attend = (LinearLayout) findViewById(R.id.custom_tab_bar_attendance);
        menuViews[0] = layout_attend;
        menuViews[1] = layout_course;
        menuViews[2] = layout_me;
        layout_me.setOnClickListener(this);
        layout_course.setOnClickListener(this);
        layout_attend.setOnClickListener(this);
        iv_course.setImageResource(R.drawable.ic_tab_bar_course_normal);
        tv_course.setTextColor(getResources().getColor(R.color.colorTabBarTextNormal));
        iv_me.setImageResource(R.drawable.ic_tab_bar_me_normal);
        tv_me.setTextColor(getResources().getColor(R.color.colorTabBarTextNormal));
    }

    private void initialize() {
        fManager = getSupportFragmentManager();
        FragmentTransaction trans = fManager.beginTransaction();
        AttendanceFragment fragAtt = new AttendanceFragment();
        CourseFragment fragCou = new CourseFragment();
        MeFragment fragMe = new MeFragment();

        MenuTag attTag = new MenuTag();
        attTag.setFragment(fragAtt);
        attTag.setIv(iv_attend);
        attTag.setTv(tv_attend);
        attTag.setNormalImgRes(R.drawable.ic_tab_bar_attendance_pressed);
        attTag.setPressedImgRes(R.drawable.ic_tab_bar_attendance_normal);
        attTag.setNormalTvRes(getResources().getColor(R.color.colorTabBarTextPressed));
        attTag.setPressedTvRes(getResources().getColor(R.color.colorTabBarTextNormal));

        MenuTag meTag = new MenuTag();
        meTag.setFragment(fragMe);
        meTag.setIv(iv_me);
        meTag.setTv(tv_me);
        meTag.setNormalImgRes(R.drawable.ic_tab_bar_me_pressed);
        meTag.setPressedImgRes(R.drawable.ic_tab_bar_me_normal);
        meTag.setNormalTvRes(getResources().getColor(R.color.colorTabBarTextPressed));
        meTag.setPressedTvRes(getResources().getColor(R.color.colorTabBarTextNormal));

        MenuTag couTag = new MenuTag();
        couTag.setFragment(fragCou);
        couTag.setIv(iv_course);
        couTag.setTv(tv_course);
        couTag.setNormalImgRes(R.drawable.ic_tab_bar_course_pressed);
        couTag.setPressedImgRes(R.drawable.ic_tab_bar_course_normal);
        couTag.setNormalTvRes(getResources().getColor(R.color.colorTabBarTextPressed));
        couTag.setPressedTvRes(getResources().getColor(R.color.colorTabBarTextNormal));

        layout_me.setTag(meTag);
        layout_course.setTag(couTag);
        layout_attend.setTag(attTag);
        trans.hide(fragCou).hide(fragMe);
        trans.add(R.id.activity_main_fragment_content, fragAtt);
        trans.add(R.id.activity_main_fragment_content, fragCou);
        trans.add(R.id.activity_main_fragment_content, fragMe);
        iv_attend.setImageResource(R.drawable.ic_tab_bar_attendance_pressed);
        tv_attend.setTextColor(getResources().getColor(R.color.colorTabBarTextPressed));
        trans.commit();

    }

    @Override
    public void onClick(View v) {
        selectMenu(v);
    }

    private void selectMenu(View selectedView) {
        MenuTag pressedTag = (MenuTag) selectedView.getTag();
        FragmentTransaction trans = fManager.beginTransaction();
        for (View v : menuViews) {
            MenuTag tag = (MenuTag) v.getTag();
            if (v.equals(selectedView)) {
                ((OnMenuClick) pressedTag.getFragment()).onMenuClick();
                trans.show(pressedTag.getFragment());
                pressedTag.getIv().setImageResource(pressedTag.getPressedImgRes());
                pressedTag.getTv().setTextColor(pressedTag.getPressedTvRes());
            } else {
                trans.hide(tag.getFragment());
                tag.getIv().setImageResource(tag.getUnPressedImgRes());
                tag.getTv().setTextColor(tag.getUnPressedTvRes());
            }
        }
        trans.commitAllowingStateLoss();
    }

    /**
     *当用户单击返回按键时，程序在后台不会停止运行
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(false);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
