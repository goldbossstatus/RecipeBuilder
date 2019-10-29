package com.recipe.builder.tagservices;

public class TagRequest {

    private String title;

    public TagRequest(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
