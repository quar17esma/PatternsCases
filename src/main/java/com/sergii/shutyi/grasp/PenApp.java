package com.sergii.shutyi.grasp;


public class PenApp {
    public static void main(String[] args) {
        Writable pen = new Pen(Color.RED);
        Writer writer = new Writer("John", pen);
        writer.write("I am writer, my name is " + writer.getName());
    }
}

class Human {
    private String name;

    public Human(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Writer extends Human {
    private Writable writable;

    public Writer(String name, Writable writable) {
        super(name);
        this.writable = writable;
    }

    void write(String string) {
        writable.write(string);
    }
}


interface Writable {
    void write(String string);
}

class Pen implements Writable {
    Color color;

    public Pen(Color color) {
        this.color = color;
    }

    @Override
    public void write(String string) {
        System.out.println("Write with " + color.name().toLowerCase() + " color: " + string);
    }
}

enum Color {
    RED, GREEN, BLACK
}