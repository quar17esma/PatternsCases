package com.sergii.shutyi.plane;

public enum Ammo {
    MACHINE_GUN(10), CANNON(5), ROCKET(1);

    int quantity;

    Ammo(int quantity) {
        if (quantity >= 0){
            this.quantity = quantity;
        } else {
            throw new IllegalArgumentException("quantity can not be negative");
        }
    }

    boolean isEmpty(){
        if (quantity > 0){
            return false;
        }
        return true;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity >= 0){
            this.quantity = quantity;
        } else {
            throw new IllegalArgumentException("quantity can not be negative");
        }
    }
}
