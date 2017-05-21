package com.yzj.studentsattendance.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.yzj.studentsattendance.R;
import com.yzj.studentsattendance.activitys.AboutActivity;
import com.yzj.studentsattendance.activitys.HelpActivity;
import com.yzj.studentsattendance.activitys.LoginActivity;
import com.yzj.studentsattendance.activitys.MainActivity;
import com.yzj.studentsattendance.activitys.SettingActivity;
import com.yzj.studentsattendance.view.CustomAlertDialog;

public class MeFragment extends Fragment implements View.OnClickListener, MainActivity.OnMenuClick {
    private View view;
    private TextView tv_name;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_me, container, false);
        }
        initView();
        return view;
    }

    private void initView() {
        FrameLayout layout_help = (FrameLayout) view.findViewById(R.id.fragment_me_layout_help);
        FrameLayout layout_landing = (FrameLayout) view.findViewById(R.id.fragment_me_layout_landing);
        FrameLayout layout_exit = (FrameLayout) view.findViewById(R.id.fragment_me_layout_exit);
        FrameLayout layout_about = (FrameLayout) view.findViewById(R.id.fragment_me_layout_about);
        FrameLayout layout_settings = (FrameLayout) view.findViewById(R.id.fragment_me_layout_settings);
        tv_name = (TextView) view.findViewById(R.id.fragment_me_tv_landing);
        layout_landing.setOnClickListener(this);
        layout_exit.setOnClickListener(this);
        layout_about.setOnClickListener(this);
        layout_settings.setOnClickListener(this);
        layout_help.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_me_layout_landing:
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.fragment_me_layout_about:
                Intent intent_about = new Intent(getContext(), AboutActivity.class);
                startActivity(intent_about);
                break;
            case R.id.fragment_me_layout_help:
                Intent intent_help = new Intent(getContext(), HelpActivity.class);
                startActivity(intent_help);
                break;
            case R.id.fragment_me_layout_exit:
                final CustomAlertDialog custom_dialog = new CustomAlertDialog(getContext());
                custom_dialog.setTitle("提示");
                custom_dialog.setMessage("确定退出应用?");
                custom_dialog.setPositiveButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        custom_dialog.dismiss();
                        System.exit(0);
                    }
                });
                custom_dialog.setNegativeButton("取消", null);
                custom_dialog.show();
                break;
            case R.id.fragment_me_layout_settings:
                startActivity(new Intent(getContext(), SettingActivity.class));
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onMenuClick() {
    }
}
