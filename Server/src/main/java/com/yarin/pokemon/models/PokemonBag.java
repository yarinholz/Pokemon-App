package com.yarin.pokemon.models;

public class PokemonBag {

    private String[] bag;

    PokemonBag(){
        this.bag = new String[3];
        this.bag[0]="";
        this.bag[1]="";
        this.bag[2]="";

    }

    public String[] getBag() {
        return this.bag;
    }
}
