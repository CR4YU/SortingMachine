package com.crayu.sorting;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

class ForkJoinMergeSort extends RecursiveAction implements Sorter {

    private Comparable[] array;
    private int start;
    private int end;

    ForkJoinMergeSort() {
    }

    private ForkJoinMergeSort(Comparable[] array, int start, int end) {
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

    private void mergeSort() {
        if(start < end) {
            int mid = start + (end - start) / 2;
            ForkJoinMergeSort left = new ForkJoinMergeSort(array, start, mid);
            ForkJoinMergeSort right = new ForkJoinMergeSort(array, mid + 1, end);
            invokeAll(left, right);
            SortUtils.merge(array, start, mid, end);
        }
    }

}
