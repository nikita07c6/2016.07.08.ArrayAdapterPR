package com.example.ccei.arrayadapterpj;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableWrapper;

/**
 * Created by ccei on 2016-07-08.
 */
public class TARAValueObject {
    public String memberName;
    public Drawable memberImage;
    public int count = 0;
    public Drawable heart;

    public TARAValueObject(String memberName, Drawable memberImage, int count, Drawable heart) {
        this.memberName = memberName;
        this.memberImage = memberImage;
        this.count = count;
        this.heart = heart;
    }
}
