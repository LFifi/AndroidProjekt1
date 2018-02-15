package com.example.gra_memory.pojo;

/**
 * Created by lenovo on 09.02.2018.
 */

public class Photo {
    private Integer id;
    private String category;
    private String uri;

    public String getCategory() {return category;}

    public void setCategory(String category) {this.category = category;}

    public String getUri() { return uri;}

    public void setUri(String uri) {this.uri = uri;}

    public Integer getId() { return id;}

    public void setId(Integer id) { this.id = id;}

    @Override
    public String toString() { return category;}


}
