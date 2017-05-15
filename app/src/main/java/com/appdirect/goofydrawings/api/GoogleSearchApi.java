package com.appdirect.goofydrawings.api;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

/**
 * Created by mehlert on 2017-05-12.
 */

public class GoogleSearchApi {

    GoogleSearchService service;


    private static GoogleSearchApi instance;

    public static GoogleSearchApi getInstance() {
        if (instance == null) {
            synchronized (GoogleSearchApi.class) {
                if (instance == null) {
                    instance = new GoogleSearchApi();
                    instance.setupService();
                }
            }
        }
        return instance;
    }

    private void setupService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.googleapis.com/")
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build();

        service = retrofit.create(GoogleSearchService.class);
    }

    public Single<ImageSearchResult> searchImages(String q) {
        return service.searchImages("015651565849393870031:xx6sr1sz-fi", "AIzaSyANXLu2QEQLdVHMxyLKLM0Wjauy2Lj4ap8", q, 10, 1, "image", "medium");
    }
}
