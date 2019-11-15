package com.yuanyu.imagerecognition.wiki;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yuanyu.imagerecognition.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class WikiFragment extends Fragment {


    @BindView(R.id.hetianyu)
    TextView hetianyu;
    @BindView(R.id.tiekuangshi)
    TextView tiekuangshi;
    @BindView(R.id.tongkuangshi)
    TextView tongkuangshi;
    @BindView(R.id.fangjieshi)
    TextView fangjieshi;
    @BindView(R.id.shiyingshi)
    TextView shiyingshi;
    @BindView(R.id.jinkuangshi)
    TextView jinkuangshi;
    Unbinder unbinder;

    public WikiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_wiki, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }
    @OnClick({R.id.hetianyu,R.id.jinkuangshi,R.id.tongkuangshi,R.id.tiekuangshi,R.id.fangjieshi,R.id.shiyingshi})
    public void onclick(TextView view){
        Intent intent = new Intent(getContext(),WikiDetailActivity.class);
        intent.putExtra("item",view.getText());
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
