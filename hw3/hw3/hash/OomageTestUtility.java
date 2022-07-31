package hw3.hash;

import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        int[] buckets = new int[M];
        int num = oomages.size();
        for (Oomage o : oomages) {
            int bucketNum = (o.hashCode() & 0x7FFFFFFF) % M;
            buckets[bucketNum]++;
        }
        int bottomBound = num / 50;
        int upBound = (int) (num / 2.5);
        for (int bucket : buckets) {
            if (bucket > upBound || bucket < bottomBound) {
                return false;
            }
        }
        return true;
    }
}
