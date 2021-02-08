package com.yarin.pokemon.models;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@Service
public class CacheManager {

    private static CacheManager single_instance = null;
    final private HashMap<String, com.yarin.pokemon.models.Trainer> trainersPokedax = new HashMap<>();
    final private HashMap<Integer, com.yarin.pokemon.models.Pokemon> idPokedax = new HashMap<>();
    final private HashMap<String, com.yarin.pokemon.models.Pokemon> namePokedax = new HashMap<>();
    final private HashMap<String,User> userMap = new HashMap<>();

    private CacheManager() {

    }

    public static CacheManager getInstance()
    {
        if (single_instance == null)
            single_instance = new CacheManager();
        return single_instance;
    }

    public void loadPokemons(List<com.yarin.pokemon.models.Pokemon> pokemonList) {
        for (com.yarin.pokemon.models.Pokemon curr : pokemonList) {
            idPokedax.put(curr.getId(), curr);
            namePokedax.put(curr.getName().toLowerCase(), curr);
        }
    }
    public void loadTrainers(List<com.yarin.pokemon.models.Trainer> tarinersList) {
        for (com.yarin.pokemon.models.Trainer curr : tarinersList) {
            trainersPokedax.put(curr.getName().toLowerCase(), curr);
        }
    }

    public void loadUsers(List<User> userList) {
        for (User curr : userList) {
            userMap.put(curr.getUsername().toLowerCase(), curr);
        }
    }

    public void addTrainer(com.yarin.pokemon.models.Trainer t){
        this.trainersPokedax.put(t.getName(),t);
    }

    public com.yarin.pokemon.models.Pokemon getPokemonById(int id) {
        return idPokedax.get(id);
    }

    public User getUser(String userName){ return userMap.get(userName); }

    public boolean trainerExist(String trainerName){
        return trainersPokedax.containsKey(trainerName);
    }

    public boolean pokemonExist(String pokemonName){
        return namePokedax.containsKey(pokemonName);
    }

    public boolean userExist(String userName){
        return userMap.containsKey(userName);
    }


    public com.yarin.pokemon.models.Pokemon getPokemonByName(String name) {
        return namePokedax.get(name);
    }

    public com.yarin.pokemon.models.Trainer getTrainer(String name) {
        return trainersPokedax.get(name);
    }

    public Collection<com.yarin.pokemon.models.Trainer> getTrainerList() {
        return trainersPokedax.values();
    }

    public void addUser(User user){
        userMap.put(user.getUsername().toLowerCase(),user);
    }

}