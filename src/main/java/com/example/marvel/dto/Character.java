package com.example.marvel.dto;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Character {
    private int id;
    private String name;
    private String description;
    private Thumbnail thumbnail;
    @JsonProperty("comics")
    private Comic comics;

    public Comic getComics() {
        return comics;
    }

    public void setComics(Comic comics) {
        this.comics = comics;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }







}



