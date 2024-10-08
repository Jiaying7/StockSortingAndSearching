import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 该类用于比较不同排序算法的性能
 */
public class SortingComparison {
    public static void main(String[] args) {
        int[] sizes = {10, 100, 1000, 5000, 10000};
        long[] quickSortTimes = new long[sizes.length];
        long[] mergeSortTimes = new long[sizes.length];
        long[] bubbleSortTimes = new long[sizes.length];
        long[] heapSortTimes = new long[sizes.length];

        // 读取数据集中的10000条记录
        Stock[] allStocks = readStockData();

        for (int i = 0; i < sizes.length; i++) {
            // 从数据集中随机抽取需要排序的记录
            Stock[] stocks = Arrays.copyOfRange(allStocks, 0, sizes[i]);

            long startTime = System.nanoTime();
            Sort.QuickSort.quickSort(stocks, 0, stocks.length - 1,"Part1");
            long endTime = System.nanoTime();
            quickSortTimes[i] = (endTime - startTime);

            stocks = Arrays.copyOfRange(allStocks, 0, sizes[i]);
            startTime = System.nanoTime();
            Sort.MergeSort.mergeSort(stocks, 0, stocks.length - 1);
            endTime = System.nanoTime();
            mergeSortTimes[i] = (endTime - startTime);

            stocks = Arrays.copyOfRange(allStocks, 0, sizes[i]);
            startTime = System.nanoTime();
            Sort.BubbleSort.sort(stocks);
            endTime = System.nanoTime();
            bubbleSortTimes[i] = (endTime - startTime);

            stocks = Arrays.copyOfRange(allStocks, 0, sizes[i]);
            startTime = System.nanoTime();
            Sort.HeapSort.heapSort(stocks);
            endTime = System.nanoTime();
            heapSortTimes[i] = (endTime - startTime);
        }

        System.out.println("Sorting Algorithm Comparison:");
        System.out.println("----------------------------");
        System.out.println("Number of Records | QuickSort | MergeSort | BubbleSort | HeapSort");
        System.out.println("----------------------------------------------------------------");
        for (int i = 0; i < sizes.length; i++) {
            System.out.printf("%-17d| %-10dus| %-10dus| %-10dus| %-10dus\n", sizes[i], quickSortTimes[i] / 1000, mergeSortTimes[i] / 1000, bubbleSortTimes[i] / 1000, heapSortTimes[i] / 1000);
        }
        System.out.println("-------------------------------------------------------------");
        System.out.println("Average           | " + getAverage(quickSortTimes) / 1000 + "us| " + getAverage(mergeSortTimes) / 1000 + "us| " + getAverage(bubbleSortTimes) / 1000 + "us| " + getAverage(heapSortTimes) / 1000 + "us");
    }


    // 读取数据集中的所有记录
    public static Stock[] readStockData() {
        // parsing and reading the CSV file data into the Stock object array
        // provide the path here...
        File directory = new File("./");
        String name = directory.getAbsolutePath() + "//Stock.csv";
        Scanner sc = null;
        try {
            sc = new Scanner(new File(name));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Stock[] stocks = new Stock[10000];

        // this will just print the header in CSV file
        sc.nextLine();

        int i = 0;
        String st = "";

        while (sc.hasNextLine()) {
            st = sc.nextLine();
            String[] data = st.split(",");
            stocks[i++] = new Stock(Integer.parseInt(data[0]), Float.parseFloat(data[1]), Float.parseFloat(data[2]), data[3], Float.parseFloat(data[4]), data[5]);
        }
        sc.close();  // closes the scanner

        return stocks;
    }

    public static double getAverage(long[] arr) {
        long sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        return (double) sum / arr.length;
    }




}