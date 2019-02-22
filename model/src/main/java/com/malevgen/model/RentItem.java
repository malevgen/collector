package com.malevgen.model;

import java.util.Map;

public class RentItem {
    private Catalog catalog;
    private String name;
    private Map<String,String> price;
    private String description;
    private String url;

    public RentItem(Catalog catalog, String name, Map<String, String> price, String description, String url) {
        this.catalog = catalog;
        this.name = name;
        this.price = price;
        this.description = description;
        this.url = url;
    }
}
