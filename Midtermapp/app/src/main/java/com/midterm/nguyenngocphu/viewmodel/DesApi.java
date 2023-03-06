package com.midterm.nguyenngocphu.viewmodel;

import com.midterm.nguyenngocphu.model.Description;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;

public interface DesApi {
    @GET("static/data/data.json")
    Single<List<Description>> getDescriptions();
}
