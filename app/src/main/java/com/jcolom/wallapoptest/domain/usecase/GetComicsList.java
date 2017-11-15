/*  Created by jcolom  on 13/11/2017  */

package com.jcolom.wallapoptest.domain.usecase;

import com.jcolom.wallapoptest.data.repository.ComicsRepository;
import com.jcolom.wallapoptest.domain.model.Comic;
import com.jcolom.wallapoptest.domain.model.ComicsResponse;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;

public class GetComicsList extends UseCase<ComicsResponse> {

  private final ComicsRepository comicsRepository;
  private String characterId = "1009351";  //HULK id by default.

  @Inject public GetComicsList(@Named("executor_thread") Scheduler executorThread,
                               @Named("ui_thread") Scheduler uiThread,
                               ComicsRepository comicsRepository) {
    super(executorThread, uiThread);
    this.comicsRepository = comicsRepository;
  }

  @Override public Observable createObservableUseCase() {
    return comicsRepository.comicsListByCharacterId(characterId);
  }

  public void setCharacterId(String characterId) {
    this.characterId = characterId;
  }
}
