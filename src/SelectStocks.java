import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SelectStocks {
    public static void main(String[] args) throws Exception{
        // 读入数据
        Stock[] stocks = readStockData();
        float truckWeightLimit = 20.0f; // 假设卡车重量为20.0
        selectStocks(truckWeightLimit, stocks);
    }

    /**
     * 根据卡车重量限制选择货物
     * @param truckWeightLimit 卡车重量限制
     * @param stocks 货物列表
     */
    public static void selectStocks(float truckWeightLimit, Stock[] stocks) {
        // 按照利润比排序
        Sort.QuickSort.quickSort(stocks, 0, stocks.length - 1,"根据利润比排序");

        // 选择货物，直到超出卡车重量限制
        float currentWeight = 0;
        float totalProfit = 0;
        for (Stock s : stocks) {
            if (currentWeight + s.getWeight() <= truckWeightLimit) {
                currentWeight += s.getWeight();
                totalProfit += s.getProfit();
            } else {
                break;
            }
        }

        // 输出所选货物和总利润
        System.out.println("Selected stocks:");
        for (Stock s : stocks) {
            if (s.getProfit() > 0 && s.getWeight() > 0 && currentWeight + s.getWeight() <= truckWeightLimit) {
                System.out.println(s);
            }
        }
        System.out.println("Total profit: " + totalProfit);
    }

    // 读取数据
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

}
