package org.acme.dto;

public class PokemonDTO {
    public String name;
    public int pokemonId;
    public String sprite;
    public float height;
    public float weight;

    public PokemonDTO() {

    }

    public PokemonDTO(String name, int pokemonId, String sprite, float height, float weight) {
        this.name = name;
        this.pokemonId = pokemonId;
        this.sprite = sprite;
        this.height = height;
        this.weight = weight;
    }
}
