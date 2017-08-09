package com.sergii.shutyi.factorymethod;

/**
 * Creates different types of watch depends on client order.
 */
public class WatchFactoryMethod {
    public static void main(String[] args) {
        WatchCreator creator = new RomeWatchCreator();
        Watch watch = creator.factoryMethod();
        watch.showTime();
    }
}

interface Watch {
    void showTime();
}

class RomeWatch implements Watch {
    @Override
    public void showTime() {
        System.out.println("Rome watch: VIII-XI");
    }
}

class SwissWatch implements Watch {
    @Override
    public void showTime() {
        System.out.println("Swiss watch: 08.11");
    }
}

class DigitalWatch implements Watch {
    @Override
    public void showTime() {
        System.out.println("Digital watch: 08.11");
    }
}

abstract class WatchCreator {
    abstract Watch factoryMethod();
}

class RomeWatchCreator extends WatchCreator {
    @Override
    Watch factoryMethod() {
        return new RomeWatch();
    }
}

class SwissWatchCreator extends WatchCreator {
    @Override
    Watch factoryMethod() {
        return new SwissWatch();
    }
}

class DigitalWatchCreator extends WatchCreator {
    @Override
    Watch factoryMethod() {
        return new DigitalWatch();
    }
}
