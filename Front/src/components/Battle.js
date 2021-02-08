import React, {useState}  from "react";
import { Button, FormGroup, FormControl, ControlLabel } from "react-bootstrap";
import "../css/Battle.css";
import DataService from "../services/data-service";
import LocalStorageService from "../services/localstorage-service";
import { useFormFields } from "../libs/useFormFields";


export default function Battle() {
    const [fields, handleFieldChange] = useFormFields({opponent: "" });
    const [alertMessage, setAlertMessage] = useState(null);
    const [succsesMessage, setSuccsesMessage] = useState(null);

        

    function handleSubmit(event) {
      setAlertMessage(null);
      setSuccsesMessage(null);
      event.preventDefault();
      DataService.battle(LocalStorageService.getActiveTrainer(),fields.opponent).then(
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
        <div className="Battle">
          {alertMessage && <div className="alert alert-danger">{alertMessage}</div>}
          {succsesMessage && <div className="alert alert-success">{succsesMessage}</div>}
          <form onSubmit={handleSubmit}>
            <FormGroup controlId="opponent" bsSize="large">
              <ControlLabel>Opponent</ControlLabel>
              <FormControl
                autoFocus
                type="opponent"
                value={fields.opponent}
                onChange={handleFieldChange}
              />
            </FormGroup>
            <Button block bsSize="large" type="submit">
              Fight
            </Button>
          </form>
        </div>
      );
}
