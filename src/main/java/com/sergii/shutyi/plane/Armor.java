package com.sergii.shutyi.plane;

public enum Armor {
    MACHINE_GUN{
        {
            this.strategy = new MachineGun();
        }
    },
    CANNON{
        {
            this.strategy = new Cannon();
        }
    },
    ROCKET{
        {
            this.strategy = new Rocket();
        }
    };

    Strategy strategy;

    public Strategy getStrategy() {
        return strategy;
    }
}
