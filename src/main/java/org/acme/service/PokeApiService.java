package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.MediaType;

@ApplicationScoped
public class PokeApiService {
    private static final String POKE_API_URL = "https://pokeapi.co/api/v2/pokemon/";

    public String getPokemonData(String nameOrId) {
        Client client = ClientBuilder.newClient();
        return client.target(POKE_API_URL + nameOrId)
                .request(MediaType.APPLICATION_JSON)
                .get(String.class);
    }
}
