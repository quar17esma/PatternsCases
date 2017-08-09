package com.sergii.shutyi.facade;

public class SecretaryFacadeApp {
    public static void main(String[] args) {
        Secretary secretary = new Secretary(new Director(), new Accountant(), new Warehouse(), new ManagerCurator());

        secretary.askPromotion();
        System.out.println();

        secretary.askSalary();
    }
}



interface ISecretary {
    void askPromotion();
    void askSalary();
}

class Secretary implements ISecretary{
    private Director director;
    private Accountant accountant;
    private Warehouse warehouse;
    private ManagerCurator managerCurator;

    public Secretary(Director director, Accountant accountant, Warehouse warehouse, ManagerCurator managerCurator) {
        this.director = director;
        this.accountant = accountant;
        this.warehouse = warehouse;
        this.managerCurator = managerCurator;
    }

    @Override
    public void askPromotion() {
        managerCurator.giveFeedback();
        accountant.checkSalaryBalance();
        director.giveOrder();
    }

    @Override
    public void askSalary() {
        warehouse.storeProduct();
        accountant.checkSalaryBalance();
        director.giveOrder();
    }
}

class Director {
    void giveOrder(){
        System.out.println("Director gives an order");
    }
}

class Accountant {
    void checkSalaryBalance(){
        System.out.println("Accountant checks salary balance");
    }
}

class Warehouse {
    void storeProduct(){
        System.out.println("Store product made by employee on warehouse");
    }
}

class ManagerCurator{
    void giveFeedback(){
        System.out.println("Manager gives Feedback on employee");
    }
}