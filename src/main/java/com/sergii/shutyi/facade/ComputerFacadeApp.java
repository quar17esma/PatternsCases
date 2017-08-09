package com.sergii.shutyi.facade;

/**
 * Hides complicated implementation of computer behind simple interface.
 */
public class ComputerFacadeApp {
    public static void main(String[] args) {
        IComputer computer = new Computer();

        computer.on();
        System.out.println();

        computer.off();
    }
}

interface IComputer {
    void on();

    void off();
}

class Computer implements IComputer {
    private Chipset chipset = new Chipset();
    private Processor processor = new Processor();
    private Ram ram = new Ram();

    @Override
    public void on() {
        chipset.chipsetOn();
        processor.processorOn();
        ram.ramOn();
    }

    @Override
    public void off() {
        ram.ramOff();
        processor.processorOff();
        chipset.chipsetOff();
    }

    class Chipset {
        void chipsetOn(){
            System.out.println("Chipset on");
        }
        void chipsetOff(){
            System.out.println("Chipset off");
        }
    }

    class Processor  {
        void processorOn(){
            System.out.println("Processor on");
        }

        void processorOff(){
            System.out.println("Processor off");
        }
    }

    class Ram {
        void ramOn(){
            System.out.println("Ram on");
        }

        void ramOff(){
            System.out.println("Ram off");
        }
    }
}