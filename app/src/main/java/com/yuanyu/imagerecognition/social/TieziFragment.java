package com.yuanyu.imagerecognition.social;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yuanyu.imagerecognition.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class TieziFragment extends Fragment {


//    @BindView(R.id.recyclerview)
//    RecyclerView recyclerview;
//    @BindView(R.id.swipe)
//    SwipeRefreshLayout swipe;
    Unbinder unbinder;
    private TieziAdapter adapter;
    private List<TieziBean> mList = new ArrayList<>();


    public TieziFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tiezi, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }
    private void initView(){
//        adapter = new TieziAdapter(getContext(),mList);
//        recyclerview.setAdapter(adapter);
//        LinearLayoutManager lm = new LinearLayoutManager(getContext());
//        recyclerview.setLayoutManager(lm);
//        mList.add(new TieziBean());
//        mList.add(new TieziBean());
//        adapter.notifyDataSetChanged();
//        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                swipe.setRefreshing(false);
//            }
//        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
