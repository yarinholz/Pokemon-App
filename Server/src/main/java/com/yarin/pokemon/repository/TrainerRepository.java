package com.yarin.pokemon.repository;

import com.yarin.pokemon.models.Trainer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TrainerRepository extends MongoRepository<Trainer, String> {
}

