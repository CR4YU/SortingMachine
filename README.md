# SortingMachine
Multi module Java application for visualizing time complexity of sorting algorithms on line chart.

## Module description
* **generator** - generates random data for given array size, returned type is some chosen implementation of `Comparable[]`
* **sorting** - contains services for sorting data with chosen sorting algorithm
* **statistics** - using above modules generates average sorting times for given algorithms and parameters, also provides single regression
* **ui** - JavaFX application that visualises statistics on line chart

## Prerequisites
In order to run JavaFX application you need:
* Installed **JDK 11**
* Installed **Maven3**
* `JAVA_HOME` setup properly

## How to run
Inside root folder execute:
```shell
./run.sh
```

## Screenshots
![Alt text](images/Screenshot1.png?raw=true "Screenshot - with regression")
![Alt text](images/Screenshot2.png?raw=true "Screenshot - without regression")

## Important notes
* Benchmarking was done using `System.nanoTime()` function. Please keep in mind it's not the perfect way to do such measures.
* `ForkJoinMergeSort` uses multi-threading hence it's performance may vary on different CPU's.
* Regression may not always work as expected when data are not evenly distributed