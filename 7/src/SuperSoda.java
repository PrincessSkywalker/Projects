import java.util.Arrays;
import java.util.HashMap;

public class SuperSoda {

  public static double minimalSodaCost(int[] sodaSizes, double[] costs, int n) {
    if(n == 0)
      return 0;
    if(n < 0)
      return -1;

    double[] memo = new double[n];
    
    for(int i = 0; i < n; i++){
      memo[i] = minimalSodaCostHelper(sodaSizes, costs, i, memo);
    }

    return minimalSodaCostHelper(sodaSizes, costs, n, memo);
  }

  private static double minimalSodaCostHelper(int[] sodaSizes, double[] costs, int n, double[] memo) {
    if(n==0){
      return 0;
    }
    double smallest = Double.POSITIVE_INFINITY;

    for(int s = 0; s<sodaSizes.length; s++){
      if(n-sodaSizes[s]>=0){
        double x = costs[s] + memo[n-sodaSizes[s]];

        if(x< smallest){
          smallest = x;
        }
      }
    }
    return smallest;
  }

  public static int maximumSodaNumber(int[] sodaSizes, double[] costs, double cost) {
    HashMap<Double, Integer> memo = new HashMap<>();
    return maximumSodaNumberHelper(sodaSizes, costs, cost, memo);
  }

  private static int maximumSodaNumberHelper(int[] sodaSizes, double[] costs, double cost, HashMap<Double, Integer> memo) {
    if(cost<=0){
      return 0;
    }
    if(memo.containsKey(cost)){
      return memo.get(cost);
    }
    int maximum = 0;

    for(int s = 0; s<sodaSizes.length; s++){
      if(cost-costs[s] >= 0){
        int x = sodaSizes[s] + maximumSodaNumberHelper(sodaSizes, costs, cost-costs[s], memo);

        if(x>maximum){
          maximum = x;
        }
      }
    }

    memo.put(cost, maximum);
    return maximum;
  }

  public static int[] minimalSodaCostCombinations(int[] sodaSizes, double[] costs, int n) {
    if(n == 0)
      return new int[sodaSizes.length];
    if(n < 0)
      return null;

    CostCombination[] memo = new CostCombination[n];

    for(int i = 0; i < n; i++){
      memo[i] = minimalSodaCostCombinationHelper(sodaSizes, costs, i, memo);
    }

    return minimalSodaCostCombinationHelper(sodaSizes, costs, n, memo).purchases_;
  }

  private static CostCombination minimalSodaCostCombinationHelper(int[] sodaSizes, double[] costs, int n, CostCombination[] memo) {
    if(n==0){
      return new CostCombination(0, new int[sodaSizes.length]);
    }
    double smallest = Double.POSITIVE_INFINITY;
    int bestSize = 0;

    for(int s = 0; s<sodaSizes.length; s++){
      if(n-sodaSizes[s]>=0){
        double x = costs[s] + memo[n-sodaSizes[s]].cost_;

        if(x< smallest){
          smallest = x;
          bestSize = s;
        }
      }
    }
    int[] purchases = new int[sodaSizes.length];
    for(int i = 0; i < purchases.length; i++){
      purchases[i] = memo[n-sodaSizes[bestSize]].purchases_[i];
    }
    purchases[bestSize]++;
    return new CostCombination(smallest, purchases);
  }

  private static class CostCombination {
    private double cost_;
    private int[] purchases_;

    private CostCombination(double cost, int[] purchases){
      cost_ = cost;
      purchases_ = purchases;
    }
  }

  public static void main(String[] args) {
    int[] sodaSizes = new int[] { 1, 6, 12, 25, 36 };
    double[] costs = new double[] { 0.8, 4, 7.5, 14, 20 };
    System.out.println(minimalSodaCost(sodaSizes, costs, 100));
    System.out.println(maximumSodaNumber(sodaSizes, costs, 56.0));
    System.out.println(Arrays.toString(minimalSodaCostCombinations(sodaSizes, costs, 105)));
  }
}
