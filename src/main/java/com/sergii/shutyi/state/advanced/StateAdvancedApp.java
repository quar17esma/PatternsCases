package com.sergii.shutyi.state.advanced;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Human changes depends on his state.
 * Human can have several roles at the same time if he has several states.
 */
public class StateAdvancedApp {
    public static void main(String[] args) {
        Human human = new Human();

        human.addStates(new OnBeach());
        human.action();
        System.out.println();

        human.addStates(new OnMushroomGlade());
        human.action();
        System.out.println();

        human.clearStates();
        human.addStates(new OnMushroomGlade(), new SeeBeast());
        human.action();
    }
}

class Human {
    Set<State> states = new HashSet<>();

    void action(){
        if (!states.isEmpty()){
            for (State state:states) {
                state.action();
            }
        } else {
            throw new IllegalStateException("At least one state must be set for Human");
        }
    }

    void addStates(State... states){
        Collections.addAll(this.states, states);
    }

    void removeStates(State... states) {
        for (State state:states) {
            this.states.remove(state);
        }
    }

    void clearStates() {
        this.states.clear();
    }
}

interface State {
    void action();
}

class OnBeach implements State {
    @Override
    public void action() {
        System.out.println("I am fisherman");
    }
}
class OnMushroomGlade implements State {
    @Override
    public void action() {
        System.out.println("I am mushroomer");
    }
}

class SeeBeast implements State {
    @Override
    public void action() {
        System.out.println("I am hunter");
    }
}
