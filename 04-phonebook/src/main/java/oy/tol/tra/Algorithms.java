
package oy.tol.tra;

import java.util.function.Predicate;

public class Algorithms {
    public static <T> void reverse(T [] array) {
        int i = 0;
        while (i < array.length/2) {
           T temp = array[i];
           array[i] = array[array.length-i-1];
           array[array.length-i-1] = temp;
           i++;
       }
    }
    public static <T extends Comparable<T>> void sort(T [] array) {
        int left = 0;  
    int right = array.length - 1;  
    while (left < right) {  
        T tmp = array[left];  
        array[left] = array[right];  
        array[right] = tmp;  
        left++;  
        right--;  
    }  
    }

    public static <E extends Comparable<E>> void fastSort(E [] array) {
        if (array == null || array.length <= 1){
            return;
        }
        quickSort(array,0,array.length-1);
    }
    public static <E extends Comparable<E>> void quickSort(E [] array, int begin, int end){
        if (begin < end){
            int q = partition(array,begin,end);
            quickSort(array,begin,q-1);
            quickSort(array,q+1,end);
        }
    }
    private static <E extends Comparable<E>> int partition(E [] array, int begin, int end) {
        int i = begin-1;
        for (int leftIndex = begin;leftIndex<end;leftIndex++){
            if (array[leftIndex].compareTo(array[end])<0){
                i++;
                swap(array,i,leftIndex);
            }
        }
        swap(array,i+1,end);
        return i+1;
    }
    public static <T> void swap(T[] array,int i,int j){
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static <T extends Comparable<T>> int binarySearch(T aValue, T [] fromArray, int fromIndex, int toIndex) {
        if (fromIndex > toIndex) {
            return -1;
        }

        int middle = (fromIndex+toIndex)/2;

        if (aValue.compareTo(fromArray[middle]) == 0) {
            return middle;
        }
        else if (aValue.compareTo(fromArray[middle]) < 0) {
            return binarySearch(aValue, fromArray, fromIndex, middle - 1);
        }
        else {
            return binarySearch(aValue, fromArray, middle + 1, toIndex);
        }

    }
    public static <T> int partitionByRule(T[] array, int count, Predicate<T> rule) {
        int index = 0;
        for (; index < count; index++) {
            if (rule.test(array[index])) {
                break;
            }
        }
        if (index >= count) {
            return count;
        }
        int nextIndex = index + 1;
        while (nextIndex != count) {
            if (!rule.test(array[nextIndex])) {
                swap(array, index, nextIndex);
                index++;
            }
            nextIndex++;
        }
        return index;
    }



}
