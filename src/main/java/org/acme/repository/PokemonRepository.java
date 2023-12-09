package org.acme.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import org.acme.entity.Pokemon;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PokemonRepository implements PanacheRepository<Pokemon> {
    public Pokemon findById(int id) {
        return find("id", id).firstResult();
    }
}
