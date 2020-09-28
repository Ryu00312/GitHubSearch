
package com.example.demo.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultApi {

    @SerializedName("total_count")
    @Expose
    public Integer totalCount;
    @SerializedName("incomplete_results")
    @Expose
    public Boolean incompleteResults;
    @SerializedName("items")
    @Expose
    public List<Item> items = null;

}
