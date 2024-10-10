# Stock Sorting and Selection System

This project focuses on sorting algorithms and selection methods for stock data. The project evaluates the performance of various sorting algorithms and includes a solution to maximize profit by selecting items under specific constraints, such as weight limits.

## Project Structure

- **Sort.java**: Implements multiple sorting algorithms, including quick sort, merge sort, heap sort, and bubble sort. The class compares these algorithms' performance using subsets of the stock data.
- **SortingComparison.java**: Conducts multiple sorting tests with different data sizes (10 to 1000) to evaluate and compare the performance of each sorting algorithm in terms of time and space complexity.
- **Search.java**: Implements search functions for retrieving stock data based on certain attributes.
- **DefensiveProgram.java**: Ensures that the program handles errors gracefully, checking for edge cases and validating inputs.
- **SelectStocks.java**: Implements a greedy algorithm to select the most profitable stocks that fit within a given weight limit.
- **ReadStockData.java**: Reads stock data from a CSV file and provides utility functions for accessing and manipulating the stock information.
- **Stock.csv**: The dataset used for sorting and selection, containing stock information including weight and profit.

## Key Features

### Sorting Algorithms
- **Quick Sort**: Efficiently sorts the stock data with an average time complexity of O(n log n). This is the preferred sorting method for large datasets, such as the 10,000 entries in the stock CSV file.
- **Merge Sort**: A stable sorting algorithm with O(n log n) time complexity but requires additional memory for merging subarrays.
- **Heap Sort**: Performs well for smaller datasets but is less efficient than quick sort as the dataset grows.
- **Bubble Sort**: A simple O(n²) algorithm used for comparison due to its poor performance on larger datasets.

### Profit Maximization
The `SelectStocks.java` class solves the knapsack problem using a greedy algorithm:
- **Problem**: Given a truck's weight limit, choose stocks to maximize profit without exceeding the weight capacity.
- **Solution**: Sorts the stocks by their profit-to-weight ratio and selects items until the weight limit is reached, ensuring maximum profit.

## Performance Analysis
- Quick sort consistently outperforms other algorithms, particularly as the dataset size increases.
- Merge sort performs slightly worse than quick sort but still remains efficient.
- Heap sort is effective for smaller datasets but becomes slower with larger data.
- Bubble sort is significantly slower and is included only for comparison.

## How to Run

1. **Clone the Repository**:
    ```bash
    git clone https://github.com/YOUR_USERNAME/Stock-Sorting-Selection-System.git
    ```
2. **Navigate to the Directory**:
    ```bash
    cd Stock-Sorting-Selection-System
    ```
3. **Compile and Run**:
    ```bash
    javac Sort.java SelectStocks.java ReadStockData.java
    java Sort
    ```

## Example Output
Sorting 10 data entries using Quick Sort: Time taken: 5 ms

Sorting 1000 data entries using Heap Sort: Time taken: 150 ms

Selecting stocks under weight limit of 1000: Total profit: $12345

## Environment

- **Compiler**: IntelliJ IDEA
- **JDK**: OpenJDK 17
- **Operating System**: Linux/Windows/MacOS

## License

This project is licensed under the MIT License.
