package com.yzj.studentsattendance.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yzj.studentsattendance.activitys.MainActivity;

import com.yzj.studentsattendance.R;

public class CourseFragment extends Fragment implements MainActivity.OnMenuClick {
    private View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_course, container, false);
            initView();
        }
        return view;
    }

    private void initView() {
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.activity_time_table_toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

    }

    @Override
    public void onMenuClick() {
    }
}
