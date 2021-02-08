import axios from "axios";
import authHeader from './auth-header';
import localStorageService from "./localstorage-service";
import { useAppContext } from "../libs/contextlib";
import { useEffect } from "react";

const API_URL = "http://localhost:8080/";

class DataService{
    
    addTrainer(userName,trainerName){
        const request = {'userName': userName, 'trainerName': trainerName }
        return axios.post(API_URL + "trainer/create",request, { headers: authHeader() });
      }
    
    getUserTrainers(userName){
      return axios.get(API_URL + 'user/'+userName+'/trainers',{ headers: authHeader() })
    }

    catchPokemon(trainerName,pokemonName){
      const request = {'trainerName': trainerName, 'pokemonName': pokemonName }
      return axios.put(API_URL + "trainer/catch",request, { headers: authHeader() });
    }

    battle(trainer1,trainer2){
      const request = {'trainer1': trainer1, 'trainer2': trainer2 }
      return axios.put(API_URL + "trainer/battle",request, { headers: authHeader() });
    }

    getTrainers(){
      return axios.get(API_URL + "trainers", { headers: authHeader() });
    }

    getTrainer(trainerName){
      return axios.get(API_URL + "trainer/name", { headers: authHeader() });
    }




    
}

export default new DataService();
