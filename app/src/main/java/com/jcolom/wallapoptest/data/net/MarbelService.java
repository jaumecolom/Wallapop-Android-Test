package com.jcolom.wallapoptest.data.net;

import com.jcolom.wallapoptest.domain.model.ComicsResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Jaume on 13/11/17.
 */

public interface MarbelService {
    @GET("/v1/public/characters/{characterId}/comics")
    Observable<ComicsResponse> getComicsList(@Path("characterId") String characterId, @Query(value = "apikey", encoded = true) String apiKey,
                                             @Query(value = "hash", encoded = true) String hash, @Query(value = "ts", encoded = true) String ts);
}