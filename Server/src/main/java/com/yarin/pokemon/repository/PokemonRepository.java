package com.yarin.pokemon.repository;

import com.yarin.pokemon.models.Pokemon;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PokemonRepository extends MongoRepository<Pokemon,String> {

}
