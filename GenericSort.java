import java.util.Arrays;
import java.util.Random;

public class GenericSort {

    public static void main(String[] args) {
        int size = 1000;
        if (args.length > 0) {
            size = Integer.parseInt(args[0]);
        }

        Integer[] data1 = generateRandomArray(size);
        Integer[] data2 = Arrays.copyOf(data1, data1.length);

        System.out.println("Array Size: " + size);

        long start = System.nanoTime();
        bubbleSort(data1);
        long end = System.nanoTime();
        System.out.printf("Bubble Sort Time: %.4f ms%n", (end - start) / 1_000_000.0);

        start = System.nanoTime();
        mergeSort(data2, 0, data2.length - 1);
        end = System.nanoTime();
        System.out.printf("Merge Sort Time: %.4f ms%n", (end - start) / 1_000_000.0);
    }

    public static <T extends Comparable<T>> void bubbleSort(T[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    T temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static <T extends Comparable<T>> void mergeSort(T[] arr, int left, int right) {
        if (left < right) {
            int middle = (left + right) / 2;
            mergeSort(arr, left, middle);
            mergeSort(arr, middle + 1, right);
            merge(arr, left, middle, right);
        }
    }

    public static <T extends Comparable<T>> void merge(T[] arr, int left, int middle, int right) {
        int n1 = middle - left + 1;
        int n2 = right - middle;

        T[] L = Arrays.copyOfRange(arr, left, middle + 1);
        T[] R = Arrays.copyOfRange(arr, middle + 1, right + 1);

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {
            if (L[i].compareTo(R[j]) <= 0) {
                arr[k++] = L[i++];
            } else {
                arr[k++] = R[j++];
            }
        }

        while (i < n1) arr[k++] = L[i++];
        while (j < n2) arr[k++] = R[j++];
    }

    public static Integer[] generateRandomArray(int size) {
        Random rand = new Random();
        Integer[] arr = new Integer[size];
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(10000);
        }
        return arr;
    }
}
