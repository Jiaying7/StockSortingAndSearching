import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 Search类用于在Stock数据集中查找记录。
 */
public class Search {
    /**
     * main方法读取数据集，并输入需要搜索的列名称和搜索的名称进行搜索。
     * @param args 命令行参数，未使用。
     */
    public static void main(String[] args) {
        // 读入数据集中stocks对象
        Stock[] stocks = readStockData();

        //输入需要搜索的列名称("StockNo"，ProductType"，"ProductName")
        String columnName = "ProductName";
        //输入需要搜索的名称(对于“ProductName”,例如“noodles”，“rice”，“dinner table”)
        String query = "sauce";
        searchStocks(stocks, columnName, query);
    }

    public static void searchStocks(Stock[] stocks, String columnName, String query) {
        boolean found = false;
        List<Stock> matchingStocks = new ArrayList<>();

        for (Stock stock : stocks) {
            if (stock != null) {
                switch (columnName) {
                    case "StockNo":
                        if (Integer.toString(stock.getStockNo()).equals(query)) {
                            matchingStocks.add(stock);
                            found = true;
                        }
                        break;
                    case "ProductType":
                        if (stock.getProductType().equals(query)) {
                            matchingStocks.add(stock);
                            found = true;
                        }
                        break;
                    case "ProductName":
                        if (stock.getProductName().equals(query)) {
                            matchingStocks.add(stock);
                            found = true;
                        }
                        break;
                    default:
                        System.out.println("Invalid column name. Please provide a valid column name.");
                        return;
                }
            }
        }

        if (!found) {
            System.out.println("Not an existing " + columnName + "!");
        } else {
            for (Stock matchingStock : matchingStocks) {
                System.out.println(matchingStock.toString());
            }
        }
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

}
