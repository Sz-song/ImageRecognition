package com.yuanyu.imagerecognition;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.makeramen.roundedimageview.RoundedImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import jp.wasabeef.glide.transformations.BlurTransformation;


/**
 * A simple {@link Fragment} subclass.
 */
public class WodeFragment extends Fragment {


    @BindView(R.id.background)
    ImageView background;
    @BindView(R.id.uname)
    TextView uname;
    Unbinder unbinder;
    @BindView(R.id.avatar)
    RoundedImageView avatar;

    public WodeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_wode, container, false);
        unbinder = ButterKnife.bind(this, view);
        GlideApp.with(getContext())
                .load(R.drawable.avatar)
                .optionalTransform(new BlurTransformation(20))
                .into(background);
        avatar.bringToFront();
        return view;
    }

    @OnClick({R.id.tiezi,R.id.shibie,R.id.feedback,R.id.about_us,R.id.quit})
    public void onClick(View view){
        Toast.makeText(getContext(), "即将开放，敬请期待", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
