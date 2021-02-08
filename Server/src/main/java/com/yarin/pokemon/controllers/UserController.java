package com.yarin.pokemon.controllers;

import com.yarin.pokemon.exception.UserNotFoundException;
import com.yarin.pokemon.models.CacheManager;
import com.yarin.pokemon.models.Trainer;
import com.yarin.pokemon.repository.TrainerRepository;
import com.yarin.pokemon.models.User;
import com.yarin.pokemon.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)

@RestController
@RequestMapping("/user")

public class UserController {

    @Autowired
    TrainerRepository trainerRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/{name}/trainers")
    @CrossOrigin
    public List<Trainer> getTrainerData(@PathVariable String name) {
        CacheManager cacheManager = CacheManager.getInstance();
        if(!cacheManager.userExist(name))
            throw new UserNotFoundException();
        else {
            User user = cacheManager.getUser(name);
            List<Trainer> trainers = new LinkedList<>();
            for (String trainer : user.getTrainers())
                trainers.add(cacheManager.getTrainer(trainer));
            return trainers;
        }
    }


}
