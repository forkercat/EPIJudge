package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class BuyAndSellStock {
  @EpiTest(testDataFile = "buy_and_sell_stock.tsv")

  /**
   * Brute-Force
   * Time: O(N^2)
   * Space: O(1)
   */
  /*
  public static double computeMaxProfit(List<Double> prices) {
    double maxProfit = Double.MIN_VALUE;

    for (int i = 0; i < prices.size(); ++i) {
      for (int j = i; j < prices.size(); ++j) {
        double profit = prices.get(j) - prices.get(i);
        if (profit > maxProfit) {
          maxProfit = profit;
        }
      }
    }

    return maxProfit;
  }
   */

  /**
   * Greedy
   * Time: O(N)
   * Space: O(1)
   */
  public static double computeMaxProfit(List<Double> prices) {
    double maxProfit = 0.0;  // cannot init with Double.MIN_VALUE
    double minPrice = Double.MAX_VALUE;
    for (double price : prices) {
      maxProfit = Math.max(maxProfit, price - minPrice);
      minPrice = Math.min(minPrice, price);
    }
    return maxProfit;
  }


  /**
   * Divide-and-Conquer
   * Time: O(N)
   * Space: O(logN)
   */
  /*
  public static double computeMaxProfit(List<Double> prices) {
    BuySell result = maxProfit(prices, 0, prices.size() - 1);
    return prices.get(result.sellTime) - prices.get(result.buyTime);
  }

  private static BuySell maxProfit(List<Double> prices, int lo, int hi) {
    if (lo >= hi) { // base case: only 1 time left
      return new BuySell(lo, lo, lo, lo);
    }
    // divide
    int mid = lo + (hi - lo) / 2;
    // conquer
    BuySell left = maxProfit(prices, lo, mid);
    BuySell right = maxProfit(prices, mid + 1, hi);
    // combine
    return combine(prices, left, right);

  }

  private static BuySell combine(List<Double> prices, BuySell left, BuySell right) {
    // case 1 (left.buyTime, left.sellTime)
    double bestProfit = prices.get(left.sellTime) - prices.get(left.buyTime);
    int bestBuyTime = left.buyTime; // set case 1 as default
    int bestSellTime = left.sellTime;

    // case 2 (right.buyTime, right.sellTime)
    double case2Profit = prices.get(right.sellTime) - prices.get(right.buyTime);
    if (case2Profit > bestProfit) { // compare
      bestProfit = case2Profit;
      bestBuyTime = right.buyTime;
      bestSellTime = right.sellTime;
    }

    // case 3 (left.minTime, right.maxTime)
    double case3Profit = prices.get(right.maxTime) - prices.get(left.minTime);
    if (case3Profit > bestProfit) { // compare
      bestProfit = case3Profit;
      bestBuyTime = left.minTime;
      bestSellTime = right.maxTime;
    }

    // update min and max
    int newMinTime = (prices.get(left.minTime) < prices.get(right.minTime)) ? left.minTime : right.minTime;
    int newMaxTime = (prices.get(left.maxTime) > prices.get(right.maxTime)) ? left.maxTime : right.maxTime;

    return new BuySell(bestBuyTime, bestSellTime, newMinTime, newMaxTime);
  }
   */

  /**
   * BuySell (helper class)
   */
  private static class BuySell {
    int buyTime; int sellTime;
    int minTime; int maxTime;

    BuySell(int b, int s, int min, int max) {
      buyTime = b; sellTime = s; minTime = min; maxTime = max;
    }
  }



  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "BuyAndSellStock.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
