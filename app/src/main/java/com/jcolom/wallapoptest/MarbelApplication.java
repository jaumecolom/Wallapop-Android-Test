/*  Created by jcolom  on 13/11/2017  */

package com.jcolom.wallapoptest;

import android.app.Application;
import com.jcolom.wallapoptest.di.components.DaggerMainComponent;
import com.jcolom.wallapoptest.di.components.MainComponent;
import com.jcolom.wallapoptest.di.modules.DataModule;
import com.jcolom.wallapoptest.di.modules.MainModule;
import com.jcolom.wallapoptest.di.modules.NetworkModule;
import com.jcolom.wallapoptest.di.modules.PresenterModule;
import com.jcolom.wallapoptest.di.modules.UseCaseModule;

public class MarbelApplication extends Application {

  private MainComponent mainComponent;

  @Override public void onCreate() {
    super.onCreate();
    initializeInjector();
  }

  private void initializeInjector() {
    mainComponent = DaggerMainComponent.builder()
            .mainModule(new MainModule(this))
            .networkModule(new NetworkModule())
            .dataModule(new DataModule())
            .presenterModule(new PresenterModule())
            .useCaseModule(new UseCaseModule()).build();
  }

  public MainComponent getMainComponent() {
    return mainComponent;
  }
}
