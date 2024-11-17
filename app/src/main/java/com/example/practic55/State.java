package com.example.practic55;

public class State {

    private int ID_State; //ID
    private String State_name; // название
    private String StateDescription;  // столица

    public State(int id, String name, String capital){
        this.ID_State = id;
        this.State_name = name;
        this.StateDescription = capital;
    }

    public String getState_name() {
        return this.State_name;
    }

    public void setState_name(String state_name) {
        this.State_name = state_name;
    }

    public String getStateDescription() {
        return this.StateDescription;
    }

    public void setStateDescription(String stateDescription) {
        this.StateDescription = stateDescription;
    }

    public int getID_State() {
        return this.ID_State;
    }

    public void setID_State(int id) {
        this.ID_State = id;
    }
}
