package com.seventh.circlerecyclerviewdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.seventh.circlerecyclerviewdemo.bean.CourseBean;

import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by admin on 2017-10-25-0025.
 */

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.VH> {

    private List<CourseBean> mImgList;
    private Context context;
    private boolean mIsNotLoop;
    OnItemClickListener mOnItemClickListener;


    public TestAdapter(Context context, List<CourseBean> mImgList, boolean mIsNotLoop) {
        this.context = context;
        this.mImgList = mImgList;
        this.mIsNotLoop = mIsNotLoop;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        VH h = new VH(LayoutInflater.from(context).inflate(R.layout.item_h, parent, false));
        return h;
    }

    @Override
    public void onBindViewHolder(final VH holder, int position) {
        holder.tv.setText("Number :" + (position % mImgList.size()));
        Glide.with(context)
                .load(R.mipmap.ic_launcher)
                .bitmapTransform(new CropCircleTransformation(context))
                .into(holder.iv);

        holder.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int n = holder.getLayoutPosition();
                mOnItemClickListener.onItemClick(v, n);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mIsNotLoop ? mImgList.size() : Integer.MAX_VALUE;
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

    public void addOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
}
