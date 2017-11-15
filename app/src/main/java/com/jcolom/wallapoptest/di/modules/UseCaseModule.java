package com.jcolom.wallapoptest.di.modules;

import com.jcolom.wallapoptest.data.net.MarbelService;
import com.jcolom.wallapoptest.data.repository.ComicsRepository;
import com.jcolom.wallapoptest.domain.usecase.GetComicsList;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;

/**
 * Created by Jaume on 16/11/17.
 */


@Module
public class UseCaseModule {

    @Provides
    GetComicsList provideGetComicList(@Named("executor_thread") Scheduler executorThread, @Named("ui_thread") Scheduler uiThread, ComicsRepository comicsRepository) {
        return new GetComicsList(executorThread, uiThread, comicsRepository);
    }
}