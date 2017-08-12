package com.sergii.shutyi.plane;

public class StateAndStretegy {
    public static void main(String[] args) {
        Plane plane = new Plane(new OnGround());
//        plane.shoot();    Exception - can not shoot on ground

        plane.changeState();
        plane.setAmmo(Ammo.MACHINE_GUN);
        plane.setArmor(Armor.ROCKET);
//        plane.shoot();    Exception - there is no suitable ammo

        plane.setAmmo(Ammo.ROCKET);
        plane.shoot();
//        plane.shoot();    Exception - there is no suitable ammo(Ammo.ROCKET has initial quantity of 1)
    }
}

class Plane {
    private State state;

    Armor armor;
    Ammo ammo;

    public Plane(State state) {
        this.state = state;
    }

    public Plane(State state, Armor armor, Ammo ammo) {
        this.state = state;
        this.armor = armor;
        this.ammo = ammo;
    }

    void shoot() {
        state.shoot(this);
    }

    void changeState() {
        state.changeState(this);
    }

    public void setState(State state) {
        this.state = state;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }

    public void setAmmo(Ammo ammo) {
        this.ammo = ammo;
    }
}

interface State {
    void shoot(Plane plane);

    void changeState(Plane plane);
}

class InAir implements State {
    Strategy strategy;

    @Override
    public void shoot(Plane plane) {
        if (isReadyToShoot(plane)) {
            strategy = plane.armor.getStrategy();
            strategy.shoot(plane.ammo);
        } else {
            throw new UnsupportedOperationException("There is no armor or suitable ammo on the plane");
        }

    }

    private boolean isReadyToShoot(Plane plane) {
        if (plane.armor == null ||
                plane.ammo == null ||
                plane.ammo.isEmpty() ||
                !plane.armor.name().equalsIgnoreCase(plane.ammo.name())) {
            return false;
        }

        return true;
    }

    @Override
    public void changeState(Plane plane) {
        plane.setState(new OnGround());
    }
}

class OnGround implements State {

    @Override
    public void shoot(Plane plane) {
        throw new UnsupportedOperationException("Plane can not shoot on the ground");
    }

    @Override
    public void changeState(Plane plane) {
        plane.setState(new InAir());
    }
}

interface Strategy {
    void shoot(Ammo ammo);
}

class MachineGun implements Strategy {
    @Override
    public void shoot(Ammo ammo) {
        ammo.setQuantity(ammo.getQuantity()-1);
        System.out.println("Shoot from Machine gun!!!");
    }
}

class Rocket implements Strategy {
    @Override
    public void shoot(Ammo ammo) {
        ammo.setQuantity(ammo.getQuantity()-1);
        System.out.println("Shoot from Rocket");
    }
}

class Cannon implements Strategy {
    @Override
    public void shoot(Ammo ammo) {
        ammo.setQuantity(ammo.getQuantity()-1);
        System.out.println("Shoot from Cannon");
    }
}