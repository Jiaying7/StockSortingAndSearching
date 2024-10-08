import java.io.File;
import java.time.LocalDate;
import java.util.*;

public class ReadStockData {

	public static void main(String[] args) throws Exception{
		//parsing and reading the CSV file data into the film (object) array
		// provide the path here...
        File directory = new File("./");
  		String name = directory.getAbsolutePath() + "//Stock.csv";
		Scanner sc = new Scanner(new File(name));
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

		// We can print film details due to overridden toString method in film class
		System.out.println(stocks[0]);
		System.out.println(stocks[1]);

		// we can compare films based on their ID due to overridden CompareTo method in film class
		System.out.println(stocks[0]==stocks[0]);
		System.out.println(stocks[0]==stocks[1]);
	}

}


class Stock implements Comparable<Object>{
	private int stockNo;
	private float stockSize;
	private float profit;
	private String productType;
	private float weight;
	private String productName;

	// constructor
	public Stock(int stockNo, float stockSize, float profit, String productType, float weight, String productName) {
		super();
		this.stockNo=stockNo;
		this.stockSize = stockSize;
		this.profit = profit;
		this.productType= productType;
		this.weight =weight;
		this.productName=productName;
	}

	//这个构造器用于异常处理
	public Stock(int stockNo, float stockSize, float profit, String productType, float weight, String productName, String buyDate) throws Exception {
		super();
		this.stockNo=stockNo;
		this.stockSize = stockSize;
		this.profit = profit;
		this.productType= productType;
		this.weight =weight;
		this.productName=productName;

		// 检查时间是否有效
		LocalDate currentDate = LocalDate.now();
		LocalDate buyDateObj = LocalDate.parse(buyDate);
		LocalDate stockStartDate = LocalDate.parse("2015-12-01");

		if (buyDateObj.isAfter(currentDate)) {
			throw new Exception("Invalid input: buy_date is in the future.");
		}
		if (buyDateObj.isBefore(stockStartDate)) {
			throw new Exception("Invalid input: buy_date is before the stock start date.");
		}

		// 检查产品种类是否有效
		if (!productType.equals("clothing") && !productType.equals("furniture") && !productType.equals("food")) {
			throw new InvalidProductTypeException("Invalid input: product_type is not one of the allowed types.Please choose from 'clothing‘、’furniture‘ and ’food‘");
		}
	}

	//异常定义
	public static class InvalidProductTypeException extends Exception {
		public InvalidProductTypeException(String message) {
			super(message);
		}
	}

	// setters and getters
	public int getStockNo() {
		return stockNo;
	}

	public void setStockNo(int stockNo) {
		this.stockNo = stockNo;
	}

	public float getStockSize() {
		return stockSize;
	}

	public void setStockSize(float stockSize) {
		this.stockSize = stockSize;
	}

	public float getProfit() {
		return profit;
	}

	public void setProfit(float profit) {
		this.profit = profit;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}


	// so the film objects can be compared when sorting/searching
	// NOTE: this will only allow comparisons based on the title of the film
	@Override
	public int compareTo(Object obj) {
		/*
		Edit this section so it compares the appropriate
		column you wish to sort by
		*/

		Stock stk = (Stock)obj;
		return stockNo - (stk.getStockNo());
	}

	@Override
	public String toString() {
		return "Stock [StockNo=" + stockNo+ ", Stock size="+stockSize+", Profit="+ profit+ ", Product Type="+ productType + ", Weight=" + weight +
		", Product name=" + productName+ "]";
	}

}