import React, {useEffect,useState} from "react";
import DataService from "../services/data-service";


export default function TrainersRank() {
const [isLoading, setIsLoading] = useState(true);
const [trainers, setTrainers] = useState([]);
const [alertMessage, setAlertMessage] = useState(null);
useEffect(() => {
    onLoad();
}, []);

function onLoad() {
    DataService.getTrainers().then(
        response => {
            setTrainers(response.data);
        },
        error => {
            const resMessage =
            (error.response &&
              error.response.data &&
              error.response.data.message) ||
            error.message ||
            error.toString();
            setAlertMessage(resMessage);
        }
    )
    setIsLoading(false);
}


return (!isLoading &&
    <div className="container">
    {alertMessage && <div className="alert alert-danger">{alertMessage}</div>}
    <h3>Trainers Rank</h3>
    <div className="container">
        <table className="table">
            <thead>
                <tr>
                    <th>Rank</th>
                    <th>Trainer name</th>
                    <th>User name</th>
                    <th>Pokemons number</th>
                    <th>Level</th>
                </tr>
            </thead>
            <tbody>
                {
                    trainers.map(
                        (trainer,i) =>
                            <tr key={i+1}>
                                <td>{i+1}</td>
                                <td>{trainer.name}</td>
                                <td>{trainer.userName}</td>
                                <td>{trainer.numOfPokemons}</td>
                                <td>{trainer.level}</td>
                            </tr>
                    )
                }
            </tbody>
        </table>
    </div>
</div>

)
}


