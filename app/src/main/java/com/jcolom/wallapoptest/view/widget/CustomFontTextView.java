/*  Created by jcolom  on 13/11/2017  */

package com.jcolom.wallapoptest.view.widget;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

public class CustomFontTextView extends AppCompatTextView {

  final static String HULK_FONT = "hulk.ttf";

  public CustomFontTextView(Context context) {
    super(context);
    if (!isInEditMode()) {
      init(context);
    }
  }

  public CustomFontTextView(Context context, AttributeSet attrs) {
    super(context, attrs);
    if (!isInEditMode()) {
      init(context);
    }
  }

  public CustomFontTextView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    if (!isInEditMode()) {
      init(context);
    }
  }

  private void init(Context context) {
    this.setTypeface(getRobotoBlackTypeFace(context));
  }

  private Typeface getRobotoBlackTypeFace(Context context) {
    return Typeface.createFromAsset(context.getAssets(), HULK_FONT);
  }
}
