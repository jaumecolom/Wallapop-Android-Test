/*  Created by jcolom  on 13/11/2017  */

package com.jcolom.wallapoptest.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.jcolom.wallapoptest.R;

public class HeaderView extends LinearLayout {

  @BindView(R.id.txt_header_title) CustomFontTextView titleLabel;
  @BindView(R.id.txt_header_subtitle) TextView subTitleLabel;

  public HeaderView(Context context) {
    super(context);
  }

  public HeaderView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public HeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override protected void onFinishInflate() {
    super.onFinishInflate();
    View view = LayoutInflater.from(getContext()).inflate(R.layout.header_detail, this, true);
    ButterKnife.bind(view);
  }

  public void initializeHeader(String title, String variantDescription) {
    titleLabel.setText(title);
    subTitleLabel.setText(variantDescription);
  }
}
