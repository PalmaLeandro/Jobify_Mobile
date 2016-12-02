package com.example.root.jobify.Models;

import com.example.root.jobify.Views.GenericContentListPage.Content;

/**
 * Created by ale on 1/12/16.
 */

public class JobPosition implements Content {

    private String id;
    private String description;
    private String name;
    private String category;

    public JobPosition(String id, String description, String name, String category) {
        this.id = id;
        this.description = description;
        this.name = name;
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getTitle() {
        return getName();
    }

    @Override
    public String getSubTitle() {
        return getName()+"\n"+getDescription()+"\n"+getCategory();
    }

    @Override
    public String getPicture() {
        return null;
    }
}
