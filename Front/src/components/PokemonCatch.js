import React, {useState} from "react";
import { Button, FormGroup, FormControl, ControlLabel } from "react-bootstrap";
import "../css/PokemonCatch.css";
import DataService from "../services/data-service";
import LocalStorageService from "../services/localstorage-service";
import { useFormFields } from "../libs/useFormFields";


export default function PokemonCatch() {
    const [fields, handleFieldChange] = useFormFields({pokemonName: "" });
    const [alertMessage, setAlertMessage] = useState(null);
    const [succsesMessage, setSuccsesMessage] = useState(null);


        

    function handleSubmit(event) {
        setAlertMessage(null);
        setSuccsesMessage(null);
        event.preventDefault();
        DataService.catchPokemon(LocalStorageService.getActiveTrainer(),fields.pokemonName).then(
          response => {
            setSuccsesMessage(response.data);
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
    }

    return (
        <div className="PokemonCatch">
          <form onSubmit={handleSubmit}>
          {alertMessage && <div className="alert alert-danger">{alertMessage}</div>}
          {succsesMessage && <div className="alert alert-success">{succsesMessage}</div>}
            <FormGroup controlId="pokemonName" bsSize="large">
              <ControlLabel>Pokemon Name</ControlLabel>
              <FormControl
                autoFocus
                type="pokemonName"
                value={fields.pokemonName}
                onChange={handleFieldChange}
              />
            </FormGroup>
            <Button block bsSize="large" type="submit">
              Catch
            </Button>
          </form>
        </div>
      );
}
