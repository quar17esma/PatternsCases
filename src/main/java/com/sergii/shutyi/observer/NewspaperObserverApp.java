package com.sergii.shutyi.observer;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Publisher prints newspaper and sends it to all subscribers.
 */
public class NewspaperObserverApp {
    public static void main(String[] args) {
        Publisher publisher = new Publisher();

        Subscriber subscriber1 = new Subscriber("Ivan");
        Subscriber subscriber2 = new Subscriber("John");
        Subscriber subscriber3 = new Subscriber("Alex");

        publisher.attach(subscriber1);
        publisher.attach(subscriber2);
        publisher.attach(subscriber3);

        publisher.printNewspaper();
        System.out.println();

        publisher.detach(subscriber1);
        publisher.detach(subscriber2);
        publisher.printNewspaper();
    }
}

interface Subject {
    void attach(Observer observer);
    void detach(Observer observer);
    void notifyObservers();
}

class Publisher implements Subject {
    ArrayList<Observer> observers = new ArrayList<>();

    private Newspaper newspaper;

    void printNewspaper() {
        this.newspaper = new Newspaper();
        notifyObservers();
    }

    @Override
    public void attach(Observer observer) {
        observers.add(Objects.requireNonNull(observer));
    }

    @Override
    public void detach(Observer observer) {
        observers.remove(Objects.requireNonNull(observer));
    }

    @Override
    public void notifyObservers() {
        if (observers != null && !observers.isEmpty()){
            for (Observer observer:observers) {
                observer.sendNewspaper(newspaper);
            }
        }
    }
}
class Newspaper {
}

interface Observer {
    void sendNewspaper(Newspaper newspaper);
}

class Subscriber implements Observer {
    String name;

    public Subscriber(String name) {
        this.name = Objects.requireNonNull(name);
    }

    @Override
    public void sendNewspaper(Newspaper newspaper) {
        System.out.println("Subscriber " + name + " gets newspaper");
    }
}