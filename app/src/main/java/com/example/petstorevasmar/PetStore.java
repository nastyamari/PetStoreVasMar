package com.example.petstorevasmar;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class PetStore {
    @Expose
    private int id;
    @Expose
    private String category;
    @Expose
    private String name;
    @SerializedName("photoUrls")
    @Expose
    private String photoUrls;
    @Expose
    private String tags;
    @Expose
    private String status;
    public int getId() {
        return id;
    }
    public String getCategory() {
        return category;
    }
    public String getName() {
        return name;
    }
    public String getTags() {
        return tags;
    }
    public String getStatus() {
        return status;
    }
    public String getPhotoUrls() {
        return photoUrls;
    }



}
