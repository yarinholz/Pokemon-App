import React, {useEffect,useState} from "react";
import DataService from "../services/data-service";
import LocalStorageService from "../services/localstorage-service";
import { useHistory } from 'react-router-dom'




export default function TrainersRank() {
const history = useHistory();
const [activeTrainer,setActiveTrainer] = useState(LocalStorageService.getActiveTrainer());
const [alertMessage, setAlertMessage] = useState(null);
const [isLoading, setIsLoading] = useState(true);
const [trainers, setTrainers] = useState([]);


    useEffect(() => {
        onLoad();
        }, []
        );

     function onLoad() {
        DataService.getUserTrainers(LocalStorageService.getUserName()).then((res) => {
            setTrainers(res.data);
        },
        error => {
            const resMessage =
            (error.response &&
              error.response.data &&
              error.response.data.message) ||
            error.message ||
            error.toString();
            setAlertMessage(resMessage);

        });
        setIsLoading(false);
    }

    function createTrainer(){
        history.push('/create');
    }

    function setActive(trainerName){
        LocalStorageService.setActiveTrainer(trainerName);
        setActiveTrainer(trainerName);
    }



    return (!isLoading &&
        <div className="container">
            {alertMessage && <div className="alert alert-danger">{alertMessage}</div>}
            <h3>My Trainers</h3>
            <div className="container">
                <table className="table">
                    <thead>
                        <tr>
                            <th>Rank</th>
                            <th>Trainer name</th>
                            <th>Level</th>
                            <th>Number of Pokemons</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            trainers.map(
                                (trainer,i) =>
                                    <tr key={i+1}>
                                        <td>{i+1}</td>
                                        <td>{trainer.name}</td>
                                        <td>{trainer.level}</td>
                                        <td>{trainer.numOfPokemons}</td>
                                    { LocalStorageService.getActiveTrainer() == trainer.name ?<td></td> : <td><button className="btn btn-success" onClick={() => {setActive(trainer.name);}}>Set active </button></td>}
                                    </tr>
                            )
                        }
                    </tbody>
                </table>
            </div>
            <div className="row">
                <button className="btn btn-success" onClick={createTrainer}>Create</button>
            </div>
        </div>

    )
    }


