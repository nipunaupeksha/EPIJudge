package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class ClosestIntSameWeight {
  @EpiTest(testDataFile = "closest_int_same_weight.tsv")
  public static long closestIntSameBitCount(long x) {
    for(int i = 0;  i < Long.SIZE - 1; i++){
      if(((x >>> i) & 1) != ((x >>> (i + 1) & 1))){
        x ^= (1L << i) | (1L << (i + 1)); // toggle the the closest different bits
        return x;
      }
    }
    throw new IllegalArgumentException("All bits are 0 or 1.");
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ClosestIntSameWeight.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
