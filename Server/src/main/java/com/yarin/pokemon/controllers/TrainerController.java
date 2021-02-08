package com.yarin.pokemon.controllers;

import com.yarin.pokemon.exception.*;
import com.yarin.pokemon.models.*;
import com.yarin.pokemon.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/trainer")
public class TrainerController {

    @Autowired
    TrainerRepository trainerRepository;

    @Autowired
    com.yarin.pokemon.repository.UserRepository userRepository;



    private boolean typeWin(com.yarin.pokemon.models.PokemonType type1, com.yarin.pokemon.models.PokemonType type2) {
        //Assume the the types are different
        if (type1 == com.yarin.pokemon.models.PokemonType.Fire & type2 == com.yarin.pokemon.models.PokemonType.Grass)
            return true;
        else if (type1 == com.yarin.pokemon.models.PokemonType.Fire & type2 == com.yarin.pokemon.models.PokemonType.Water)
            return false;
        else if (type1 == com.yarin.pokemon.models.PokemonType.Grass & type2 == com.yarin.pokemon.models.PokemonType.Water)
            return true;
        else if (type1 == com.yarin.pokemon.models.PokemonType.Grass & type2 == com.yarin.pokemon.models.PokemonType.Fire)
            return false;
        else if (type1 == com.yarin.pokemon.models.PokemonType.Water & type2 == com.yarin.pokemon.models.PokemonType.Fire)
            return true;
            //Water - Grass
        else
            return false;

    }

    private int pokemonBattle(com.yarin.pokemon.models.Pokemon pokemon1, com.yarin.pokemon.models.Pokemon pokemon2) {
        com.yarin.pokemon.models.PokemonType type1 = pokemon1.getType();
        com.yarin.pokemon.models.PokemonType type2 = pokemon2.getType();

        if (type1 == type2)
            return 0;
        else {
            if (typeWin(type1, type2))
                return 1;
            else
                return 2;
        }
    }


    @GetMapping("/name")
    @CrossOrigin
    public ResponseEntity<?> getTrainerData(/*@PathVariable String name*/) {
        CacheManager cacheManager = CacheManager.getInstance();
        if(!cacheManager.trainerExist("yarin".toLowerCase()))
            throw new com.yarin.pokemon.exception.TrainerNotFoundException();
        return  ResponseEntity.status(HttpStatus.OK).body(cacheManager.getTrainer("yarin".toLowerCase()));
    }

    @PutMapping("/catch")
    @CrossOrigin
    public ResponseEntity<?> catchPokemon(@Valid @RequestBody com.yarin.pokemon.payload.request.CatchRequest request) {
        String lowPokemonName = request.getPokemonName().toLowerCase();
        String lowTrainerName = request.getTrainerName().toLowerCase();
        CacheManager cacheManager = CacheManager.getInstance();
        if(!cacheManager.trainerExist(lowTrainerName))
            throw new com.yarin.pokemon.exception.TrainerNotFoundException();
        else if(!cacheManager.pokemonExist(lowPokemonName))
            throw new com.yarin.pokemon.exception.PokemonNotFoundException();
        else{
            com.yarin.pokemon.models.Pokemon pokemon = cacheManager.getPokemonByName(lowPokemonName);
            com.yarin.pokemon.models.Trainer trainer = cacheManager.getTrainer(lowTrainerName);
            trainer.addPokemon(pokemon);
            trainerRepository.save(trainer);
            return ResponseEntity.status(HttpStatus.OK).body("alright! you catch "+request.getPokemonName());
        }
    }

    @PutMapping("/battle")
    @CrossOrigin
    public ResponseEntity<?> trainerBattle(@Valid @RequestBody com.yarin.pokemon.payload.request.BattleRequest request) {
        String trainer1_name = request.getTrainer1();
        String trainer2_name = request.getTrainer2();

        CacheManager cacheManager = CacheManager.getInstance();
        if(!cacheManager.trainerExist(trainer2_name.toLowerCase())){
            throw new com.yarin.pokemon.exception.TrainerNotFoundException();
        }

        com.yarin.pokemon.models.Trainer trainer1 = cacheManager.getTrainer(trainer1_name.toLowerCase());
        com.yarin.pokemon.models.Trainer trainer2 = cacheManager.getTrainer(trainer2_name.toLowerCase());
        //if one of the trainers or both don't have 3 pokemons, the battle is canceled
        if (trainer1.NumOfPokemons() < 3 ) {
            throw new com.yarin.pokemon.exception.MinPokemonsException();
        }


        if (trainer2.NumOfPokemons() < 3 ) {
            throw new com.yarin.pokemon.exception.OpponentMinPokemonsException();
        }

        //else, the battle begins
        else {
            int trainer1Score = 0;
            int trainer2Score = 0;
            for (int i = 0; i < 3; i++) {
                com.yarin.pokemon.models.Pokemon pokemon1 = cacheManager.getPokemonByName(trainer1.getBag()[i].getName().toLowerCase());
                com.yarin.pokemon.models.Pokemon pokemon2 = cacheManager.getPokemonByName(trainer2.getBag()[i].getName().toLowerCase());
                int result = pokemonBattle(pokemon1, pokemon2);
                if (result == 1)
                    trainer1Score++;
                else if (result == 2)
                    trainer2Score++;
            }
            //If the results are equal, increase both trainers level by 1
            if (trainer1Score == trainer2Score) {
                trainer1.levelUp(1);
                trainerRepository.save(trainer1);
                trainer2.levelUp(1);
                trainerRepository.save(trainer2);
                return ResponseEntity.status(HttpStatus.OK).body("draw");
            }
            // Trainer 1 wins, increase his level by 2
            else if (trainer1Score > trainer2Score) {
                trainer1.levelUp(2);
                trainerRepository.save(trainer1);
                return ResponseEntity.status(HttpStatus.OK).body("Alright, you win the battle!");
            }
            // Trainer 2 wins, increase his level by 2
            else {
                trainer2.levelUp(2);
                trainerRepository.save(trainer2);
                return ResponseEntity.status(HttpStatus.OK).body("You lost the battle");
            }
        }
    }

    @PostMapping("/create")
    @CrossOrigin
    public ResponseEntity<?> addTrainer(@RequestBody com.yarin.pokemon.payload.request.AddTrainerRequest request) {
        CacheManager cacheManager = CacheManager.getInstance();
        if(cacheManager.trainerExist(request.getTrainerName()))
            throw new com.yarin.pokemon.exception.TrainerExistException();
        com.yarin.pokemon.models.Trainer newTrainer = new com.yarin.pokemon.models.Trainer(request.getTrainerName(),request.getUserName());
        cacheManager.addTrainer(newTrainer);
        trainerRepository.save(newTrainer);
        User user = cacheManager.getUser(newTrainer.getUserName());
        user.addTrainer(newTrainer.getName());
        userRepository.save(user);
        return new ResponseEntity(HttpStatus.OK);

    }



}