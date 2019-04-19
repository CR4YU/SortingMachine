package com.crayu.sorting;

@SuppressWarnings("unchecked")
class InsertionSort implements Sorter {

    InsertionSort() {
    }

    @Override
    public void sort(Comparable[] array) {
        insertionSort(array);
    }

    private void insertionSort(Comparable[] array) {
        int n = array.length;
        for (int i = 1; i < n; ++i) {
            Comparable key = array[i];
            int j = i-1;

            while (j >= 0 && array[j].compareTo(key) > 0) {
                array[j+1] = array[j];
                j--;
            }
            array[j+1] = key;
        }
    }

}
