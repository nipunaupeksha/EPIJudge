package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class PrimitiveDivide {
  @EpiTest(testDataFile = "primitive_divide.tsv")
  public static int divide(int x, int y) {

    // ## Example: `20 / 3`

    // Start:

    // - `x = 20`
    // - `y = 3`
    // - `result = 0`

    // Find the largest shifted value of `3` that fits into `20`:

    // - `3 << 3 = 24` → too large
    // - `3 << 2 = 12` → fits

    // So:

    // - `power = 2`
    // - `yPower = 12`
    // - `1 << 2 = 4`

    // Update:

    // - `result = 0 + 4 = 4`
    // - `x = 20 - 12 = 8`

    // Next, find the largest shifted value of `3` that fits into `8`:

    // - `3 << 2 = 12` → too large
    // - `3 << 1 = 6` → fits

    // So:

    // - `power = 1`
    // - `yPower = 6`
    // - `1 << 1 = 2`

    // Update:

    // - `result = 4 + 2 = 6`
    // - `x = 8 - 6 = 2`

    // Now:

    // - `x = 2`
    // - `y = 3`
    // - `2 >= 3` is false

    // Stop.

    // Final result:

    // - `20 / 3 = 6`
    // - Remainder = `2`

    // ### Key idea

    // `y << power` means:

    // `y × 2^power`

    // So when we subtract:

    // `3 << 2 = 12`

    // we are subtracting:

    // `3 × 4`

    // Therefore we add:

    // `1 << 2 = 4`

    // to the quotient.

    int result = 0;
    int power = 32;
    long yPower = (long) (y << power);
    while(x >= y){
      while(yPower > x){
        yPower >>>= 1;
        power--;
      }

      result += (1 << power);
      x -= yPower;
    }
    return result;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PrimitiveDivide.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
