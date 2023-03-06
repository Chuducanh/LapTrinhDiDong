package com.example.giuaky2022.viewmodel;

import com.example.giuaky2022.model.Record;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;

public interface RecordApi {
    @GET("static/data/data.json")
    Single<List<Record>> getRecords();
}
