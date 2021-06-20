package tests;

import com.gridnine.testing.Filter;
import com.gridnine.testing.Flight;
import com.gridnine.testing.FlightBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

class CreateFlightsTest {
    @Test
    void createFlights() {
        List<Flight> flightList = FlightBuilder.createFlights();
        Filter filter = new Filter(flightList);

        Assertions.assertAll(
                () -> Assertions.assertFalse(flightList.isEmpty(), "The flight list is empty"),
                () -> Assertions.assertTrue(flightList.stream()
                                .allMatch(flight -> flight.getSegments().stream()
                                        .allMatch(segment -> segment.getArrivalDate().isAfter(segment.getDepartureDate()))),
                        "Arrival date later than departure date"),
                () -> Assertions.assertTrue(flightList.stream()
                        .allMatch(flight -> flight.getSegments().stream()
                                .allMatch(segment -> segment.getDepartureDate().isAfter(LocalDateTime.now())))
                        , "Departure until the current time"),
                () -> Assertions.assertEquals(filter.lessHrsWait(2).getFlightList().size(), flightList.size()
                        , "Eliminate flights with more than two hours of waiting on the ground")

        );
    }

}
