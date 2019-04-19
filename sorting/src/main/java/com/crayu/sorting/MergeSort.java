package com.crayu.sorting;

public class MergeSort implements Sorter {

    Comparable[] array;

    public MergeSort() {
    }

    @Override
    public void sort(Comparable[] array) {
        this.array = array;
        mergeSort(0, array.length - 1);
    }

    private void mergeSort(int start, int end) {
        if (start < end) {
            int mid = start + (end - start) / 2;
            mergeSort(start, mid);
            mergeSort(mid + 1, end);
            SortUtils.merge(array,start, mid, end);
        }
    }
}
