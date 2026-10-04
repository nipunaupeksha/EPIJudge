package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class Parity {
  @EpiTest(testDataFile = "parity.tsv")
  public static short parity(long x) {
    // ------------------------

    // Easiest way is to use XOR with the bits, so that even number of 1s will give 0 and odd number of 1s will give 1
    // short result = 0;
    // while(x != 0){
    //   result ^= (x & 1);
    //   x >>>= 1;
    // }
    // return result;

    // ------------------------

    // We can also clear the LSB everytime as well
    // short result = 0;
    // while(x != 0){
    //   result ^= 1;
    //   x &= (x-1);
    // }
    // return result;

    // ------------------------

    // Also we can have a cache as well
    // int BIT_MASK = 0xFF;
    // int MASK_SIZE = 8;

    // int[] PRECOMPUTED_PARITY = new int[(int)Math.pow(2, MASK_SIZE)];
    // for(int i = 1; i < (int)Math.pow(2, MASK_SIZE); i++){
    //   PRECOMPUTED_PARITY[i] = (PRECOMPUTED_PARITY[i >> 1] + (i & 1)) % 2;
    // }

    // return (short) (PRECOMPUTED_PARITY[(int) x & BIT_MASK] 
    // ^ PRECOMPUTED_PARITY[(int)(x >>> MASK_SIZE) & BIT_MASK] 
    // ^ PRECOMPUTED_PARITY[(int)(x >>> (2 * MASK_SIZE)) & BIT_MASK] 
    // ^ PRECOMPUTED_PARITY[(int)(x >>> (3 * MASK_SIZE)) & BIT_MASK]
    // ^ PRECOMPUTED_PARITY[(int)(x >>> (4 * MASK_SIZE)) & BIT_MASK]
    // ^ PRECOMPUTED_PARITY[(int)(x >>> (5 * MASK_SIZE)) & BIT_MASK]
    // ^ PRECOMPUTED_PARITY[(int)(x >>> (6 * MASK_SIZE)) & BIT_MASK]
    // ^ PRECOMPUTED_PARITY[(int)(x >>> (7 * MASK_SIZE)) & BIT_MASK]);

    // ------------------------

    // We can simplify this to, but since the array size increases the time it takes is more than 2ms
    // int BIT_MASK = 0xFFFF;
    // int MASK_SIZE = 16;

    // int[] PRECOMPUTED_PARITY = new int[(int)Math.pow(2, MASK_SIZE)];
    // for(int i = 1; i < (int)Math.pow(2, MASK_SIZE); i++){
    //   PRECOMPUTED_PARITY[i] = (PRECOMPUTED_PARITY[i >> 1] + (i & 1)) % 2;
    // }

    // return (short) (PRECOMPUTED_PARITY[(int) x & BIT_MASK] 
    // ^ PRECOMPUTED_PARITY[(int)(x >>> MASK_SIZE) & BIT_MASK] 
    // ^ PRECOMPUTED_PARITY[(int)(x >>> (2 * MASK_SIZE)) & BIT_MASK] 
    // ^ PRECOMPUTED_PARITY[(int)(x >>> (3 * MASK_SIZE)) & BIT_MASK]);

    // ------------------------
    
    // Since we know the number XOR is associative, we can do this in O(n) time
    x ^= x >>> 32;
    x ^= x >>> 16;
    x ^= x >>> 8;
    x ^= x >>> 4;
    x ^= x >>> 2;
    x ^= x >>> 1;
    return (short) (x & 1);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "Parity.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
