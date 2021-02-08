
class LocalStorageService {

getUserName(){
    if(localStorage.getItem('user')!=null)
        return JSON.parse(localStorage.getItem('user')).username
    else
        return "";
}

setLogin(){
    localStorage.setItem('login','true');
}

getUserObj(){
    return JSON.parse(localStorage.getItem('user'));
}

addTrainer(trainerName){
    let userObj = JSON.parse(localStorage.getItem('user'));
    userObj.trainers.push(trainerName);
    localStorage.setItem("user", JSON.stringify(userObj));
}

getUserTrainers(){
    if(localStorage.getItem('user')!=null)
        return JSON.parse(localStorage.getItem('user')).trainers;
}

setActiveTrainer(trainerName){
    localStorage.setItem('activeTrainer',trainerName);
}

getActiveTrainer(){
   return localStorage.getItem('activeTrainer');
}

removeActiveTrainer(){
    localStorage.setItem('activeTrainer','');
}
logout(){
    localStorage.setItem('login','false');
}



}

export default new LocalStorageService();


