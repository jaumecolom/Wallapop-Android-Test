package com.jcolom.wallapoptest.di.modules;

import com.jcolom.wallapoptest.data.net.MarbelService;
import com.jcolom.wallapoptest.data.repository.ComicsRepository;
import com.jcolom.wallapoptest.domain.usecase.GetComicsList;
import com.jcolom.wallapoptest.view.presenter.ComicsListPresenter;


import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;

/**
 * Created by Jaume on 13/11/17.
 */

@Module
public class DataModule {

    @Provides
    ComicsRepository provideComicsRepository(MarbelService marbelService) {
        return new ComicsRepository(marbelService);
    }

}