/*  Created by jcolom  on 13/11/2017  */

package com.jcolom.wallapoptest.di.modules;

import android.content.Context;
import com.jcolom.wallapoptest.MarbelApplication;
import com.jcolom.wallapoptest.data.repository.ComicsRepository;
import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import javax.inject.Named;
import javax.inject.Singleton;

@Module public class MainModule {

  private final MarbelApplication marbelApplication;

  public MainModule(MarbelApplication marbelApplication) {
    this.marbelApplication = marbelApplication;
  }

  @Provides @Singleton Context provideApplicationContext() {
    return marbelApplication;
  }

  @Provides @Named("executor_thread") Scheduler provideExecutorThread() {
    return Schedulers.io();
  }

  @Provides @Named("ui_thread") Scheduler provideUiThread() {
    return AndroidSchedulers.mainThread();
  }
}
