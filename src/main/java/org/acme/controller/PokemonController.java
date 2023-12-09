package org.acme.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import org.acme.dto.PokemonDTO;
import org.acme.entity.Pokemon;
import org.acme.repository.PokemonRepository;
import org.acme.service.PokeApiService;

import java.util.List;

@Path("/pokemon")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PokemonController {

    @Inject
    PokemonRepository pokemonRepository;

    @Inject
    PokeApiService pokeApiService;

    @GET
    @Path("/{id}")
    public PokemonDTO getPokemonById(@PathParam("id") int id) {
        Pokemon pokemon = pokemonRepository.findById(id);
        return mapPokemonToDTO(pokemon);
    }

    @GET
    public List<PokemonDTO> getAllPokemon() {
        List<Pokemon> pokemonList = pokemonRepository.listAll();
        return mapPokemonListToDTO(pokemonList);
    }

    @Transactional
    @POST
    public void savePokemon(@Valid PokemonDTO pokemonDTO) {
        Pokemon pokemon = mapDTOToPokemon(pokemonDTO);
        pokemonRepository.persist(pokemon);
    }

    @GET
    @Path("/search")
    public PokemonDTO searchPokemon(@QueryParam("nameOrId") String nameOrId) {
        String pokemonData = pokeApiService.getPokemonData(nameOrId);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(pokemonData);

            String pokemonname = jsonNode.get("name").asText();
            int pokemonId = jsonNode.get("id").asInt();
            String sprite = jsonNode.get("sprites").get("front_default").asText();
            float height = jsonNode.get("height").floatValue();
            float weight = jsonNode.get("weight").floatValue();

            PokemonDTO pokemonDTO = new PokemonDTO(pokemonname, pokemonId, sprite, height, weight);
            return pokemonDTO;

        } catch (Exception e) {
            throw new WebApplicationException("Pokemon not found", 404);
        }
    }

    private PokemonDTO mapPokemonToDTO(Pokemon pokemon) {
        return new PokemonDTO(
                pokemon.name,
                pokemon.id,
                pokemon.sprite,
                pokemon.height,
                pokemon.weight
        );
    }

    private List<PokemonDTO> mapPokemonListToDTO(List<Pokemon> pokemonList) {
        return pokemonList.stream()
                .map(this::mapPokemonToDTO)
                .toList();
    }

    private Pokemon mapDTOToPokemon(PokemonDTO pokemonDTO) {
        return new Pokemon(
                pokemonDTO.name,
                pokemonDTO.pokemonId,
                pokemonDTO.sprite,
                pokemonDTO.height,
                pokemonDTO.weight
        );
    }
}