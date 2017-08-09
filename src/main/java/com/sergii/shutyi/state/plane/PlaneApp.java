package com.sergii.shutyi.state.plane;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


/**
 * If plane in air and there are armor and suitable ammo,
 * plane shoots one time from each armor.
 */
public class PlaneApp {
    public static void main(String[] args) {
        Plane plane = new Plane();

        plane.addAmmo(Ammo.ROCKET, Ammo.CANNON);
        //initial Ammo: 1 rocket, 5 cannon, 10 machine guns
        plane.addArmors(Armor.MACHINE_GUN, Armor.CANNON, Armor.ROCKET);

        plane.setState(plane.new OnGround());
//        plane.shoot();    IllegalStateException

        plane.setState(plane.new InAir());
        plane.shoot();
        System.out.println();
        plane.shoot();
        System.out.println();
        plane.shoot();
    }
}

class Plane {
    State state;

    Set<Armor> armors = new HashSet<>();
    Set<Ammo> ammo = new HashSet<>();

    void shoot() {
        Objects.requireNonNull(state);
        state.shoot();
    }

    void addArmors(Armor... armors) {
        Objects.requireNonNull(armors);
        Collections.addAll(this.armors, armors);
    }

    void removeArmors(Armor... armors) {
        for (Armor armor : armors) {
            this.armors.remove(armor);
        }
    }

    void clearArmors() {
        this.armors.clear();
    }

    void addAmmo(Ammo... ammo) {
        Objects.requireNonNull(ammo);
        Collections.addAll(this.ammo, ammo);
    }

    void removeAmmo(Ammo... ammo) {
        for (Ammo a : ammo) {
            this.ammo.remove(a);
        }
    }

    void clearAmmo() {
        this.ammo.clear();
    }

    public void setState(State state) {
        this.state = Objects.requireNonNull(state);
    }

    class OnGround implements State {
        @Override
        public void shoot() {
            throw new IllegalStateException("Forbidden to shoot on the ground");
        }
    }

    class InAir implements State {
        /**
         * Method checks armor and suitable ammo and
         * shoots from one time from each armor.
         * @throws IllegalArgumentException if there is no armor or ammo on the plane
         */
        @Override
        public void shoot() {
            Set<Armor> armors = Plane.this.armors;
            Set<Ammo> ammo = Plane.this.ammo;

            if (armors.isEmpty() || ammo.isEmpty()) {
                throw new IllegalStateException("Empty armor or ammo");
            } else {
                for (Armor armor : armors) {
                    for (Ammo am : ammo) {
                        if (!am.isEmpty() && armor.name().equals(am.name())) {
                            am.quantity--;
                            System.out.println("Shooting from " + armor.name());
                        }
                    }
                }

            }
        }
    }
}

interface State {
    void shoot();
}

