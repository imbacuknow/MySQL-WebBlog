package com.cpe.mysql.webblog.model;

import javax.validation.constraints.NotNull;

public class TagModel {
    @NotNull
    private String tagName;
    @NotNull
    private String tagColor;

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getTagColor() {
        return tagColor;
    }

    public void setTagColor(String tagColor) {
        this.tagColor = tagColor;
    }
}
