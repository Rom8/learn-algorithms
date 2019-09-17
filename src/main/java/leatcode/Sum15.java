package leatcode;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Sum15 {
    public List<List<Integer>> threeSum(int[] nums) {
        final Map<Integer, Integer> numbers = IntStream.of(nums)
                .boxed()
                .sorted(Comparator.comparing((Integer a) -> -Math.abs(a)).thenComparingInt(a -> a))
                .collect(Collectors.toMap(a -> a, a -> 1, Integer::sum, LinkedHashMap::new));

        TreeSet<List<Integer>> result = new TreeSet<>(new ListComparator());
        final Set<Integer> keys = numbers.keySet();
        final Iterator<Integer> iteratorOut = keys.iterator();
        while (iteratorOut.hasNext()) {
            Integer a = iteratorOut.next();
            //check three zeroes case
            if (a == 0) {
                if (numbers.get(0) >= 3) {
                    result.add(Arrays.asList(0,0,0));
                }
                break;
            }
            if (a % 2 == 0 && numbers.getOrDefault(-a/2, 0) > 1) {
                if (a < 0) {
                    result.add(Arrays.asList(a, -a/2, -a/2));
                } else {
                    result.add(Arrays.asList(-a/2, -a/2, a));
                }
            }
            final Iterator<Integer> iteratorInner = keys.iterator();
            iteratorInner.next();
            while (iteratorInner.hasNext()) {
                int b = iteratorInner.next();
                if (keys.contains(-a-b) && Math.abs(b) > Math.abs(-a-b)) {
                    if (a < b) {
                        result.add(Arrays.asList(a, -a-b, b));
                    } else {
                        result.add(Arrays.asList(b, -a-b, a));
                    }
                }
            }
            iteratorOut.remove();
        }
        return new ArrayList<>(result);
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
}
