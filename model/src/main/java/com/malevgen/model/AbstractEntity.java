package com.malevgen.model;

import java.util.UUID;

public class AbstractEntity {
    private UUID id;
    private String name;
    private String description;
    private String url;

    public AbstractEntity(UUID id, String name, String description, String url) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.url = url;
    }


}
