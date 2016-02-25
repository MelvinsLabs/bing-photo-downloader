package me.melvins.labs.pojo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by HomeAlone on 26-02-2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BingModel {

    @JsonProperty(value = "images")
    private List<BingImageModel> bingImageModelList;

    public BingModel() {
    }

    public List<BingImageModel> getBingImageModelList() {
        return bingImageModelList;
    }

}
