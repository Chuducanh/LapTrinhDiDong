package com.midterm.nguyenngocphu.viewmodel;

import com.midterm.nguyenngocphu.model.Description;

import java.util.List;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DesApiService {
    private static final String BASE_URL = "http://staff.vnuk.edu.vn:5000/";
    private DesApi api;
    public  DesApiService()
    {
        api = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
                .create(DesApi.class);

    }
    public Single<List<Description>> getDescriptions(){return api.getDescriptions();}
}
