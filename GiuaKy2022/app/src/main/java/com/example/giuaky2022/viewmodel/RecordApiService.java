package com.example.giuaky2022.viewmodel;

import com.example.giuaky2022.model.Record;

import java.util.List;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecordApiService {
    private static final String BASE_URL = "http://staff.vnuk.edu.vn:5000/";
    private RecordApi api;

    public RecordApiService() {
        if (api == null) {
            api = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .build()
                    .create(RecordApi.class);
        }
    }

    public Single<List<Record>> getRecords() {
        return api.getRecords();
    }
}
