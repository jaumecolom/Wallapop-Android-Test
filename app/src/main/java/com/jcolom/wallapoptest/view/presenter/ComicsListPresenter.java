/*  Created by jcolom  on 13/11/2017  */

package com.jcolom.wallapoptest.view.presenter;

import android.support.annotation.NonNull;

import com.jcolom.wallapoptest.domain.model.Comic;
import com.jcolom.wallapoptest.domain.model.ComicsResponse;
import com.jcolom.wallapoptest.domain.usecase.GetComicsList;
import com.jcolom.wallapoptest.domain.usecase.UseCaseObserver;
import java.util.List;
import javax.inject.Inject;

public class ComicsListPresenter extends Presenter<ComicsListPresenter.View> {

  private GetComicsList getComicsList;

  @Inject public ComicsListPresenter(@NonNull GetComicsList getComicsList) {
    this.getComicsList = getComicsList;
  }

  @SuppressWarnings("unchecked") @Override public void initialize() {
    super.initialize();
    getView().showLoading();
    getComicsList.execute(new ComicListObserver());
  }

  public void onComicClicked(Comic comic) {
    getView().openComicDetail(comic);
  }

  public void destroy() {
    this.getComicsList.dispose();
    setView(null);
  }

  public interface View extends Presenter.View {

    void showComicList(List<Comic> comicList);

    void openComicDetail(Comic comic);
  }

  private final class ComicListObserver extends UseCaseObserver<ComicsResponse> {

    @Override public void onComplete() {
      getView().hideLoading();
    }

    @Override public void onError(Throwable e) {
      getView().hideLoading();
      e.printStackTrace();
    }

    @Override public void onNext(ComicsResponse comicsResponse) {
      getView().showComicList(comicsResponse.getData().getResults());
    }
  }
}
