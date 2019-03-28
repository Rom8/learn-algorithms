package structures.nonintersectionsets.tableunion;

import java.io.*;
import java.util.Arrays;
import java.util.PrimitiveIterator;

public class Main {

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("tablesUnion.txt")));
//        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("tablesUnion2.txt")));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] firstLineArgs = br.readLine().split(" ");
        int tablesCount = Integer.parseInt(firstLineArgs[0]);
        int queriesCount = Integer.parseInt(firstLineArgs[1]);

        String[] secondLine = br.readLine().split(" ");
        PrimitiveIterator.OfInt iterator = Arrays.stream(secondLine)
                .mapToInt(Integer::parseInt)
                .iterator();

        Tables tables = new Tables(iterator, tablesCount);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < queriesCount; i++) {
            String[] query = br.readLine().split(" ");
            int destination = Integer.parseInt(query[0]);
            int source = Integer.parseInt(query[1]);
            long max = tables.union(destination, source);
            result.append(max).append('\n');
        }
        System.out.print(result);
    }
}

class Table {
    private final int index;
    private int parent;     //invariant: if parent != index, then size == 0
    private long size;

    public Table(int index, long size) {
        this.index = index;
        this.parent = index;
        this.size = size;
    }

    public int getIndex() {
        return index;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

}

class Tables {
    //all except 0
    private final Table[] tables;

    private long maxSize;

    public Tables(PrimitiveIterator.OfInt iter, int size) {
        tables = new Table[size + 1];
        for (int i = 1; i <= size; i++) {
            tables[i] = new Table(i, iter.nextInt());
            maxSize = Math.max(tables[i].getSize(), maxSize);
        }
    }

    public long union(int destination, int source) {
        destination = find(destination);
        source = find(source);
        if (destination == source) {
            return maxSize;
        }
        Table destTable = tables[destination];
        Table sourceTable = tables[source];
        long newSize = destTable.getSize() + sourceTable.getSize();
        destTable.setSize(newSize);
        sourceTable.setSize(0);
        sourceTable.setParent(destTable.getIndex());
        changeMaxSize(newSize);
        return maxSize;
    }

    private void changeMaxSize(long size) {
        maxSize = Math.max(maxSize, size);
    }

    private int find(int i) {
        if (i != tables[i].getParent()) {
            tables[i].setParent(find(tables[i].getParent()));
        }
        return tables[i].getParent();
    }

}