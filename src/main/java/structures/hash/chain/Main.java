package structures.hash.chain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {

    public static void main(String[] args) throws IOException {

//        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("chain1.txt")));
//        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("chain2.txt")));
//        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("chain3.txt")));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int m = Integer.parseInt(br.readLine());
        Hashtable hashtable = new Hashtable(m);
        int operationsCount = Integer.parseInt(br.readLine());
        for (int i = 0; i < operationsCount; i++) {
            String[] command = br.readLine().split(" ");
            switch (command[0]) {
                case "add":
                    hashtable.add(command[1]);
                    break;
                case "del":
                    hashtable.delete(command[1]);
                    break;
                case "find":
                    hashtable.find(command[1]);
                    break;
                case "check":
                    hashtable.check(Integer.parseInt(command[1]));
                    break;
            }
        }
    }
}

class Hashtable {

    private final static BigInteger P = BigInteger.valueOf(1_000_000_007L);
    private final static BigInteger X = BigInteger.valueOf(263L);

    private final Container[] table;

    Hashtable(int m) {
        this.table = new Container[m];
    }

    void add(String word) {
        int position = hash(word);
        Container container = table[position];
        if (container == null) {
            table[position] = new Container(word);
        } else {
            do {
                if (container.getValue().equals(word)) {
                    return;
                }
                container = container.getNext();
            } while (container != null);
            table[position] = new Container(word, table[position]);
        }
    }

    void delete(String word) {
        int position = hash(word);
        if (table[position] == null) {
            return;
        }
        Container a = table[position];
        if (a.getValue().equals(word)) {
            table[position] = a.getNext();
            return;
        }
        Container previous = a;
        Container current = a.getNext();
        while (current != null) {
            if (current.getValue().equals(word)) {
                previous.setNext(current.getNext());
                return;
            }
            previous = current;
            current = current.getNext();
        }

    }

    void find(String word) {
        int position = hash(word);
        if (table[position] == null) {
            System.out.println("no");
        } else {
            Container container = table[position];
            while (container != null) {
                if (container.getValue().equals(word)) {
                    System.out.println("yes");
                    return;
                }
                container = container.getNext();
            }
            System.out.println("no");
        }
    }

    void check(int i) {
        if (table[i] == null) {
            System.out.println();
        } else {
            StringBuilder toPrint = new StringBuilder();
            Container container = table[i];
            while (container != null) {
                toPrint.append(container.getValue()).append(' ');
                container = container.getNext();
            }
            System.out.println(toPrint);
        }
    }

    private int hash(String word) {
        BigInteger sum = BigInteger.valueOf(0L);
        for (int i = 0; i < word.length(); i++) {
            int ascii = word.charAt(i);
            BigInteger asciiByModule = BigInteger.valueOf(ascii).mod(P);
            BigInteger xPowByModule = X.modPow(BigInteger.valueOf(i), P);
            BigInteger multiplication = (asciiByModule.multiply(xPowByModule)) .mod(P);
            sum = (sum.add(multiplication)) .mod(P);
        }
        return sum.mod(BigInteger.valueOf(table.length)).intValue();
    }
}

class Container {
    private final String value;
    private Container next;

    public Container(String value) {
        this.value = value;
    }

    public Container(String value, Container next) {
        this.value = value;
        this.next = next;
    }

    public String getValue() {
        return value;
    }

    public Container getNext() {
        return next;
    }

    public void setNext(Container next) {
        this.next = next;
    }
}