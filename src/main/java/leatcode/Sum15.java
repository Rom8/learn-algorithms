package leatcode;

import java.util.*;

public class Sum15 {
    public List<List<Integer>> threeSum(int[] nums) {
        int zeroCount = 0;
        final TreeMap<Integer, Integer> negativeNumbers = new TreeMap<>();
        final TreeMap<Integer, Integer> positiveNumbers = new TreeMap<>();
        for (int number: nums) {
            if (number > 0) {
                positiveNumbers.merge(number, 1, Integer::sum);
            } else if(number < 0) {
                negativeNumbers.merge(number, 1, Integer::sum);
            } else {
                zeroCount++;
            }
        }
        Set<List<Integer>> result = new HashSet<>();
        if (zeroCount >= 3) {
            final List<Integer> threeZeroes = Arrays.asList(0, 0, 0);
            result.add(threeZeroes);
        }
        if (negativeNumbers.size() == 0 || positiveNumbers.size() == 0) {
            return new ArrayList<>(result);
        }
        calculateForOneSide(negativeNumbers, positiveNumbers, result);
        calculateForOneSide(positiveNumbers, negativeNumbers, result);

        for (Integer number: positiveNumbers.keySet()) {
            if (zeroCount > 0 && negativeNumbers.containsKey(-number)) {
                insertWithoutSorting(-number, 0, number, result);
            }
        }

        final ArrayList<List<Integer>> lists = new ArrayList<>(result);
        lists.sort(new ListComparator());
        return lists;
    }

    private void calculateForOneSide(TreeMap<Integer, Integer> leftNumbers, TreeMap<Integer, Integer> rightNumbers, Set<List<Integer>> result) {
        for (Integer a: rightNumbers.keySet()) {
            final int key = -a / 2;
            if (a % 2 == 0) {
                if (leftNumbers.getOrDefault(key, 0) > 1) {
                    if (key < 0) {
                        insertWithoutSorting(key, key, a, result);
                    } else {
                        insertWithoutSorting(a, key, key, result);
                    }
                }
            }

            final NavigableMap<Integer, Integer> subMap = getMap(leftNumbers, a);
            final Iterator<Integer> iterator = subMap.navigableKeySet().iterator();
            while (iterator.hasNext()) {
                final Integer x = iterator.next();
                if (x >key) {
                    break;
                }
                if (subMap.containsKey(-a-x)) {
                    int y = -a-x;
                    if (x==y) {
                        continue;
                    }
                    if (a < 0) {
                        if (x < y) {
                            insertWithoutSorting(a, x, y, result);
                        } else {
                            insertWithoutSorting(a, y, x, result);
                        }
                    } else {
                        if (x < y) {
                            insertWithoutSorting(x, y, a, result);
                        } else {
                            insertWithoutSorting(y, x, a, result);
                        }
                    }
                }
            }
        }
    }

    private NavigableMap<Integer, Integer> getMap(TreeMap<Integer, Integer> leftNumbers, Integer number) {
        if (number > 0) {
            return leftNumbers.subMap(-number, false, 0, false);
        } else {
            return leftNumbers.subMap(0, false, -number, false);
        }
    }

    static class ListComparator implements Comparator<List<Integer>> {

        @Override
        public int compare(List<Integer> o1, List<Integer> o2) {
            final int i1 = o1.get(0).compareTo(o2.get(0));
            final int i2 = o1.get(1).compareTo(o2.get(1));
            final int i3 = o1.get(2).compareTo(o2.get(2));
            return i1 != 0 ? i1 : (i2 != 0 ? i2 : i3);
        }
    }

    private void insertWithoutSorting(int a, int b, int c, Set<List<Integer>> result) {
        List<Integer> list = Arrays.asList(a, b, c);
        result.add(list);
    }

}
