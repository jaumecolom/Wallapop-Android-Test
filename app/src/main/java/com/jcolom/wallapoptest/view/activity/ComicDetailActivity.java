/*  Created by jcolom  on 13/11/2017  */

package com.jcolom.wallapoptest.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.jcolom.wallapoptest.MarbelApplication;
import com.jcolom.wallapoptest.R;
import com.jcolom.wallapoptest.domain.model.Comic;
import com.jcolom.wallapoptest.view.base.view.BaseActivity;
import com.jcolom.wallapoptest.view.widget.HeaderView;
import com.squareup.picasso.Picasso;

import java.util.Random;

import butterknife.BindView;

public class ComicDetailActivity extends BaseActivity {

    private final static String COMIC_PARAM_KEY = "COMIC_PARAM_KEY";

    @BindView(R.id.header_detail)
    HeaderView detailHeader;
    @BindView(R.id.image_detail_history)
    ImageView imageDetailHistory;
    @BindView(R.id.label_description_1)
    TextView labelDescription1;


    public static void open(Context context, Comic comic) {
        Intent intent = new Intent(context, ComicDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(COMIC_PARAM_KEY, comic);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    public void initView() {
        super.initView();
        initializeToolbar();
        initializeDagger();
        initializeScreen();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_comic_detail;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    private void initializeDagger() {
        MarbelApplication marbelApplication = (MarbelApplication) getApplication();
        marbelApplication.getMainComponent().inject(this);
    }

    private void initializeScreen() {
        Comic comic = getComicParam();
        renderComicDetail(comic);
    }

    private Comic getComicParam() {
        return (Comic) getIntent().getExtras().get(COMIC_PARAM_KEY);
    }

    private void renderComicDetail(Comic comic) {

        detailHeader.initializeHeader(comic.getTitle(), comic.getVariantDescription());
        labelDescription1.setText(comic.getDescription());

        String image = comic.getThumbnail();
        if (image.contains("image_not_available")) {
            if (comic.getImages() != null && comic.getImages().size() > 0) {
                image = comic.getImages().get(new Random().nextInt(comic.getImages().size())).getFullPath();  //Getting a random image from the list.
            }
        } else {
            image = comic.getThumbnail(); // If not exists any picture, try to use the thumbnail.
        }
        getImage(image, imageDetailHistory);

    }

    private void getImage(String photo, ImageView photoImageView) {
        Picasso.with(photoImageView.getContext()).load(photo).fit().centerCrop().into(photoImageView);
    }

    private void initializeToolbar() {
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(
                    ContextCompat.getColor(ComicDetailActivity.this, R.color.colorPrimaryDark));
        }

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("");
        }
    }

}
