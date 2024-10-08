import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 Sort类包含归并排序、快速排序和冒泡排序三种排序算法的实现。
 该类还包括读取未排序数据的方法和main方法，用于测试三种排序算法的效率。
 */
public class Sort {

    /**
     主方法，用于测试三种排序算法的效率。
     首先读取未排序的数据，然后对数据进行排序。
     最后输出排序后的结果。
     测试时取消注释相应方法的注释
     */
    public static void main(String[] args) {
        // 读入未排序的数据
        Stock[] stocks = readStockData();

        //经过测试快速排序相对平均速度最快
        //快速排序
        QuickSort.quickSort(stocks, 0, stocks.length - 1,"Part1");

        //归并排序
        //MergeSort.mergeSort(stocks,0,stocks.length-1);

        //冒泡排序
        //BubbleSort.sort(stocks);

        //堆排序
        //HeapSort.heapSort(stocks);

        // 输出排序后的结果
        for (Stock stock : stocks) {
            System.out.println(stock);
        }
    }

    // 读取未排序的数据
    public static Stock[] readStockData() {
        //parsing and reading the CSV file data into the film (object) array
        // provide the path here...
        File directory = new File("./");
        String name = directory.getAbsolutePath() + "//Stock.csv";
        Scanner sc = null;
        try {
            sc = new Scanner(new File(name));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Stock[] stocks= new Stock[10000];

        // this will just print the header in CSV file
        sc.nextLine();

        int i = 0; String st = "";

        while (sc.hasNextLine())  //returns a boolean value
        {
            st = sc.nextLine();
            String[] data = st.split(",");
            stocks[i++] = new Stock(Integer.parseInt(data[0]),  Float.parseFloat(data[1]),  Float.parseFloat(data[2]),  data[3], Float.parseFloat(data[4]),data[5]);
        }
        sc.close();  //closes the scanner

        return stocks;
    }

    public static class MergeSort {
        //归并排序
        public static void mergeSort(Stock[] stocks, int low, int high) {
            if (low < high) {
                int mid = (low + high) / 2;
                mergeSort(stocks, low, mid);
                mergeSort(stocks, mid + 1, high);
                merge(stocks, low, mid, high);
            }
        }

        public static void merge(Stock[] stocks, int low, int mid, int high) {
            int n1 = mid - low + 1;
            int n2 = high - mid;

            Stock[] leftArr = new Stock[n1];
            Stock[] rightArr = new Stock[n2];

            for (int i = 0; i < n1; i++) {
                leftArr[i] = stocks[low + i];
            }

            for (int j = 0; j < n2; j++) {
                rightArr[j] = stocks[mid + 1 + j];
            }

            int i = 0, j = 0, k = low;
            while (i < n1 && j < n2) {
                if (leftArr[i].getStockSize() < rightArr[j].getStockSize()) {
                    stocks[k] = leftArr[i];
                    i++;
                } else if (leftArr[i].getStockSize() == rightArr[j].getStockSize()) {
                    if (leftArr[i].getStockNo() < rightArr[j].getStockNo()) {
                        stocks[k] = leftArr[i];
                        i++;
                    } else {
                        stocks[k] = rightArr[j];
                        j++;
                    }
                } else {
                    stocks[k] = rightArr[j];
                    j++;
                }
                k++;
            }

            while (i < n1) {
                stocks[k] = leftArr[i];
                i++;
                k++;
            }

            while (j < n2) {
                stocks[k] = rightArr[j];
                j++;
                k++;
            }
        }
    }

        public static class QuickSort {
            // 快速排序算法实现
            public static void quickSort(Stock[] stocks, int low, int high,String role) {
                if (low < high) {
                    // 找到基准位置
                    int pivotPos;
                    if(role.equals("根据利润比排序")) {
                        pivotPos = partition(stocks, low, high,role);
                    }else{
                        pivotPos = partition(stocks, low, high);
                    }
                    // 对左右两部分递归调用快排算法
                    quickSort(stocks, low, pivotPos - 1,role);
                    quickSort(stocks, pivotPos + 1, high,role);
                }
            }

            // 找到基准位置
            public static int partition(Stock[] stocks, int low, int high) {
                // 选取最右边的元素作为基准
                Stock pivot = stocks[high];
                int i = low - 1;
                for (int j = low; j <= high - 1; j++) {
                    // 如果当前元素比基准元素小，则将其交换到左侧
                    if (stocks[j].getStockSize() < pivot.getStockSize() ||
                            (stocks[j].getStockSize() == pivot.getStockSize() && stocks[j].getStockNo() < pivot.getStockNo())) {
                        i++;
                        swap(stocks, i, j);
                    }
                }
                // 将基准元素交换到正确的位置上
                swap(stocks, i + 1, high);
                // 返回基准位置
                return i + 1;
            }

            // 用于Part3选择最大利润
            // 找到基准位置
            public static int partition(Stock[] stocks, int low, int high,String role) {
                // 选取最右边的元素作为基准
                Stock pivot = stocks[high];
                int i = low - 1;
                for (int j = low; j <= high - 1; j++) {
                    // 如果当前元素的利润比比基准元素的利润比大，则将其交换到左侧
                    float ratio1 = stocks[j].getProfit() / stocks[j].getWeight();
                    float ratio2 = pivot.getProfit() / pivot.getWeight();
                    if (ratio1 > ratio2 ||
                            (ratio1 == ratio2 && stocks[j].getStockNo() < pivot.getStockNo())) {
                        i++;
                        swap(stocks, i, j);
                    }
                }
                // 将基准元素交换到正确的位置上
                swap(stocks, i + 1, high);
                // 返回基准位置
                return i + 1;
            }

            // 交换数组中的两个元素
            public static void swap(Stock[] stocks, int i, int j) {
                Stock temp = stocks[i];
                stocks[i] = stocks[j];
                stocks[j] = temp;
            }
        }

        public static class BubbleSort {
            public static void sort(Stock[] arr) {
                int n = arr.length;

                // 使用冒泡排序
                for (int i = 0; i < n - 1; i++) {
                    for (int j = 0; j < n - i - 1; j++) {
                        // 如果第2列不同，按第2列排序
                        if (arr[j].getStockSize() > arr[j + 1].getStockSize()) {
                            Stock temp = arr[j];
                            arr[j] = arr[j + 1];
                            arr[j + 1] = temp;
                        }
                        // 如果第2列相同，按第1列排序
                        else if (arr[j].getStockSize() == arr[j + 1].getStockSize() && arr[j].getStockNo() > arr[j + 1].getStockNo()) {
                            Stock temp = arr[j];
                            arr[j] = arr[j + 1];
                            arr[j + 1] = temp;
                        }
                    }
                }
            }
        }
        //堆排序
        public static class HeapSort {
            public static void heapSort(Stock[] arr) {
                int n = arr.length;

                // 从第一个非叶子节点开始建立大根堆
                for (int i = n / 2 - 1; i >= 0; i--) {
                    heapify(arr, n, i);
                }

                // 依次取出堆顶元素放到数组末尾，并维护大根堆
                for (int i = n - 1; i > 0; i--) {
                    swap(arr, 0, i);
                    heapify(arr, i, 0);
                }
            }

            // 维护大根堆
            public static void heapify(Stock[] arr, int n, int i) {
                int largest = i;
                int l = 2 * i + 1;
                int r = 2 * i + 2;

                if (l < n && (arr[l].getStockSize() > arr[largest].getStockSize() || (arr[l].getStockSize() == arr[largest].getStockSize() && arr[l].getStockNo() > arr[largest].getStockNo()))) {
                    largest = l;
                }

                if (r < n && (arr[r].getStockSize() > arr[largest].getStockSize() || (arr[r].getStockSize() == arr[largest].getStockSize() && arr[r].getStockNo() > arr[largest].getStockNo()))) {
                    largest = r;
                }

                if (largest != i) {
                    swap(arr, i, largest);
                    heapify(arr, n, largest);
                }
            }

            // 交换数组中的两个元素
            public static void swap(Stock[] stocks, int i, int j) {
                Stock temp = stocks[i];
                stocks[i] = stocks[j];
                stocks[j] = temp;
            }
        }

}
