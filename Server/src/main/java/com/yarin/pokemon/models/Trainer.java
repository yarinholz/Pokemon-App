package com.yarin.pokemon.models;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Trainers")
public class Trainer implements Comparable<Trainer>{
    @Id
    @Indexed
     private String name;
     private String userName;
    private int level;
    private int numOfPokemons;
    private int bagPointer;
    private  Pokemon[] pokemonBag;

    public Trainer(){
    }

    public Trainer(String name, String userName) {
        this.name = name;
        this.userName = userName;
        this.level = 1;
        this.pokemonBag = new Pokemon[3];
        this.bagPointer = 0;
        this.numOfPokemons = 0;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getBagPointer(){
        return this.bagPointer;
    }

    public int getNumOfPokemons(){
        return this.numOfPokemons;
    }

    public Pokemon[] getBag() {
        return pokemonBag;
    }
    public void levelUp(int num) {
        this.level = this.level+num;
    }

    public Pokemon[] PokemonsBag() {
        return pokemonBag;
    }

    public int NumOfPokemons() {
        return numOfPokemons;

    }

    public int addPokemon(Pokemon pokemon) {
        int index;
        if(numOfPokemons < 3) {
            this.pokemonBag[bagPointer] = pokemon;
            index=bagPointer;
            numOfPokemons++;
            bagPointer = (bagPointer+1)%3;
        }
        else {
            this.pokemonBag[bagPointer]=pokemon;
            index=bagPointer;
            bagPointer = (bagPointer+1)%3;
        }
        return index;
    }

    public int compareTo(Trainer u) {
        return u.level-this.level;
    }


    public String getName() {
        return name;
    }

    public String getUserName() {
        return userName;
    }
}

