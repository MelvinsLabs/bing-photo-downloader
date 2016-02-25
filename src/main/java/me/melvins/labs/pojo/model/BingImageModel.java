package me.melvins.labs.pojo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by HomeAlone on 26-02-2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BingImageModel {

    private String url;

    public BingImageModel() {
    }

    public String getUrl() {
        return url;
    }

}
