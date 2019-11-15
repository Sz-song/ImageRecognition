package com.yuanyu.imagerecognition;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.donkingliang.imageselector.view.SquareImageView;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.yuanyu.imagerecognition.base.BaseObserver;
import com.yuanyu.imagerecognition.utils.ExceptionHandler;
import com.yuanyu.imagerecognition.utils.L;
import com.yuanyu.imagerecognition.wiki.WikiDetailActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import top.zibin.luban.Luban;

public class CameraActivity extends AppCompatActivity {
    private final String TAG = "camera";
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.image)
    SquareImageView image;
    @BindView(R.id.thumbnail)
    SquareImageView thumbnail;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.probability)
    TextView probability;
    @BindView(R.id.cancel)
    ImageView cancel;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.rl)
    RelativeLayout rl;
    @BindView(R.id.wiki)
    TextView wiki;

    private List<String> path = new ArrayList<>();
    private Model model = new Model();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        ButterKnife.bind(this);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//禁止横屏
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.mipmap.triangle);
        wiki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CameraActivity.this,WikiDetailActivity.class);
                intent.putExtra("item",name.getText().toString());
                startActivity(intent);
            }
        });

        selectImage();


    }

    @OnClick(R.id.cancel)
    public void click(View view) {
        selectImage();
    }

    private void selectImage() {
        PictureSelector.create(CameraActivity.this).openGallery(PictureMimeType.ofImage())
                .maxSelectNum(1)
                .enableCrop(true)
                .withAspectRatio(1, 1)
                .rotateEnabled(false)
                .forResult(PictureConfig.CHOOSE_REQUEST);
    }

    //压缩图片
    public void compressImage(List<String> image) {
        progressBar.setVisibility(View.VISIBLE);
        rl.setVisibility(View.INVISIBLE);

        Flowable.just(image).observeOn(Schedulers.io())
                .map(new Function<List<String>, List<File>>() {
                    @Override
                    public List<File> apply(List<String> list) throws Exception {
                        //Luban压缩，返回List<File>
                        return Luban.with(CameraActivity.this).load(list).get();
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<File>>() {
                    @Override
                    public void accept(List<File> files) {
                        uploadImage(files);

                    }
                });

    }

    //上传图片
    public void uploadImage(List<File> images) {
        model.uploadImage(images).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(new HttpServiceInstance.ErrorTransformer<List<String>>())
                .subscribe(new BaseObserver<List<String>>() {
                    @Override
                    public void onError(ExceptionHandler.ResponeThrowable e) {
                        L.e("error is " + e.status + e.message);
                        progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onNext(List<String> list) {
                        if (!isDestroyed()) {
                            progressBar.setVisibility(View.GONE);
                            rl.setVisibility(View.VISIBLE);
                            L.e("success " + list.get(0));
                            GlideApp.with(CameraActivity.this).load(path.get(0)).into(image);
                            GlideApp.with(CameraActivity.this).load(path.get(0)).into(thumbnail);
                            String result = list.get(0).substring(1, list.get(0).length() - 1);
                            String[] temp = result.split(",");
                            if (temp.length == 2) {
                                name.setText(temp[0]);
                                probability.setText(temp[1]);
                            } else {
                                name.setText(temp[0]);
                            }

                        }

                    }
                });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PictureConfig.CHOOSE_REQUEST && data != null) {
//            ArrayList<String> images = data.getStringArrayListExtra(ImageSelector.SELECT_RESULT);
            List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
            path.clear();
            path.add(selectList.get(0).getPath());
            compressImage(path);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
