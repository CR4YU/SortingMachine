package com.crayu.sorting;

@SuppressWarnings("unchecked")
class SortUtils {

    static void swap(Comparable[] array, int i, int j) {
        Comparable temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    static void merge(Comparable[] array, int start, int mid, int end) {
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
}
