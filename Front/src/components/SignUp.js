import React, {useState} from "react";
import { Button, FormGroup, FormControl, ControlLabel } from "react-bootstrap";
import "../css/SignUp.css";
import { useHistory } from 'react-router-dom'
import { useFormFields } from "../libs/useFormFields";
import AuthService from "../services/auth.service";



export default function SignUp() {
    const history = useHistory();  
    const [alertMessage, setAlertMessage] = useState(null);
    const [fields, handleFieldChange] = useFormFields({
        userName: "",
        email: "",
        password: "",
        repeatPassword: ""
      });

    function validateForm() {
        return fields.userName.length > 0 && fields.password.length > 0 && fields.repeatPassword.length > 0;
    }

    function resetFileds() {
      fields.userName= "";
      fields.email= "";
      fields.password= "";
      fields.repeatPassword= "";
    }


    async function handleSubmit(event) {
        event.preventDefault();
        if(!(fields.password === fields.repeatPassword)){
            setAlertMessage("Passwords don't match");
        }
        else{
        AuthService.register(
          fields.userName,
          fields.email,
          fields.password
        ).then(
          response => {
            resetFileds();
            history.push("/login");
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
  
      }

    return (
      <>
        {alertMessage && <div className="alert alert-danger">{alertMessage}</div>}
        <div className="SignUp">
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
          <FormGroup controlId="email" bsSize="large">
            <ControlLabel>Email</ControlLabel>
            <FormControl
              autoFocus
              type="email"
              value={fields.email}
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
          <FormGroup controlId="repeatPassword" bsSize="large">
            <ControlLabel>Repeat Password</ControlLabel>
            <FormControl
              value={fields.repeatPassword}
              onChange={handleFieldChange}            
              type="password"
            />
          </FormGroup>

          <Button block bsSize="large" disabled={!validateForm()} type="submit">
            Sign Up
          </Button>
        </form>
      </div>
    </>
    );
}
