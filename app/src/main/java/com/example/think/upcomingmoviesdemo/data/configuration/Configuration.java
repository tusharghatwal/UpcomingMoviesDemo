
package com.example.think.upcomingmoviesdemo.data.configuration;

import com.example.think.upcomingmoviesdemo.data.local.PreferencesHelper;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


public class Configuration {

    @JsonProperty("images")
    private Images images;
    @JsonProperty("change_keys")
    private List<String> changeKeys = null;

    public Configuration() {}

    public Configuration(Images images, List<String> changeKeys) {
        this.images = images;
        this.changeKeys = changeKeys;
    }

    public Images getImages() {
        return images;
    }

    public void setImages(Images images) {
        this.images = images;
    }

    public List<String> getChangeKeys() {
        return changeKeys;
    }

    public void setChangeKeys(List<String> changeKeys) {
        this.changeKeys = changeKeys;
    }

    public void save(PreferencesHelper preferencesHelper) {
        int len = images.posterSizes.size();
        int blen = images.getBackdropSizes().size();
        preferencesHelper.putThumbnailBaseImageUrl(images.baseUrl +
                images.posterSizes.get(0));
        preferencesHelper.putMediumBaseImageUrl(images.baseUrl +
                images.getBackdropSizes().get(blen - 4));
        preferencesHelper.putOriginalBaseImageUrl(images.baseUrl +
                images.getBackdropSizes().get(blen - 1));
    }
}
