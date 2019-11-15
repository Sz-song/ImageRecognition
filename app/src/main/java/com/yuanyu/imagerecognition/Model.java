package com.yuanyu.imagerecognition;

import com.google.gson.Gson;
import com.yuanyu.imagerecognition.base.BaseResponse;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class Model {
    HttpService httpService;

    public Model(){httpService = HttpServiceInstance.getInstance();}

    public Observable<BaseResponse<List<String>>> uploadImage(List<File> images){

        Map map = new HashMap();

        map.put("action", "upload");
        Map data = new HashMap();
        data.put("type", "2");
        data.put("postfix", "jpg");
        map.put("data", data);


        Gson gson = new Gson();
        String str = gson.toJson(map);

        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), str);

        MultipartBody.Part[] part = new MultipartBody.Part[images.size()];
        for (int i = 0; i < images.size(); i++) {
            RequestBody photoBody = RequestBody.create(MediaType.parse("image/jpg"), images.get(i));
            part[i] = MultipartBody.Part.createFormData("files[]", images.get(i).getName(), photoBody);
        }

        return httpService.uploadImage(body,part);

    }


}
