/*  Created by jcolom  on 13/11/2017  */

package com.jcolom.wallapoptest.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.jcolom.wallapoptest.R;
import com.jcolom.wallapoptest.domain.model.Comic;
import com.jcolom.wallapoptest.view.presenter.ComicsListPresenter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ComicsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  private final ComicsListPresenter presenter;
  private final List<Comic> comicList;

  public ComicsAdapter(@NonNull ComicsListPresenter presenter) {
    this.presenter = presenter;
    comicList = new ArrayList<>();
  }

  @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comic_row, parent, false);
    return new ComicsViewHolder(view, presenter);
  }

  @Override public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    ComicsViewHolder comicsViewHolder = (ComicsViewHolder) holder;
    Comic comic = comicList.get(position);
    comicsViewHolder.render(comic);
  }

  @Override public int getItemCount() {
    return comicList.size();
  }

  public void addAll(Collection<Comic> collection) {
    comicList.addAll(collection);
  }
}
