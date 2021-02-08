package com.yarin.pokemon.sevices;


import com.yarin.pokemon.models.Trainer;
import com.yarin.pokemon.models.Pokemon;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Service
public class DataService {

    private MongoTemplate mongoTemplate;

    public DataService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public List<Pokemon> getPokemons(){
        Query query = new Query();
        query.with(Sort.by(Sort.Direction.ASC, "id"));
        return mongoTemplate.find(query,Pokemon.class);
    }

    public List<Trainer> getTrainers(){
        Query query = new Query();
        query.with(Sort.by(Sort.Direction.DESC, "level"));
        return mongoTemplate.find(query,Trainer.class);
    }

    public Trainer[] getTrainersArr(){
        Query query = new Query();
        query.with(Sort.by(Sort.Direction.DESC, "level"));
        List<Trainer> list = mongoTemplate.find(query,Trainer.class);
        Object[] trainerArr = list.toArray();
        return (Trainer[]) trainerArr;
    }


    public Trainer getTrainer(String name){
        Query query = new Query();
        query.addCriteria(Criteria.where("userName").is(name));
        return mongoTemplate.findOne(query,Trainer.class);
    }

    public void addPokemon(Trainer trainer,Pokemon pokemon,int index){
         mongoTemplate.updateMulti(
                new Query(where("userName").is(trainer.getName())),
                new Update().set("pokemonBag."+index, pokemon).set("numOfPokemons",trainer.NumOfPokemons()).set("bagPointer",trainer.getBagPointer()),
                Trainer.class);
    }

    public Trainer addTrainer(Trainer t){
        mongoTemplate.insert(t);
        return t;
    }

    public void updateLevel(Trainer trainer,int level){
        mongoTemplate.updateFirst(new Query(where("userName").is(trainer.getName())),
                new Update().set("level",level),Trainer.class );
    }

    public void removeAllTrainers(){
        mongoTemplate.dropCollection("Trainers");
        mongoTemplate.createCollection("Trainers");
    }
    public void removeAllPokemons(){
        mongoTemplate.dropCollection("Pokemons");
        mongoTemplate.createCollection("Pokemons");
    }



}
