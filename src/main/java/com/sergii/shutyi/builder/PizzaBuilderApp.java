package com.sergii.shutyi.builder;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Builds ordered pizza and counts its price.
 */
public class PizzaBuilderApp {
    public static void main(String[] args) {
        PizzaBuilder builder = new ConcretePizzaBuilder();
        PizzaDirector director = new PizzaDirector(builder);
        Pizza pizza = director.construct(PizzaBase.MIDDLE, Adds.TOMATO_SOUCE,Adds.PAPPERONI, Adds.GARLIC);
        System.out.println(pizza);
        System.out.println(pizza.getPrice());

    }
}

class PizzaDirector {
    private PizzaBuilder pizzaBuilder;

    public PizzaDirector(PizzaBuilder pizzaBuilder) {
        this.pizzaBuilder = pizzaBuilder;
    }

    Pizza construct(PizzaBase pizzaBase, Adds... adds){
        pizzaBuilder.buildPizzaBase(pizzaBase);
        pizzaBuilder.buildAdds(adds);
        pizzaBuilder.addProfit(2);

        return pizzaBuilder.getPizza();
    }
}

interface PizzaBuilder {
    Pizza getPizza();

    void buildPizzaBase(PizzaBase pizzaBase);
    void buildAdds(Adds... adds);
    void addProfit(int profit);
}

class ConcretePizzaBuilder implements PizzaBuilder{
    private Pizza pizza = new Pizza();

    @Override
    public Pizza getPizza() {
        return pizza;
    }

    @Override
    public void buildPizzaBase(PizzaBase pizzaBase) {
        pizza.addToPrice(pizzaBase.price);
        pizza.setPizzaBase(pizzaBase);
    }

    @Override
    public void buildAdds(Adds... adds) {
        for (Adds add:adds) {
            pizza.addToPrice(add.price);
        }
        pizza.setAdds(adds);
    }

    @Override
    public void addProfit(int profit) {
        pizza.addToPrice(profit);
    }
}


class Pizza {
    private PizzaBase pizzaBase;
    private ArrayList<Adds> adds = new ArrayList<>();
    private int price = 0;

    public void addToPrice(int price){
        this.price += price;
    }

    public void setPizzaBase(PizzaBase pizzaBase) {
        this.pizzaBase = pizzaBase;
    }

    public void setAdds(Adds... adds) {
        Collections.addAll(this.adds, adds);
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Pizza{" +
                "pizzaBase=" + pizzaBase +
                ", adds=" + adds +
                '}';
    }
}

enum PizzaBase {
    SMALL(3), MIDDLE(5), BIG(7);

    int price;

    PizzaBase(int price) {
        this.price = price;
    }
}
enum Adds{
    TOMATO_SOUCE(1), GARLIC(1), PAPPERONI(3), MAZZARELLA(2), PARMESAN(2), MUSHROOMS(1), TOMATO(1);

    int price;

    Adds(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return name();
    }
}