import axios from "axios";
import authHeader from './auth-header';

const API_URL = "http://localhost:8080/api/auth/";
const url = "http://localhost:8080/"

class AuthService {
  login(username, password) {
    return axios
      .post(API_URL + "signin", {
        username,
        password
      })
      .then(response => {
        if (response.data) {
          localStorage.setItem("user", JSON.stringify(response.data));
        }
        return response.data;
      });
  }

  logout() {
    localStorage.removeItem("user");
  }

  register(username, email, password) {
    return axios.post(API_URL + "signup", {
      username,
      email,
      password,
      'roles': ["user"]
    });
  }

  getCurrentUser() {
    return JSON.parse(localStorage.getItem('user'));
  }
  
}

export default new AuthService();
