package com.sergii.shutyi.state;

import java.util.Objects;

/**
 * Human changes depends on his state
 */
public class HumanStatesApp {
    public static void main(String[] args) {
        Human human = new Human();

        human.setState(new OnBeach());
        human.action();

        human.setState(new OnMushroomGlade());
        human.action();

        human.setState(new SeeBeast());
        human.action();
    }
}

class Human {
    State state;

    public void action(){
        Objects.requireNonNull(state);
        state.action();
    }

    public void setState(State state) {
        this.state = Objects.requireNonNull(state);
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