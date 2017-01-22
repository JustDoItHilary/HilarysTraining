package com.example.testswipemenulistviewdemo;

import android.graphics.Color;
import android.graphics.drawable.Drawable;

/**
 * Created by Hilary on 2016/10/21.
 *
 */

public class MySwipMenu {
    private Drawable icon;
    private Drawable background;
    private Color titleColor;
    private String title;

    public Color getTitleColor() {
        return titleColor;
    }

    public void setTitleColor(Color titleColor) {
        this.titleColor = titleColor;
    }

    public Drawable getBackground() {
        return background;
    }

    public void setBackground(Drawable background) {
        this.background = background;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
