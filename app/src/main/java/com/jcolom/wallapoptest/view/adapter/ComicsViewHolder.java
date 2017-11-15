/*  Created by jcolom  on 13/11/2017  */

package com.jcolom.wallapoptest.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jcolom.wallapoptest.R;
import com.jcolom.wallapoptest.domain.model.Comic;
import com.jcolom.wallapoptest.view.presenter.ComicsListPresenter;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class ComicsViewHolder extends RecyclerView.ViewHolder {

    private final ComicsListPresenter comicsListPresenter;
    @BindView(R.id.image_header)
    ImageView headerImage;
    @BindView(R.id.label_name)
    TextView nameLabel;

    public ComicsViewHolder(@NonNull View itemView, @NonNull ComicsListPresenter comicsListPresenter) {
        super(itemView);
        this.comicsListPresenter = comicsListPresenter;
        ButterKnife.bind(this, itemView);
    }

    public void render(Comic comic) {
        onItemClick(comic);
        renderImage(comic);
        renderComicName(comic.getTitle());
    }

    private void onItemClick(final Comic comic) {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comicsListPresenter.onComicClicked(comic);
            }
        });
    }

    private void renderImage(Comic comic) {

        String image = comic.getThumbnail();
        if(image.contains("image_not_available")){
            if(comic.getImages() != null && comic.getImages().size() > 0){
                image = comic.getImages().get(0).getFullPath();
            }
        }
        getImage(image, headerImage);
    }

    private void renderComicName(String name) {
        nameLabel.setText(name);
    }

    private void getImage(String photo, ImageView photoImageView) {
        Picasso.with(getContext()).load(photo).placeholder(R.drawable.hulk_default).fit().centerCrop().into(photoImageView);
    }

    private Context getContext() {
        return itemView.getContext();
    }
}
