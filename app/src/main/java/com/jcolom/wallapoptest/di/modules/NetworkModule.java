package com.jcolom.wallapoptest.di.modules;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jcolom.wallapoptest.data.net.MarbelService;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    private final String BASE_URL = "http://gateway.marvel.com";


    @Singleton
    @Provides
    Gson provideGson() {
        return new GsonBuilder().create();
    }

    @Singleton
    @Provides
    OkHttpClient provideOkHttpProxy() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        Interceptor rewriteCacheControlInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Response originalResponse = chain.proceed(chain.request());
                return originalResponse.newBuilder()
                        .header("Cache-Control", String.format("max-age=%d, only-if-cached, " +
                                "max-stale=%d", 60 * 4, 0))
                        .build();
            }
        };

        OkHttpClient initialOkHttpClient = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(logging)
                .addInterceptor(rewriteCacheControlInterceptor)
                .build();

        return initialOkHttpClient;
    }

    @Singleton
    @Provides
    MarbelService provideMarbelService(OkHttpClient client,
                                       Gson gson) {

        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .baseUrl(BASE_URL)
                .build();

        return retrofit.create(MarbelService.class);
    }
}