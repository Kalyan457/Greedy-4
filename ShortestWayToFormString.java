import java.util.*;
public class ShortestWayToFormString {
    // TC: O(T. log S) where T is length of target and S is length of source
    // SC: O(1)
    public int shortestWay(String source, String target) {
        Map<Character, List<Integer>> charIndices = new HashMap<>();
        for (int i = 0; i < source.length(); i++) {
            charIndices.putIfAbsent(source.charAt(i), new ArrayList<>());
            charIndices.get(source.charAt(i)).add(i);
        }
        int i = 0, j = 0;
        int result = 0;
        while (j < target.length()) {
            char sChar = source.charAt(i);
            char tChar = target.charAt(j);
            if (!charIndices.containsKey(tChar)) return -1;
            if (sChar == tChar) {
                i++;
                j++;
            } else {
                List<Integer> indices = charIndices.get(tChar);
                int bsIndex = binarySearch(indices, i);
                if (bsIndex != -1) {
                    i = bsIndex;
                } else {
                    if (i > indices.get(0)) {
                        result++;
                    }
                    i = indices.get(0);
                }
            }
            if (i == source.length()) {
                result++;
                i = 0;
            }
        }
        if (i != 0) {
            return result + 1;
        } else {
            return result;
        }
    }
    private int binarySearch(List<Integer> vals, int target) {
        int left = 0, right = vals.size() - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (vals.get(mid) > target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return vals.get(left) > target ? vals.get(left) : -1;
    }
    // TC: O(T.S) where T is length of target and S is length of source
    // SC: O(1)
    private int shortestWayBruteForce(String source, String target) {
        Set<Character> sSet = new HashSet<>();
        for (char ch : source.toCharArray()) {
            sSet.add(ch);
        }
        int i = 0, j = 0;
        int result = 0;
        while (j < target.length()) {
            char sChar = source.charAt(i);
            char tChar = target.charAt(j);
            if (!sSet.contains(tChar)) {
                return -1;
            }
            if (sChar == tChar) {
                i++;
                j++;
            } else {
                i++;
            }
            if (i == source.length()) {
                result++;
                i = 0;
            }
        }
        if (i != 0) {
            return result + 1;
        } else {
            return result;
        }
    }
}
