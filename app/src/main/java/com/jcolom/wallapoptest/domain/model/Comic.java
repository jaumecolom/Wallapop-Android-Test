/*  Created by jcolom  on 13/11/2017  */

package com.jcolom.wallapoptest.domain.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Comic implements Parcelable {

    private int id;
    private String title;
    private String variantDescription;
    private String description;
    private List<Image> images;
    private Image thumbnail;

    public Comic() {

    }

    public Comic(int id, String title, String variantDescription, String description, List<Image> images, Image thumbnail) {
        this.id = id;
        this.title = title;
        this.variantDescription = variantDescription;
        this.description = description;
        this.images = images;
        this.thumbnail = thumbnail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVariantDescription() {
        return variantDescription;
    }

    public void setVariantDescription(String variantDescription) {
        this.variantDescription = variantDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public String getThumbnail() {
        return thumbnail.getFullPath();
    }


    protected Comic(Parcel in) {
        id = in.readInt();
        title = in.readString();
        variantDescription = in.readString();
        description = in.readString();
        if (in.readByte() == 0x01) {
            images = new ArrayList<>();
            in.readList(images, Image.class.getClassLoader());
        } else {
            images = null;
        }
        thumbnail = (Image) in.readValue(Image.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(variantDescription);
        dest.writeString(description);
        if (images == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(images);
        }
        dest.writeValue(thumbnail);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Comic> CREATOR = new Parcelable.Creator<Comic>() {
        @Override
        public Comic createFromParcel(Parcel in) {
            return new Comic(in);
        }

        @Override
        public Comic[] newArray(int size) {
            return new Comic[size];
        }
    };
}