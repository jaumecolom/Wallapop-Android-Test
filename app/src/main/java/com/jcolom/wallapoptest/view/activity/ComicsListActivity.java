/*  Created by jcolom  on 13/11/2017  */

package com.jcolom.wallapoptest.view.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.jcolom.wallapoptest.MarbelApplication;
import com.jcolom.wallapoptest.R;
import com.jcolom.wallapoptest.domain.model.Comic;
import com.jcolom.wallapoptest.view.adapter.ComicsAdapter;
import com.jcolom.wallapoptest.view.base.view.BaseActivity;
import com.jcolom.wallapoptest.view.presenter.ComicsListPresenter;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import butterknife.BindView;

public class ComicsListActivity extends BaseActivity implements ComicsListPresenter.View {

    @Inject
    ComicsListPresenter presenter;
    @BindView(R.id.list_comics)
    RecyclerView comicList;
    @BindView(R.id.progress_comics)
    ProgressBar comicProgress;
    private ComicsAdapter adapter;

    @Override
    public void initView() {
        super.initView();
        initializeDagger();
        initializePresenter();
        disableTitleFromToolbar();
        initializeAdapter();
        initializeRecyclerView();
        presenter.initialize();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_comics;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.destroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showComicList(List<Comic> comicList) {
        long seed = System.nanoTime();
        Collections.shuffle(comicList, new Random(seed));
        adapter.addAll(comicList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void openComicDetail(Comic comic) {
        ComicDetailActivity.open(ComicsListActivity.this, comic);
    }

    @Override
    public void showLoading() {
        comicProgress.setVisibility(View.VISIBLE);
        comicList.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        comicProgress.setVisibility(View.GONE);
        comicList.setVisibility(View.VISIBLE);
    }

    private void initializeAdapter() {
        adapter = new ComicsAdapter(presenter);
    }

    private void initializeRecyclerView() {
        comicList.setLayoutManager(new LinearLayoutManager(this));
        comicList.addItemDecoration(new android.support.v7.widget.DividerItemDecoration(this,
                android.support.v7.widget.DividerItemDecoration.VERTICAL));
        comicList.setHasFixedSize(true);
        comicList.setAdapter(adapter);
    }

    private void initializeDagger() {
        MarbelApplication app = (MarbelApplication) getApplication();
        app.getMainComponent().inject(this);
    }

    private void initializePresenter() {
        presenter.setView(this);
    }

    private void disableTitleFromToolbar() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
    }

}
