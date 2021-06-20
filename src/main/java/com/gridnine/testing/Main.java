package com.gridnine.testing;

import java.util.logging.Logger;

public class Main {
    private static Logger log = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        Filter filter = new Filter(FlightBuilder.createFlights());

        log.info("Original flight list:\n" + filter.getFlightList() + "\n");
        filter.departNotInPast().lessHrsWait(2).arrNotEarlierDept();
    }
}
