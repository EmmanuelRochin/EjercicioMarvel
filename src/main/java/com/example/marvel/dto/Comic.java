package com.example.marvel.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Comic {
    @JsonProperty("items")
    private List<Item> items;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }


}
