/*  Created by jcolom  on 13/11/2017  */
package com.jcolom.wallapoptest.presentation;

import com.jcolom.wallapoptest.domain.usecase.GetComicsList;
import com.jcolom.wallapoptest.view.presenter.ComicsListPresenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class ComicsListPresenterTest {

    @Mock
    ComicsListPresenter.View view;
    @Mock
    GetComicsList getComicsList;
    private ComicsListPresenter presenter;

    @Before
    public void setUp() throws Exception {
        presenter = new ComicsListPresenter(getComicsList);
        presenter.setView(view);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testComicsListPresenterInitialize() {
        assertThat(presenter, instanceOf(ComicsListPresenter.class));
        assertThat(presenter.getView(), notNullValue());
    }
}
