package com.yarin.pokemon.payload.request;

public class AddTrainerRequest {

    private String userName;
    private String trainerName;

    public AddTrainerRequest(String userName, String trainerName) {
        this.userName = userName;
        this.trainerName = trainerName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTrainerName() {
        return trainerName;
    }

    public void setTrainerName(String trainerName) {
        this.trainerName = trainerName;
    }

}
