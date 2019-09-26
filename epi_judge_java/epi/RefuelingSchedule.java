package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;
import java.util.List;
public class RefuelingSchedule {
  private static final int MPG = 20;

  /**
   * Brute-force
   * Time: O(N^2)
   */
  // gallons[i] is the amount of gas in city i, and distances[i] is the distance
  // city i to the next city.
  public static int findAmpleCity(List<Integer> gallons,
                                  List<Integer> distances) {
    int n = gallons.size(); // #city
    // for each city i
    for (int i = 0; i < n; ++i) {
      boolean isAmple = true;
      int gallonSum = gallons.get(i);
      // check n cities including itself
      for (int j = 0; j < n; ++j) { // n
        int currCity = (i + j) % n; // caveat: the distance lies in the current city
        int nextCity = (currCity + 1) % n;
        gallonSum -= distances.get(currCity) / MPG;
        if (gallonSum < 0) { // negative gas
          isAmple = false;
          break;
        }
        // reachable --> refuel!
        gallonSum += gallons.get(nextCity);
      }
      if (isAmple) return i;
    }
    return -1; // no ample city
  }




  @EpiTest(testDataFile = "refueling_schedule.tsv")
  public static void findAmpleCityWrapper(TimedExecutor executor,
                                          List<Integer> gallons,
                                          List<Integer> distances)
      throws Exception {
    int result = executor.run(() -> findAmpleCity(gallons, distances));
    final int numCities = gallons.size();
    int tank = 0;
    for (int i = 0; i < numCities; ++i) {
      int city = (result + i) % numCities;
      tank += gallons.get(city) * MPG - distances.get(city);
      if (tank < 0) {
        throw new TestFailure(String.format("Out of gas on city %d", city));
      }
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "RefuelingSchedule.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
