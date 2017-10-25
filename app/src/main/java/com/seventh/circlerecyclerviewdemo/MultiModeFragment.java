package com.seventh.circlerecyclerviewdemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import com.seventh.circlerecyclerviewdemo.bean.CourseBean;
import com.seventh.criclerecycler.widget.CircleRecyclerView;
import com.seventh.criclerecycler.widget.CircularHorizontalBTTMode;
import com.seventh.criclerecycler.widget.ItemViewMode;

/**
 *
 */
public class MultiModeFragment extends Fragment {

    private CircleRecyclerView mCircleRecyclerView;
    private ItemViewMode mItemViewMode;
    private LinearLayoutManager mLayoutManager;
    private List<CourseBean> mImgList;
    private boolean mIsNotLoop;

    private EditText et_position;
    private Button btn_goto;

    private Integer[] mImgs = {
            R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher,
            R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher,
            R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher
    };

    public static MultiModeFragment newInstance(@ModeType.ModeTypeChecker int modeType) {
        MultiModeFragment fragment = new MultiModeFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("mode_type", modeType);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mCircleRecyclerView = (CircleRecyclerView) view.findViewById(R.id.circle_rv);

        et_position = (EditText) view.findViewById(R.id.et_position);
        btn_goto = (Button) view.findViewById(R.id.btn_goto);

        mItemViewMode = new CircularHorizontalBTTMode(600, false);
        mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        mIsNotLoop = true;

        mCircleRecyclerView.setLayoutManager(mLayoutManager);
        mCircleRecyclerView.setViewMode(mItemViewMode);
        mCircleRecyclerView.setNeedCenterForce(true);
        mCircleRecyclerView.setNeedLoop(!mIsNotLoop);

        mImgList = new ArrayList<>();
        for (int i=0;i<12;i++){
            mImgList.add(new CourseBean());
        }
        TestAdapter adapter = new TestAdapter(getContext(), mImgList, mIsNotLoop);
        mCircleRecyclerView.setAdapter(adapter);

        adapter.addOnItemClickListener(new TestAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getContext(), "Clicked"+position, Toast.LENGTH_SHORT).show();
            }
        });

        mCircleRecyclerView.setOnCenterItemClickListener(new CircleRecyclerView.OnCenterItemClickListener() {
            @Override
            public void onCenterItemClick(View v) {
                Toast.makeText(getContext(), "Center Clicked", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
