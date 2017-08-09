package com.sergii.shutyi.adapter;

/**
 * Connects Computer with SVGA to DVI through adapter
 */
public class ComputerAdapterApp {
    public static void main(String[] args) {
        SVGASocket svgaAdapter = new SvgaToDviAdapter(new DVISocket());
        Computer computer = new Computer(svgaAdapter);
        computer.execute();
    }
}

class Computer {
    private SVGASocket svgaSocket;

    public Computer(SVGASocket svgaSocket) {
        this.svgaSocket = svgaSocket;
    }

    public void execute(){
        svgaSocket.showSVGA();
    }
}

interface SVGASocket {
    void showSVGA();
}

class DVISocket {
    void showDVI(){
        System.out.println("show in DVI");
    }
}

class SvgaToDviAdapter implements SVGASocket {
    private DVISocket dviSocket;

    public SvgaToDviAdapter(DVISocket dviSocket) {
        this.dviSocket = dviSocket;
    }

    @Override
    public void showSVGA() {
        dviSocket.showDVI();
    }
}