
package com.example.think.upcomingmoviesdemo.data.Images;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class MovieImages {

    @JsonProperty("id")
    public Integer id;
    @JsonProperty("backdrops")
    public List<Backdrop> backdrops = null;
    @JsonProperty("posters")
    public List<Poster> posters = null;

    public MovieImages() {}

    public MovieImages(Integer id, List<Backdrop> backdrops, List<Poster> posters) {
        this.id = id;
        this.backdrops = backdrops;
        this.posters = posters;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Backdrop> getBackdrops() {
        return backdrops;
    }

    public void setBackdrops(List<Backdrop> backdrops) {
        this.backdrops = backdrops;
    }

    public List<Poster> getPosters() {
        return posters;
    }

    public void setPosters(List<Poster> posters) {
        this.posters = posters;
    }
}
