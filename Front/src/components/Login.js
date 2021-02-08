import React, {useState} from "react";
import { Button, FormGroup, FormControl, ControlLabel } from "react-bootstrap";
import "../css/Login.css";
import { useHistory } from 'react-router-dom'
import { useAppContext } from "../libs/contextlib";
import { useFormFields } from "../libs/useFormFields";
import AuthService from "../services/auth.service";
import LocalStorageService from "../services/localstorage-service";



export default function Login() {
  const { userHasAuthenticated,setStateUserName,setActiveTrainer} = useAppContext();
  const [alertMessage] = useState(null);
  const history = useHistory();
  const [fields, handleFieldChange] = useFormFields({
    userName: "",
    password: ""
  });



  function validateForm() {
    return fields.userName.length > 0 && fields.password.length > 0;
  }

  async function handleSubmit(event) {
    event.preventDefault();
    AuthService.login(fields.userName, fields.password).then(
      () => {
        userHasAuthenticated(true);
        setStateUserName(LocalStorageService.getUserName());
        LocalStorageService.setLogin();
        let trainers = LocalStorageService.getUserTrainers();
        if(trainers.length>0){
          LocalStorageService.setActiveTrainer(trainers[0]);
          console.log( LocalStorageService.getActiveTrainer());
        }
        history.push("/");
      },
      error => {
        const resMessage =
          (error.response &&
            error.response.data &&
            error.response.data.message) ||
          error.message ||
          error.toString();

      }
    );
    
      }

  return (
    <>
    {alertMessage && <div className="alert alert-danger">{alertMessage}</div>}
    <div className="Login">
      <form onSubmit={handleSubmit}>
        <FormGroup controlId="userName" bsSize="large">
          <ControlLabel>User Name</ControlLabel>
          <FormControl
            autoFocus
            type="userName"
            value={fields.userName}
            onChange={handleFieldChange}
          />
        </FormGroup>
        <FormGroup controlId="password" bsSize="large">
          <ControlLabel>Password</ControlLabel>
          <FormControl
            value={fields.password}
            onChange={handleFieldChange}
            type="password"
          />
        </FormGroup>
        <Button block bsSize="large" disabled={!validateForm()} type="submit">
          Login
        </Button>
      </form>
    </div>
  </>  
  );
}