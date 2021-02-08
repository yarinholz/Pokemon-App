package com.yarin.pokemon.controllers;

import com.yarin.pokemon.models.Trainer;
import com.yarin.pokemon.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class TrainersRankController {

    @Autowired
    TrainerRepository trainerRepository;

    @RequestMapping("/trainers")
    @CrossOrigin
    public ResponseEntity<?> trainersRank() {
        List<Trainer> list = trainerRepository.findAll();
        Trainer[] arr = new Trainer[list.size()];
        for(int i=0;i<arr.length;i++)
            arr[i]=list.get(i);
        Arrays.sort(arr);
        return ResponseEntity.status(HttpStatus.OK).body(arr);
    }
}