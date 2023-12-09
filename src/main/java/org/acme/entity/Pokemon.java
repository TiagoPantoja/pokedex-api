package org.acme.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import jakarta.persistence.Entity;

public class Pokemon  extends PanacheEntity {
    public String name;
    public int id;
    public String sprite;
    public float height;
    public float weight;

    public Pokemon() {

    }

    public Pokemon(String name, int pokemonId, String sprite, float height, float weight) {
        this.name = name;
        this.id = pokemonId;
        this.sprite = sprite;
        this.height = height;
        this.weight = weight;
    }
}
