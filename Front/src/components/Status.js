import React, {useEffect,useState } from "react";
import { useAppContext } from "../libs/contextlib";
import DataService from "../services/data-service";
import LocalStorageService from "../services/localstorage-service";


export default function Status() {
const [isLoading, setIsLoading] = useState(true);
const [trainer, setTrainer] = useState({});
const [pokemons,setPokemons] = useState([]);
const {stateUserName} = useAppContext();


useEffect(() => {
onLoad();
}, []);

function onLoad() {
    console.log(LocalStorageService.getActiveTrainer())
    DataService.getTrainer(LocalStorageService.getActiveTrainer()).then(
        response => {
        setTrainer(response.data);
        if(response.data.bag[0] == null)
            setPokemons([]);
        else
            setPokemons(response.data.bag);
    });
    setIsLoading(false);
    }
    


return (!isLoading &&
<div>
    <h1>Trainer name: {trainer.userName}</h1>
    <h1>Trainer level: {trainer.level}</h1> 
    <h1>Trainer number of pokemons: {trainer.numOfPokemons}</h1>
    <h1>Pokemon list: </h1>
    <div>
    <ul>
{ pokemons.map(pokemon => pokemon != null ? <li >{pokemon.name} of type: {pokemon.type}</li> : <li></li>) }
    </ul>            
</div>


</div>)
}


