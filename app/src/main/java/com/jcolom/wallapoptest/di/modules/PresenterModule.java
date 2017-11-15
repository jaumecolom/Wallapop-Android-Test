package com.jcolom.wallapoptest.di.modules;

import com.jcolom.wallapoptest.domain.usecase.GetComicsList;
import com.jcolom.wallapoptest.view.presenter.ComicsListPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Jaume on 16/11/17.
 */

@Module
public class PresenterModule {

    @Provides
    ComicsListPresenter provideComicListPresenter(GetComicsList getComicsList) {
        return new ComicsListPresenter(getComicsList);
    }

}