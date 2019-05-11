package com.crayu.sorting;

public class SortingServices {

    public static BasicSortingService basicSortingService() {
        return new BasicSortingServiceImpl();
    }

    public static TimedSortingService timedSortingService() {
        return new TimedSortingServiceImpl();
    }
}
