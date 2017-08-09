package com.sergii.shutyi.builder;

/**
 * Creates tour depends on customer needs.
 */
public class TourBuilderApp {
    public static void main(String[] args) {
        Director director = new Director();
        Tour tour = director.construct();
    }
}

class Tour {
    boolean transferToAirport;
    boolean transferFromAirport;
    boolean flightTo;
    boolean flightBack;
    boolean hotelReservation;
    boolean insurance;

    public void setTransferToAirport(boolean transferToAirport) {
        this.transferToAirport = transferToAirport;
    }

    public void setTransferFromAirport(boolean transferFromAirport) {
        this.transferFromAirport = transferFromAirport;
    }

    public void setFlightTo(boolean flightTo) {
        this.flightTo = flightTo;
    }

    public void setFlightBack(boolean flightBack) {
        this.flightBack = flightBack;
    }

    public void setHotelReservation(boolean hotelReservation) {
        this.hotelReservation = hotelReservation;
    }

    public void setInsurance(boolean insurance) {
        this.insurance = insurance;
    }
}

interface TourBuilder{
    Tour getTour();

    void buildTransferToAirport(boolean transferToAirport);
    void buildTransferFromAirport(boolean transferFromAirport);
    void buildFlightTo(boolean flightTo);
    void buildFlightBack(boolean flightBack);
    void buildHotelReservation(boolean hotelReservation);
    void buildInsurance(boolean insurance);
}

class ConcreteTourBuilder implements TourBuilder {
    private Tour tour = new Tour();

    @Override
    public Tour getTour() {
        return tour;
    }

    @Override
    public void buildTransferToAirport(boolean transferToAirport) {
        tour.setTransferToAirport(transferToAirport);
    }

    @Override
    public void buildTransferFromAirport(boolean transferFromAirport) {
        tour.setTransferFromAirport(transferFromAirport);
    }

    @Override
    public void buildFlightTo(boolean flightTo) {
        tour.setFlightTo(flightTo);
    }

    @Override
    public void buildFlightBack(boolean flightBack) {
        tour.setFlightBack(flightBack);
    }

    @Override
    public void buildHotelReservation(boolean hotelReservation) {
        tour.setHotelReservation(hotelReservation);
    }

    @Override
    public void buildInsurance(boolean insurance) {
        tour.setInsurance(insurance);
    }
}

class Director {
    private TourBuilder tourBuilder;

    public Director() {
        this.tourBuilder = new ConcreteTourBuilder();
    }

    public Tour construct(){
        tourBuilder.buildTransferToAirport(true);
        tourBuilder.buildTransferFromAirport(true);
        tourBuilder.buildFlightTo(true);
        tourBuilder.buildFlightBack(true);
        tourBuilder.buildHotelReservation(false);
        tourBuilder.buildInsurance(true);
        return tourBuilder.getTour();
    }
}