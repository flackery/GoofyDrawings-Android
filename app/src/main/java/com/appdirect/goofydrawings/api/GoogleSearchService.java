package com.appdirect.goofydrawings.api;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by mehlert on 2017-05-12.
 */

public interface GoogleSearchService {

    @GET("customsearch/v1")
    Single<ImageSearchResult> searchImages(@Query("cx") String cx,
                                           @Query("key") String key,
                                           @Query("q") String q,
                                           @Query("num") int num,
                                           @Query("start") int start,
                                           @Query("searchType") String searchType,
                                           @Query("imgSize") String imgSize);

}
