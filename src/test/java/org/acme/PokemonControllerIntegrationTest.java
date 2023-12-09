package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import org.acme.dto.PokemonDTO;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
public class PokemonControllerIntegrationTest {

    @Test
    public void testGeetPokemonById() {
        given()
                .pathParam("id", 25)
                .when()
                .get("/pokemon/{id}")
                .then()
                .statusCode(200)
                .body("name", equalTo("pikachu"))
                .body("pokemonId", equalTo(25));
    }

    @Test
    public void testGetAllPokemon() {
        given()
                .when()
                .get("/pokemon")
                .then()
                .statusCode(200)
                .body("$.size()", greaterThan(0))
                .body("name", hasItems("Bulbasaur", "Charmander", "Squirtle"));
    }

    @Test
    public void testSearchPokemon() {
        given()
                .queryParam("nameOrId", "pikachu")
                .when()
                .get("/pokemon/search")
                .then()
                .statusCode(200)
                .body("name", equalTo("Pikachu"))
                .body("pokemonId", equalTo(25));
    }

    @Test
    public void testSavePokemon() {
        PokemonDTO pokemonDTO = new PokemonDTO("TestPokemon", 999, "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/999.png", 1.0f, 1.0f);

        given()
                .contentType("application/json")
                .body(pokemonDTO)
                .when()
                .post("/pokemon")
                .then()
                .statusCode(204);

        given()
                .pathParam("id", 999)
                .when()
                .get("/pokemon/{id}")
                .then()
                .statusCode(200)
                .body("name", equalTo("TestPokemon"))
                .body("pokemonId", equalTo(999));
    }

    @Test
    public void testSaveInvalidPokemon() {
        PokemonDTO pokemonDTO = new PokemonDTO("InvalidPokemon", 999, "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/999.png", 1.0f, 1.0f);

        given()
                .contentType("application/json")
                .body(pokemonDTO)
                .when()
                .post("/pokemon")
                .then()
                .statusCode(400);
    }
}
