import React, { useState,useEffect } from "react";
import { Link } from "react-router-dom";
import { Navbar,Nav,NavItem } from "react-bootstrap";
import "./css/App.css";
import { useHistory } from "react-router-dom";
import Routes from "./Routes";
import { LinkContainer } from "react-router-bootstrap";
import { AppContext } from "./libs/contextlib";
import AuthService from "./services/auth.service";
import LocalStorageService from "./services/localstorage-service";




function App() {
  const [isAuthenticating, setIsAuthenticating] = useState(true);
  const [isAuthenticated, userHasAuthenticated] = useState(false);
  const [stateUserName,setStateUserName] = useState('');

  const history = useHistory();

  useEffect(() => {
    onLoad();
  }, []);

  const handleLogout = () => {
    userHasAuthenticated(false);
    setStateUserName('');
    localStorage.removeItem('activeTrainer');
    LocalStorageService.logout();
    LocalStorageService.removeActiveTrainer();
    AuthService.logout();
    history.push("/login")
  }

  const currentUser = () => {
    alert(isAuthenticated);
  }

  const onLoad = ()=>{
  if(sessionStorage.getItem('login')==='true'){
    userHasAuthenticated(true);
    setStateUserName(LocalStorageService.getUserName);
  }
  setIsAuthenticating(false);
}
  
  return (
    !isAuthenticating &&
    <div className="App container">
      <Navbar fluid collapseOnSelect>
        <Navbar.Header>
          <Navbar.Brand>
            <Link to="/">Home</Link>
          </Navbar.Brand>
          { (isAuthenticated) ? 
          <>
          <Navbar.Brand>
            <Link to="/trainermanager">Trainer Manager</Link>
          </Navbar.Brand>
          <Navbar.Brand>
            <Link to="/battle">Battle</Link>
          </Navbar.Brand>
          <Navbar.Brand>
            <Link to="/catch">Catch-Pokemon</Link>
          </Navbar.Brand>
          <Navbar.Brand>
            <Link to="/trainers">Trainers-Rank</Link>
          </Navbar.Brand>

          </>
           :
            <> </>}
          <Navbar.Toggle />
        </Navbar.Header>
        <Navbar.Collapse>
          <Nav pullRight>
          {(isAuthenticated)
  ? <NavItem onClick={handleLogout}>Logout</NavItem>
  : <>
      <LinkContainer to="/signup">
        <NavItem>Signup</NavItem>
      </LinkContainer>
      <LinkContainer to="/login">
        <NavItem>Login</NavItem>
      </LinkContainer>

    </>
}
          </Nav>
        </Navbar.Collapse>
      </Navbar>
      <AppContext.Provider value={{ isAuthenticated,userHasAuthenticated,stateUserName,setStateUserName}}>
        <Routes />
      </AppContext.Provider>
    </div>
  );
}


export default App;
