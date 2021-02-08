package com.yarin.pokemon.payload.request;

public class CatchRequest {
    private String trainerName;
    private String pokemonName;

    public CatchRequest(String trainerName, String pokemonName) {
        this.trainerName = trainerName;
        this.pokemonName = pokemonName;
    }

    public String getTrainerName() {
        return trainerName;
    }

    public void setTrainerName(String trainerName) {
        this.trainerName = trainerName;
    }

    public String getPokemonName() {
        return pokemonName;
    }

    public void setPokemonName(String pokemonName) {
        this.pokemonName = pokemonName;
    }
}
