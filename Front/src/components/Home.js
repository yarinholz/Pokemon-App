import React from "react";
import "../css/Home.css";
import { useAppContext } from "../libs/contextlib";
import LocalStorageService from "../services/localstorage-service";



export default function Home() {
  const { userHasAuthenticated} = useAppContext();

  return (
    <div className="Home">
      <div className="lander">
        <h1>Pokemon GT</h1>
        <p>Catch them all!!</p>
        {userHasAuthenticated && <h3>welcome {localStorage.getItem("user") == null ? '' :JSON.parse(localStorage.getItem("user")).username}</h3>}
        {LocalStorageService.getActiveTrainer() === null ? <h3>No trainers, please create one in the trainers manager</h3> : <h3>Active Trainer: {LocalStorageService.getActiveTrainer()} </h3> }
      </div>
    </div>
  );
}