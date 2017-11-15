/*  Created by jcolom  on 13/11/2017  */

package com.jcolom.wallapoptest.domain;

import com.jcolom.wallapoptest.data.repository.ComicsRepository;
import com.jcolom.wallapoptest.domain.usecase.GetComicsList;

import io.reactivex.schedulers.Schedulers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@RunWith(MockitoJUnitRunner.class) public class GetComicsListTest {

  @Mock
  ComicsRepository repository;
  private GetComicsList getComicsList;

  @Before public void setUp() {
    getComicsList = givenAComicListUseCase();
  }

  @Test public void givenAConcreteUseCaseOfGetComic() {
    assertThat(getComicsList, instanceOf(GetComicsList.class));
  }

  @Test public void getComicListObservableFromRepository() {
    getComicsList.createObservableUseCase();
    verify(repository).comicsListByCharacterId("1009351");
    verifyNoMoreInteractions(repository);
  }

  private GetComicsList givenAComicListUseCase() {
    return new GetComicsList(Schedulers.trampoline(), Schedulers.trampoline(), repository);
  }
}
