package com.yuanyu.imagerecognition.social;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.donkingliang.imageselector.view.SquareImageView;
import com.yuanyu.imagerecognition.GlideApp;
import com.yuanyu.imagerecognition.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TieziAdapter extends RecyclerView.Adapter<TieziAdapter.ViewHolder> {

    private Context mContext;
    private List<TieziBean> mList;

    public TieziAdapter(Context mContext, List<TieziBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_tiezi, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GlideApp.with(mContext).load(R.drawable.img1).into(holder.img1);
        GlideApp.with(mContext).load(R.drawable.img2).into(holder.img2);
        GlideApp.with(mContext).load(R.drawable.img3).into(holder.img3);
        GlideApp.with(mContext).load(R.drawable.img4).into(holder.img4);
        GlideApp.with(mContext).load(R.drawable.img5).into(holder.img5);
        GlideApp.with(mContext).load(R.drawable.img6).into(holder.img6);
        GlideApp.with(mContext).load(R.drawable.img7).into(holder.img7);
        GlideApp.with(mContext).load(R.drawable.img8).into(holder.img8);
        GlideApp.with(mContext).load(R.drawable.img9).into(holder.img9);

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img1)
        SquareImageView img1;
        @BindView(R.id.img2)
        SquareImageView img2;
        @BindView(R.id.img3)
        SquareImageView img3;
        @BindView(R.id.img4)
        SquareImageView img4;
        @BindView(R.id.img5)
        SquareImageView img5;
        @BindView(R.id.img6)
        SquareImageView img6;
        @BindView(R.id.img7)
        SquareImageView img7;
        @BindView(R.id.img8)
        SquareImageView img8;
        @BindView(R.id.img9)
        SquareImageView img9;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
