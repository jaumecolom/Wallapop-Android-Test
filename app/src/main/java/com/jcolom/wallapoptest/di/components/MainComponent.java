/*  Created by jcolom  on 13/11/2017  */

package com.jcolom.wallapoptest.di.components;

import com.jcolom.wallapoptest.di.modules.DataModule;
import com.jcolom.wallapoptest.di.modules.MainModule;
import com.jcolom.wallapoptest.di.modules.NetworkModule;
import com.jcolom.wallapoptest.di.modules.PresenterModule;
import com.jcolom.wallapoptest.di.modules.UseCaseModule;
import com.jcolom.wallapoptest.view.activity.ComicDetailActivity;
import com.jcolom.wallapoptest.view.activity.ComicsListActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
        modules = {
                MainModule.class,
                DataModule.class,
                NetworkModule.class,
                UseCaseModule.class,
                PresenterModule.class
        }
)
public interface MainComponent {

    void inject(ComicsListActivity activity);

    void inject(ComicDetailActivity activity);
}
