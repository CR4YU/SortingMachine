package com.crayu.sorting;

@SuppressWarnings("unchecked")
class BubbleSort implements Sorter {

    BubbleSort() {
    }

    @Override
    public void sort(Comparable[] array) {
        int n = array.length;
        for (int i = 0; i < n-1; i++)
            for (int j = 0; j < n-i-1; j++)
                if (array[j].compareTo(array[j+1]) > 0)
                    SortUtils.swap(array, j, j+1);
    }

}
