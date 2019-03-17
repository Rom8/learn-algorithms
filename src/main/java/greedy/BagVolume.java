package greedy;

import java.util.*;

public class BagVolume {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final int thingsCount = scanner.nextInt();
        final int bagVolume = scanner.nextInt();

        Set<Thing> things = new TreeSet<>();

        int counter = 0;
        while (scanner.hasNextLine() && counter < thingsCount) {
            int cost = scanner.nextInt();
            int volume = scanner.nextInt();
            things.add(new Thing(cost, volume));
            counter++;
        }

        Bag bag = new Bag(bagVolume);

        for (Iterator<Thing> iter = ((TreeSet<Thing>) things).descendingIterator(); iter.hasNext() && bag.freeSpace > 0;) {
            Thing thing = iter.next();
            if (thing.volume <= bag.freeSpace) {
                bag.add(thing);
            } else {
                Thing lowerThing = Thing.lowerVolume(thing, bag.freeSpace);
                bag.add(lowerThing);
            }
        }
        System.out.format("%.3f", bag.getCost());
    }

    static class Bag {
        private final int bagVolume;
        private int freeSpace;
        private double cost;

        private List<Thing> things = new ArrayList<>();

        public Bag(int bagVolume) {
            this.bagVolume = bagVolume;
            freeSpace = bagVolume;
        }

        public void add(Thing thing) {
            if (thing.volume > freeSpace) {
                throw new IllegalStateException("Not enough free space.");
            }
            things.add(thing);
            freeSpace -= thing.volume;
            cost += thing.cost;
        }

        public int getFreeSpace() {
            return freeSpace;
        }

        public double getCost() {
            return cost;
        }
    }


    static class Thing implements Comparable<Thing> {

        private final double cost;
        private final int volume;
        private final double price;

        public Thing(double cost, int volume) {
            this.cost = cost;
            this.volume = volume;
            price = volume / cost;
        }

        private Thing(double cost, int volume, double price) {
            this.cost = cost;
            this.volume = volume;
            this.price = price;
        }

        public static Thing lowerVolume(Thing thing, int lowerVolume) {
            double lowerCost = (thing.cost * lowerVolume) / thing.volume;
            return new Thing(lowerCost, lowerVolume, thing.price);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Thing thing = (Thing) o;
            return cost == thing.cost &&
                    volume == thing.volume;
        }

        @Override
        public int hashCode() {
            return Objects.hash(cost, volume);
        }

        @Override
        public int compareTo(Thing o) {
            double diff = o.price - price;
            if (0 < diff && diff < 1) {
                return 1;
            }
            if (-1 < diff && diff < 0) {
                return -1;
            }
            return (int) diff;
        }
    }
}
