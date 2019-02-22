package com.malevgen.model;


public class CatalogService {
    private static final CatalogService INSTANCE = new CatalogService();

    private CatalogService() {
    }

    public static CatalogService getInstance() {
        return INSTANCE;
    }


}
