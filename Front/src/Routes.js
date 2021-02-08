import React from "react";
import { Route, Switch } from "react-router-dom";
import Home from "./components/Home";
import NotFound from './components/NotFound';
import Login from './components/Login';
import Battle from './components/Battle';
import ProtectedRoute from './components/ProtectedRoute';
import SignUp from "./components/SignUp";
import PokemonCatch from "./components/PokemonCatch";
import TrainersRank from "./components/TrainersRank";
import CreateTrainer from "./components/CreateTrainer";
import TrainerManager from "./components/TrainerManager";



export default function Routes() {
  return (
    <Switch>
      <Route exact path="/">
        <Home />
      </Route>
      <Route exact path="/login">
        <Login />
      </Route>
      <Route exact path="/signup">
        <SignUp />
      </Route>
      <Route exact path="/battle">
        <ProtectedRoute comp={Battle} />
      </Route>
      <Route exact path="/create">
        <CreateTrainer />
      </Route>
      <Route exact path="/trainers">
        <ProtectedRoute comp={TrainersRank} />
      </Route>
      <Route exact path="/catch">
        <ProtectedRoute comp={PokemonCatch} />
      </Route>
      <Route exact path="/trainermanager">
        <ProtectedRoute comp={TrainerManager} />
      </Route>
      <Route>
        <NotFound />
      </Route>
    </Switch>
  );
}
