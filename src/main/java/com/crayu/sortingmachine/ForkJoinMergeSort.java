package com.crayu.sortingmachine;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class ForkJoinMergeSort extends RecursiveAction implements SortingAlgorithm {

    private Comparable[] array;
    private int start;
    private int end;
    private int mid;

    public ForkJoinMergeSort() {
    }

    private  ForkJoinMergeSort(Comparable[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    public void sort(Comparable[] array) {
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinMergeSort sort = new ForkJoinMergeSort(array, 0, array.length - 1);
        pool.invoke(sort);
    }

    @Override
    protected void compute() {
        mergeSort();
    }

    private void merge() {
        Comparable[] temp = new Comparable[end - start + 1];

        int x = start;
        int y = mid + 1;
        int z = 0;

        while(x <= mid && y <= end) {
            temp[z++] = (array[x].compareTo(array[y]) < 1)
                    ? array[x++]
                    : array[y++];
        }
        while(x <= mid) temp[z++] = array[x++];
        while(y <= end) temp[z++] = array[y++];
        for(z = 0; z < temp.length; z++) {
            array[start + z] = temp[z];
        }
    }


    private void mergeSort() {
        if(start < end) {
            mid = start + (end - start) / 2;
            ForkJoinMergeSort left = new ForkJoinMergeSort(array, start, mid);
            ForkJoinMergeSort right = new ForkJoinMergeSort(array, mid + 1, end);
            invokeAll(left, right);
            merge();
        }
    }

    @Override
    public String name() {
        return "Fork Join Merge Sort";
    }
}
