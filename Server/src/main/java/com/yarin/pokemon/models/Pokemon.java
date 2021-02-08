package com.yarin.pokemon.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Pokemons")
public class Pokemon {
    @Id
    @Indexed
    int id;
    String name;
    PokemonType type;

    public Pokemon(int id,String name,PokemonType type){
        this.id=id;
        this.name=name;
        this.type=type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PokemonType getType() {
        return type;
    }

    public void setType(PokemonType type) {
        this.type = type;
    }
    //  @Override
    // public String toString() {
    //    return this.name;
    // }
}