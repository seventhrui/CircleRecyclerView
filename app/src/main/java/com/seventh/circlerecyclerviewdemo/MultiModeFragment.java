package com.seventh.circlerecyclerviewdemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;
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
    private List<Integer> mImgList;
    private boolean mIsNotLoop;

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

        mItemViewMode = new CircularHorizontalBTTMode(600, false);
        mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        mIsNotLoop = true;

        mCircleRecyclerView.setLayoutManager(mLayoutManager);
        mCircleRecyclerView.setViewMode(mItemViewMode);
        mCircleRecyclerView.setNeedCenterForce(true);
        mCircleRecyclerView.setNeedLoop(!mIsNotLoop);

        mCircleRecyclerView.setOnCenterItemClickListener(new CircleRecyclerView.OnCenterItemClickListener() {
            @Override
            public void onCenterItemClick(View v) {
                Toast.makeText(getContext(), "Center Clicked", Toast.LENGTH_SHORT).show();
            }
        });


        mImgList = Arrays.asList(mImgs);
        Collections.shuffle(mImgList);

        mCircleRecyclerView.setAdapter(new A());

    }

    class A extends RecyclerView.Adapter<VH> {

        @Override
        public VH onCreateViewHolder(ViewGroup parent, int viewType) {
            VH h = new VH(LayoutInflater.from(getContext()).inflate(R.layout.item_h, parent, false));
            return h;
        }

        @Override
        public void onBindViewHolder(VH holder, int position) {
            holder.tv.setText("Number :" + (position % mImgList.size()));
            Glide.with(getContext())
                    .load(mImgList.get(position % mImgList.size()))
                    .bitmapTransform(new CropCircleTransformation(getContext()))
                    .into(holder.iv);

        }

        @Override
        public int getItemCount() {
            return mIsNotLoop ? mImgList.size() : Integer.MAX_VALUE;
        }
    }

    class VH extends RecyclerView.ViewHolder {
        TextView tv;
        ImageView iv;
        public VH(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.item_text);
            iv = (ImageView) itemView.findViewById(R.id.item_img);
        }
    }
}
