/*  Created by jcolom  on 13/11/2017  */

package com.jcolom.wallapoptest.data.repository;

import android.support.annotation.NonNull;

import com.jcolom.wallapoptest.data.net.MarbelService;
import com.jcolom.wallapoptest.data.util.MD5Helper;
import com.jcolom.wallapoptest.domain.model.ComicsResponse;

import io.reactivex.Observable;


public class ComicsRepository {

    private MarbelService apiService;
    private String PUBLIC_API_KEY = "6a7ed890b4b941a925202a5630d5b162";
    private String PRIVATE_API_KEY = "0f1d0fdf46a0bf32f962b0b9997233c0395cdf8e";

    public ComicsRepository(@NonNull MarbelService apiService) {
        this.apiService = apiService;
    }

    public Observable<ComicsResponse> comicsListByCharacterId(String characterId) {
        long timestamp = System.currentTimeMillis();
        return apiService.getComicsList(characterId, PUBLIC_API_KEY, getHash(timestamp), String.valueOf(timestamp));
    }

    /**
     * Method to calculate the hash required in Marbel API.
     *
     * @param timestamp Current timestamp
     */
    private String getHash(long timestamp) {
        return MD5Helper.md5(String.valueOf(timestamp) + PRIVATE_API_KEY + PUBLIC_API_KEY);
    }

}
