package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.lang.Long;
public class ReverseBits {

  private static final int MASK_SIZE = 16;
  private static final int BIT_MASK = 0xFFFF;

  // 2^16 possible 16-bit values
  private static final long[] PRECOMPUTED_REVERSE = new long[1 << MASK_SIZE];

  static {
    for (int i = 0; i < PRECOMPUTED_REVERSE.length; i++) {
      PRECOMPUTED_REVERSE[i] = reverse16Bits(i);
      }
  }

  public static long reverse16Bits(long x){
    long result = 0;

    for (int i = 0; i < MASK_SIZE; i++) {
      result = (result << 1) | (x & 1);
      x >>>= 1;
    }

    return result;
  }

  @EpiTest(testDataFile = "reverse_bits.tsv")
  public static long reverseBits(long x) {
    // Easiest solution
    // return Long.reverse(x);

    // using bit manipulation
    // long result = 0;
    // for(int i = 0;  i < Long.SIZE; i++){
    //   result <<= 1;
    //   result |= x & 1; // or result = (result << 1) | (x & 1);
    //   x >>>= 1;
    // }
    // return result;

    return (PRECOMPUTED_REVERSE[(int) (x & BIT_MASK)] << (3 * MASK_SIZE))
                | (PRECOMPUTED_REVERSE[(int) ((x >>> MASK_SIZE) & BIT_MASK)] << (2 * MASK_SIZE))
                | (PRECOMPUTED_REVERSE[(int) ((x >>> (2 * MASK_SIZE)) & BIT_MASK)] << MASK_SIZE)
                | PRECOMPUTED_REVERSE[(int) ((x >>> (3 * MASK_SIZE)) & BIT_MASK)];
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ReverseBits.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
