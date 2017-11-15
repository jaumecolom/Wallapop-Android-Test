package com.jcolom.wallapoptest.domain.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Jaume on 15/11/17.
 */

public class Image implements Parcelable {
    private String path;
    private String extension;

    private Image(String path, String extension) {
        this.path = path;
        this.extension = extension;
    }

    public String getFullPath() {
        return path+"."+extension;
    }

    protected Image(Parcel in) {
        path = in.readString();
        extension = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(path);
        dest.writeString(extension);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Image> CREATOR = new Parcelable.Creator<Image>() {
        @Override
        public Image createFromParcel(Parcel in) {
            return new Image(in);
        }

        @Override
        public Image[] newArray(int size) {
            return new Image[size];
        }
    };
}