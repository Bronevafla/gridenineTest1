package com.gridnine.testing;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class containing filtering methods
 */
public class Filter {
    private List<Flight> flightList;

    public Filter(List<Flight> flightList) {
        this.flightList = flightList;
    }

    /**
     * Exclude departures up to the current point in time
     */
    public Filter departNotInPast() {
        Filter sortFilter = new Filter(flightList.stream()
                .filter(flight -> flight.getSegments().stream()
                        .allMatch(segment -> segment.getDepartureDate().isAfter(LocalDateTime.now())))
                .collect(Collectors.toList()));

        System.out.println(sortFilter.getFlightList());
        return sortFilter;
    }

    /**
     * Exclude flights with segments with an arrival date earlier than the departure date
     */
    public Filter arrNotEarlierDept() {
        Filter sortFilter = new Filter(flightList.stream()
                .filter(flight -> flight.getSegments().stream()
                        .allMatch(segment -> segment.getArrivalDate().isAfter(segment.getDepartureDate())))
                .collect(Collectors.toList()));

        System.out.println(sortFilter.getFlightList());
        return sortFilter;
    }

    /**
     * Eliminate flights with more than two hours of waiting on the ground
     */
    public Filter lessTwoHrsWait() {
        List<Flight> trueList = new ArrayList<>();
        for (Flight flight : flightList) {
            int count = 0;
            if (flight.getSegments().size() > 1) {
                for (int i = 0; i < flight.getSegments().size() - 1; i++) {
                    if (flight.getSegments().get(i).getArrivalDate().plusHours(2).isAfter(flight.getSegments().get(i + 1).getDepartureDate())) {
                        count++;
                    }
                    if (count == flight.getSegments().size() - 1) {
                        trueList.add(flight);
                    }
                }
            } else {
                trueList.add(flight);
            }
        }

        Filter sortFilter = new Filter(trueList);
        System.out.println(sortFilter.getFlightList());

        return sortFilter;
    }

    public List<Flight> getFlightList() {
        return flightList;
    }
}
