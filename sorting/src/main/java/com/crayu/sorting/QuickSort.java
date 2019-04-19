package com.crayu.sorting;

@SuppressWarnings("unchecked")
class QuickSort implements Sorter {

    QuickSort() {
    }

    @Override
    public void sort(Comparable[] array) {
        quickSort(array,0, array.length - 1);
    }

    private int partition(Comparable[] array, int left, int right) {
        int i = left;
        int j = right;
        Comparable pivot = array[left + (right - left) / 2];

        while (i <= j) {
            while (array[i].compareTo(pivot) < 0) i++;
            while (array[j].compareTo(pivot) > 0) j--;

            if (i <= j) {
                SortUtils.swap(array, i, j);
                i++;
                j--;
            }
        }
        return i;
    }

    private void quickSort(Comparable[] array, int left, int right) {
        int index = partition(array, left, right);
        if (left < index - 1)
            quickSort(array, left, index - 1);
        if (index < right)
            quickSort(array, index, right);
    }
}
