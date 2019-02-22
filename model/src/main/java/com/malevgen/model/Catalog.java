package com.malevgen.model;

import java.util.Objects;

public class Catalog {
    private String name;
    private String providerURl;

    public Catalog(String name, String providerURl) {
        this.name = name;
        this.providerURl = providerURl;
    }

    public String getName() {
        return name;
    }

    public String getProviderURl() {
        return providerURl;
    }

    @Override
    public String toString() {
        return "Catalog{" +
                "name='" + name + '\'' +
                ", providerURl='" + providerURl + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Catalog catalog = (Catalog) o;
        return Objects.equals(name, catalog.name) &&
                Objects.equals(providerURl, catalog.providerURl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, providerURl);
    }
}
