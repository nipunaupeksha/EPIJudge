package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class CountBits {
  @EpiTest(testDataFile = "count_bits.tsv")

  public static short countBits(int x) {
    // Easiest way is to count all the bits until the number is zero
    // short numBits = 0;
    // while(x != 0){
    //   numBits += (x & 1);
    //   x >>>= 1;
    // }
    // return numBits;

    // other than that you can also clear the remove the lowest set bit until the number is zero
    short numBits = 0;
    while(x != 0){
      x &= (x-1);
      numBits++;
    }
    return numBits;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "CountBits.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
