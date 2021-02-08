package com.yarin.pokemon.payload.request;

public class BattleRequest {

    private String trainer1;
    private String trainer2;

    public BattleRequest(String trainer1, String trainer2) {
        this.trainer1 = trainer1;
        this.trainer2 = trainer2;
    }

    public String getTrainer1() {
        return trainer1;
    }

    public void setTrainer1(String trainer1) {
        this.trainer1 = trainer1;
    }

    public String getTrainer2() {
        return trainer2;
    }

    public void setTrainer2(String trainer2) {
        this.trainer2 = trainer2;
    }
}
