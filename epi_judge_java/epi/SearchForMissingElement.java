package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;

import java.util.List;

public class SearchForMissingElement {
  @EpiUserType(ctorParams = {Integer.class, Integer.class})

  public static class DuplicateAndMissing {
    public Integer duplicate;
    public Integer missing;

    public DuplicateAndMissing(Integer duplicate, Integer missing) {
      this.duplicate = duplicate;
      this.missing = missing;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }

      DuplicateAndMissing that = (DuplicateAndMissing)o;

      if (!duplicate.equals(that.duplicate)) {
        return false;
      }
      return missing.equals(that.missing);
    }

    @Override
    public int hashCode() {
      int result = duplicate.hashCode();
      result = 31 * result + missing.hashCode();
      return result;
    }

    @Override
    public String toString() {
      return "duplicate: " + duplicate + ", missing: " + missing;
    }
  }

  @EpiTest(testDataFile = "find_missing_and_duplicate.tsv")

  // brute-force
  // public static DuplicateAndMissing findDuplicateMissing(List<Integer> A) {
  //   Map<Integer, Integer> map = new HashMap<>();
  //   // first pass --> hash map
  //   for (int val : A) {
  //     map.put(val, map.getOrDefault(val, 0) + 1);
  //   }
  //   // second pass -->
  //   Integer duplicate = null, missing = null;
  //   for (int i = 0; i < A.size(); ++i) {  // O(N)
  //     if (map.containsKey(i)) {
  //       if (map.get(i) == 2) duplicate = i; // duplicate found
  //     } else {
  //       missing = i; // missing found
  //     }
  //   }
  //   return new DuplicateAndMissing(duplicate, missing);
  // }

  // Sorting
  // public static DuplicateAndMissing findDuplicateMissing(List<Integer> A) {
  //   Collections.sort(A); // O(NlogN)
  //   Integer duplicate = null, missing = null;
  //   if (A.size() == 2) { // special case [0, 0] & [1, 1]
  //     return (A.get(0) == 0) ? new DuplicateAndMissing(0, 1) : new DuplicateAndMissing(1, 0);
  //   }
  //   for (int i = 0; i < A.size() - 1; ++i) {
  //     int curr = A.get(i), next = A.get(i + 1);
  //     if (curr == next) {
  //       duplicate = curr;
  //     }
  //     if (curr + 2 == next) {
  //       missing = curr + 1;
  //     }
  //   }
  //   return new DuplicateAndMissing(duplicate, missing);
  // }


  // better bitwise approach - time: O(N)  space: O(1)
  public static DuplicateAndMissing findDuplicateMissing(List<Integer> A) {
    // first pass - XOR = duplicate ^ missing
    int xor = 0;
    for (int i = 0; i < A.size(); ++i) {
      xor = xor ^ (i ^ A.get(i));
    }
    int oneBit = xor & ~(xor - 1); // isolate one "1" bit
    // second pass - split numbers into two groups (one contains duplicate, the other contains missing)
    int numSet = 0, numUnset = 0;
    for (int i = 0; i < A.size(); ++i) {
      // i
      if ((i & oneBit) > 0) {   // set
        numSet ^= i;
      } else {                  // unset
        numUnset ^= i;
      }
      // val
      int val = A.get(i);
      if ((val & oneBit) > 0) { // set
        numSet ^= val;
      } else {                  // unset
        numUnset ^= val;
      }
    }
    // third pass - distinguish
    Integer duplicate = null, missing = null;
    for (int val : A) {
      if (val == numSet) {
        duplicate = numSet; missing = numUnset; break;
      }
      if (val == numUnset) {
        duplicate = numUnset; missing = numSet; break;
      }
    }
    return new DuplicateAndMissing(duplicate, missing);
  }




  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SearchForMissingElement.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
