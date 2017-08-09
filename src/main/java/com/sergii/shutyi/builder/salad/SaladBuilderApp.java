package com.sergii.shutyi.builder.salad;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Makes Salad from ingredients and previously checks their availability.
 */
public class SaladBuilderApp {
    public static void main(String[] args) {
        SaladBuilder builder = new ConcreteSaladBuilder();
        SaladDirector director = new SaladDirector(builder);
        Salad salad;
        try {
            salad = director.construct(Ingredients.TOMATO, Ingredients.PEPPERONI, Ingredients.GARLIC);
            System.out.println(salad);
            salad = director.construct(Ingredients.MAZZARELLA, Ingredients.MUSHROOMS, Ingredients.CUCUMBER);
            System.out.println(salad);
            salad = director.construct(Ingredients.TOMATO, Ingredients.PEPPERONI, Ingredients.GARLIC);
            System.out.println(salad);
        } catch (IllegalArgumentException e) {
            System.out.println("Sorry Not Enough Ingredients");
        }

    }
}

class SaladDirector {
    private SaladBuilder saladBuilder;

    public SaladDirector(SaladBuilder saladBuilder) {
        this.saladBuilder = saladBuilder;
    }

    Salad construct(Ingredients... adds) throws IllegalArgumentException {
        saladBuilder.buildIngredients(adds);

        return saladBuilder.getSalad();
    }
}

interface SaladBuilder {
    Salad getSalad();

    void buildIngredients(Ingredients... adds);
}

class ConcreteSaladBuilder implements SaladBuilder {
    private Salad salad = new Salad();

    @Override
    public Salad getSalad() {
        return salad;
    }

    @Override
    public void buildIngredients(Ingredients... ingredients) throws IllegalArgumentException {
        if (checkIngredients(ingredients)){
            for (Ingredients ingredient : ingredients) {
                    ingredient.available--;
            }
            salad.setIngredients(ingredients);
        } else {
            throw new IllegalArgumentException("Sorry not enough ingredients");
        }
    }

    /**
     * Checks are there enough ingredients for salad.
     * @param ingredients - ingredients needed for salad
     * @return {@code true} if there are enough ingredients.
     */
    private boolean checkIngredients(Ingredients... ingredients) {
        Map<Ingredients, Integer> need = new HashMap<>();

        for (Ingredients ingredient : ingredients) {
            if (need.containsKey(ingredient)) {
                need.put(ingredient, need.get(ingredient) + 1);
            } else {
                need.put(ingredient, 1);
            }
        }

        for (Map.Entry<Ingredients, Integer> entry : need.entrySet()) {
            if (entry.getValue() > entry.getKey().available) {
                return false;
            }
        }

        return true;
    }
}


class Salad {
    private ArrayList<Ingredients> ingredients = new ArrayList<>();

    public void setIngredients(Ingredients... ingredients) {
        Collections.addAll(this.ingredients, ingredients);
    }

    @Override
    public String toString() {
        return "Salad{" +
                "ingredients=" + ingredients +
                '}';
    }
}

enum Ingredients {
    CUCUMBER(2), GARLIC(1), PEPPERONI(3), MAZZARELLA(2), EGG(4), MUSHROOMS(1), TOMATO(1), OIL(6);

    int available;

    Ingredients(int available) {
        if (available >= 0) {
            this.available = available;
        } else {
            throw new IllegalArgumentException("Available ingredients can not be negative value");
        }
    }

    @Override
    public String toString() {
        return name();
    }
}
