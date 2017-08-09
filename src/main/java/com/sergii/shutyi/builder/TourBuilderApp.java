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
    void buildtransferFromAirport(boolean transferFromAirport);
    void buildflightTo(boolean flightTo);
    void buildflightBack(boolean flightBack);
    void buildhotelReservation(boolean hotelReservation);
    void buildinsurance(boolean insurance);
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
    public void buildtransferFromAirport(boolean transferFromAirport) {
        tour.setTransferFromAirport(transferFromAirport);
    }

    @Override
    public void buildflightTo(boolean flightTo) {
        tour.setFlightTo(flightTo);
    }

    @Override
    public void buildflightBack(boolean flightBack) {
        tour.setFlightBack(flightBack);
    }

    @Override
    public void buildhotelReservation(boolean hotelReservation) {
        tour.setHotelReservation(hotelReservation);
    }

    @Override
    public void buildinsurance(boolean insurance) {
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
        tourBuilder.buildtransferFromAirport(true);
        tourBuilder.buildflightTo(true);
        tourBuilder.buildflightBack(true);
        tourBuilder.buildhotelReservation(false);
        tourBuilder.buildinsurance(true);
        return tourBuilder.getTour();
    }
}