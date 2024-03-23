import java.util.HashMap;
import java.util.Map;

public class MinimumDominoRotations {
    // TC: O(N) where N is length of tops array
    // SC: O(1)
    public int minDominoRotations(int[] tops, int[] bottoms) {
        int rotations = check(tops[0], tops, bottoms);
        if (rotations != -1 || tops[0] == bottoms[0]) return rotations;
        return check(bottoms[0], tops, bottoms);
    }
    private int check(int target, int[] A, int[] B) {
        int aRotations = 0, bRotations = 0, n = A.length;
        for (int i = 0; i < n; i++) {
            if (A[i] != target && B[i] != target) return -1;
            if (A[i] != target) {
                aRotations++;
            }
            if (B[i] != target) {
                bRotations++;
            }
        }
        return Math.min(aRotations, bRotations);
    }

    // TC: O(N) where N is length of tops array
    // SC: O(1)
    private int minDominoRotationsUsingFreq(int[] tops, int[] bottoms) {
        Map<Integer, Integer> freq = new HashMap<>();
        int n = tops.length, maxElement = -1;
        for (int i = 0; i < n; i++) {
            freq.put(tops[i], freq.getOrDefault(tops[i], 0) + 1);
            if (freq.get(tops[i]) >= n) {
                maxElement = tops[i];
                break;
            }
            freq.put(bottoms[i], freq.getOrDefault(bottoms[i], 0) + 1);
            if (freq.get(bottoms[i]) >= n) {
                maxElement = bottoms[i];
                break;
            }
        }
        if (maxElement == -1) return -1;
        int topRotations = 0, bottomRotations = 0;
        for (int i = 0; i < n; i++) {
            if (tops[i] != maxElement && bottoms[i] != maxElement) return -1;
            if (tops[i] != maxElement) {
                topRotations++;
            }
            if (bottoms[i] != maxElement) {
                bottomRotations++;
            }
        }
        return Math.min(topRotations, bottomRotations);
    }
}
