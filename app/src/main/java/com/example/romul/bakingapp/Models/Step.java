package com.example.romul.bakingapp.Models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Step implements Serializable {

    @JsonProperty("id")
    private int id;

    @JsonProperty("shortDescription")
    private String shortDescription;

    @JsonProperty("description")
    private String fullDescription;

    @JsonProperty("videoURL")
    private String videoURL;

    @JsonProperty("thumbnailURL")
    private String thumbnailURL;

    private Step(){
    }

    public int getId() {
        return id;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getFullDescription() {
        return fullDescription;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public void setFullDescription(String fullDescription) {
        this.fullDescription = fullDescription;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }

    public void setThumbnailURL(String thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
    }
}
