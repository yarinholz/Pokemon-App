import React, {useState} from "react";
import { Button, FormGroup, FormControl, ControlLabel } from "react-bootstrap";
import "../css/CreateTrainer.css";
import { useFormFields } from "../libs/useFormFields";
import DataService from "../services/data-service";
import LocalStorageService from "../services/localstorage-service";
import { useHistory } from 'react-router-dom'





export default function CreateTrainer() {
  const history = useHistory();
  const [fields, handleFieldChange] = useFormFields({trainerName: "" });
  const [alertMessage, setAlertMessage] = useState(null);


  function handleSubmit(event) {
      event.preventDefault();
      DataService.addTrainer(LocalStorageService.getUserName(),fields.trainerName).then(
          response => {
              LocalStorageService.addTrainer(fields.trainerName);
                if(LocalStorageService.getUserTrainers().length === 1)
              LocalStorageService.setActiveTrainer(LocalStorageService.getUserTrainers()[0]);
              history.push('/trainermanager');
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
        );
  }
  

  
  return (
      <>
      {alertMessage && <div className="alert alert-danger">{alertMessage}</div>}
      <div className="CreateTrainer">
      <form onSubmit={handleSubmit}>
        <FormGroup controlId="trainerName" bsSize="large">
          <ControlLabel>Trainer Name</ControlLabel>
          <FormControl
            autoFocus
            type="trainerName"
            value={fields.trainerName}
            onChange={handleFieldChange}
          />
        </FormGroup>
        <Button block bsSize="large"  type="submit">
          Submit
        </Button>
      </form>
    </div>
    </>
  
  )

    }