package com.fci.colors_app.data.models;

import com.google.gson.annotations.SerializedName;

public class IntroModel {

    /**
     * id : 9
     * title :  : غير مترجم
     * description :  : غير مترجم
     * image : how_to_use/9_5f901068ed99a_.jpeg
     */

    @SerializedName("id")
    private Integer id;
    @SerializedName("title")
    private String title;
    @SerializedName("description")
    private String description;
    @SerializedName("image")
    private String image;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
