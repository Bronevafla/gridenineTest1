package com.gridnine.testing;

import java.util.List;
import java.util.logging.Logger;

public class Main {
    private static Logger log = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        Filter filter = new Filter(FlightBuilder.createFlights());

        log.info("Original flight list: " + filter);
        filter.departNotInPast().lessTwoHrsWait().arrNotEarlierDept();
//        log.info("Filtered flight list: " + filter.departNotInPast().lessTwoHrsWait().arrNotEarlierDept());
    }
}
