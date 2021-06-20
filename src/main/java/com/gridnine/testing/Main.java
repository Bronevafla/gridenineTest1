package com.gridnine.testing;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Flight> flightList = FlightBuilder.createFlights();
        Filter filter = new Filter(flightList);

        System.out.println(flightList);
        filter.departNotInPast().lessTwoHrsWait().arrNotEarlierDept();
    }
}
